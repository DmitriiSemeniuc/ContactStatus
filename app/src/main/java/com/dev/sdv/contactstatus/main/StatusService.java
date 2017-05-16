package com.dev.sdv.contactstatus.main;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.icu.text.LocaleDisplayNames;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.db.FireBaseDbHelper;
import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.utils.Connectivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class StatusService extends Service {

    @Inject Status currentStatus;
    private Status status;

    private NetworkConnectivityReceiver networkConnectivityReceiver;
    private DatabaseReference statusDbRef;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("onStartCommand", "Service started");
        return Service.START_STICKY;
    }

    @Override public void onCreate() {
        super.onCreate();
        ((App) getApplication()).getComponent().inject(this);
        statusDbRef = FirebaseDatabase.getInstance().getReference(DbHelper.FirebaseReference.STATUSES);
        status = currentStatus;
        registerBroadcastReceivers();
    }

    private void updateStatusInDb(Status status){
        try{
            statusDbRef.child(status.getUid()).setValue(status.toMap());
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void registerBroadcastReceivers() {
        registerNetworkReceiver();
    }

    private void registerNetworkReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        networkConnectivityReceiver = new NetworkConnectivityReceiver();
        registerReceiver(networkConnectivityReceiver, intentFilter);
    }

    @Nullable @Override public IBinder onBind(Intent intent) {
        return null;
    }

    @Override public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "Service destroyed");
        try {
            if (networkConnectivityReceiver != null)
                unregisterReceiver(networkConnectivityReceiver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    class NetworkConnectivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent arg1) {
            Log.d("Service", "Is alive!");
            Log.d("status", status.getStatusMessage());
            Log.d("currentStatus", currentStatus.getStatusMessage());

            if (Connectivity.isConnected(context)) {
                if (Connectivity.isConnectedWifi(context)) {
                    status.setNetworkUnlimited(true);
                    updateStatusInDb(status);
                    Log.d("Updated", "Network unlimited");
                } else if (Connectivity.isConnectedMobile(context)) {
                    status.setNetworkUnlimited(false);
                    updateStatusInDb(status);
                    Log.d("Updated", "Network limited");
                }
                status.setNetworkFast(Connectivity.isConnectedFast(context));
                updateStatusInDb(status);
                Log.d("Updated", "Network speed");
            }
        }
    }
}
