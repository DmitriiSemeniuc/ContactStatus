package com.dev.sdv.contactstatus.main;

import android.support.v4.app.FragmentActivity;

import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.models.Status;

interface MainInteractor {

    void reconnectGoogleApiClient(FragmentActivity fragmentActivity);

    void signOut(OnSignOutListener listener);

    void disconnect(OnSignOutListener listener);

    void saveStatusToDb(Status status, DbHelper.OnStatusChangeListener listener);

    interface OnSignOutListener {
        void onSignOut();
    }
}
