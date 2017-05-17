package com.dev.sdv.contactstatus.main;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.utils.Connectivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

public class StatusService extends Service {

    public static final String TAG = StatusService.class.getSimpleName();
    public static final String LOW_BATTERY_ACTION = "android.intent.action.BATTERY_LOW";
    public static final String OKAY_BATTERY_ACTION = "android.intent.action.BATTERY_OKAY";
    public static final String PHONE_STATE_ACTION = "android.intent.action.PHONE_STATE";
    public static final String OUTGOING_CALL_ACTION = "android.intent.action.NEW_OUTGOING_CALL";

    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
    private static boolean isIncoming;
    //@Inject Status currentStatus;
    @Inject Status status;
    private NetworkConnectivityReceiver networkConnectivityReceiver;
    private DatabaseReference statusDbRef;
    private BatteryStateReceiver batteryStateReceiver;
    private PhoneStateReceiver phoneStateReceiver;
    //private Status status;
    private DatabaseReference statusUpdatesDbRef;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        return Service.START_STICKY;
    }

    @Override public void onCreate() {
        super.onCreate();
        ((App) getApplication()).getComponent().inject(this);
        statusDbRef = FirebaseDatabase.getInstance().getReference(DbHelper.FirebaseReference.STATUSES);
        statusUpdatesDbRef = FirebaseDatabase.getInstance().getReference(DbHelper.FirebaseReference.STATUS_UPDATES);
        //status = currentStatus;
        registerBroadcastReceivers();
    }

    private void updateStatusInDb(Status status) {
        try {
            statusDbRef.child(status.getUid()).setValue(status.toMap());
            statusUpdatesDbRef.child(status.getUid()).child("updated").setValue(statusUpdatesDbRef.push().getKey());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void registerBroadcastReceivers() {
        registerNetworkReceiver();
        registerBatteryReceiver();
        registerPhoneStateReceiver();
    }

    private void registerNetworkReceiver() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        networkConnectivityReceiver = new NetworkConnectivityReceiver();
        registerReceiver(networkConnectivityReceiver, intentFilter);
    }

    private void registerBatteryReceiver() {
        IntentFilter batteryState = new IntentFilter();
        batteryState.addAction(LOW_BATTERY_ACTION);
        batteryState.addAction(OKAY_BATTERY_ACTION);
        batteryStateReceiver = new BatteryStateReceiver();
        registerReceiver(batteryStateReceiver, batteryState);
    }

    private void registerPhoneStateReceiver() {
        IntentFilter phoneState = new IntentFilter();
        phoneState.addAction(PHONE_STATE_ACTION);
        phoneState.addAction(OUTGOING_CALL_ACTION);
        phoneStateReceiver = new PhoneStateReceiver();
        registerReceiver(phoneStateReceiver, phoneState);
    }

    @Nullable @Override public IBinder onBind(Intent intent) {
        return null;
    }

    @Override public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        try {
            if (networkConnectivityReceiver != null){
                unregisterReceiver(networkConnectivityReceiver);
            }
            if(batteryStateReceiver != null){
                unregisterReceiver(batteryStateReceiver);
            }
            if(phoneStateReceiver != null){
                unregisterReceiver(phoneStateReceiver);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    //** PHONE STATE ZONE ***********

    /**
     * Incoming call-  goes from IDLE to RINGING when it rings, to OFFHOOK when it's answered, to IDLE when its hung up
     * Outgoing call-  goes from IDLE to OFFHOOK when it dials out, to IDLE when hung up
     */
    public void onCallStateChanged(Context context, int state) {
        try {
            if (lastState == state) {
                //No change, debounce extras
                Log.d(TAG, "phone state NO CHANGE");
                return;
            }
            switch (state) {
                case TelephonyManager.CALL_STATE_RINGING:
                    /* Incoming call started */
                    isIncoming = true;
                    status.setFreeLine(false);
                    updateStatusInDb(status);
                    Log.d(TAG, "phone state incoming call started");
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    //Transition of ringing->offhook are pickups of incoming calls.  Nothing done on them
                    if (lastState != TelephonyManager.CALL_STATE_RINGING) {
                        /* Outgoing call started */
                        isIncoming = false;
                        status.setFreeLine(false);
                        updateStatusInDb(status);
                        Log.d(TAG, "phone state outgoing call started");
                    }
                    break;
                case TelephonyManager.CALL_STATE_IDLE:
                    //Went to idle-  this is the end of a call.  What type depends on previous state(s)
                    if (lastState == TelephonyManager.CALL_STATE_RINGING) {
                        //Ring but no pickup-  a miss
                        /* Missed call */
                        status.setFreeLine(true);
                        updateStatusInDb(status);
                        Log.d(TAG, "phone state missed call");
                    } else if (isIncoming) {
                        /* Incoming call ended */
                        status.setFreeLine(true);
                        updateStatusInDb(status);
                        Log.d(TAG, "phone state incoming call ended");
                    } else {
                        /* Outgoing call ended */
                        status.setFreeLine(true);
                        updateStatusInDb(status);
                        Log.d(TAG, "phone state outgoing call ended");
                    }
                    break;
            }
            lastState = state;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    class NetworkConnectivityReceiver extends BroadcastReceiver {
        @Override public void onReceive(Context context, Intent arg1) {
            try {
                Log.d(TAG, "network broadcast");

                if (Connectivity.isConnected(context)) {
                    if (Connectivity.isConnectedWifi(context)) {
                        status.setNetworkUnlimited(true);
                        updateStatusInDb(status);
                        Log.d(TAG, "Network unlimited");
                    } else if (Connectivity.isConnectedMobile(context)) {
                        status.setNetworkUnlimited(false);
                        updateStatusInDb(status);
                        Log.d(TAG, "Network limited");
                    }
                    status.setNetworkFast(Connectivity.isConnectedFast(context));
                    updateStatusInDb(status);
                    Log.d(TAG, "Network speed fast: " + status.isNetworkFast());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class BatteryStateReceiver extends BroadcastReceiver {
        @Override public void onReceive(Context context, Intent intent) {
            try {
                Log.d(TAG, "battery broadcast");
                if (intent.getAction().equals(LOW_BATTERY_ACTION)) {
                    status.setBatteryNormal(false);
                    updateStatusInDb(status);
                    Log.d(TAG, "battery OK");
                } else if (intent.getAction().equals(OKAY_BATTERY_ACTION)) {
                    status.setBatteryNormal(true);
                    updateStatusInDb(status);
                    Log.d(TAG, "battery low");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    //Deals with actual events
    public class PhoneStateReceiver extends BroadcastReceiver {
        @Override public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "phone state broadcast");
            String stateStr = intent.getExtras().getString(TelephonyManager.EXTRA_STATE);
            if(stateStr == null) return;
            int state = 0;
            if (stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                state = TelephonyManager.CALL_STATE_IDLE;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                state = TelephonyManager.CALL_STATE_OFFHOOK;
            } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                state = TelephonyManager.CALL_STATE_RINGING;
            }
            onCallStateChanged(context, state);
        }
    }
}
