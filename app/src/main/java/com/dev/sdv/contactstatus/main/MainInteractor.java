package com.dev.sdv.contactstatus.main;

import android.support.v4.app.FragmentActivity;

public interface MainInteractor {

    void reconnectGoogleApiClient(FragmentActivity fragmentActivity);

    void signOut(OnSignOutListener listener);

    void disconnect(OnSignOutListener listener);

    interface OnSignOutListener {
        void onSignOut();
    }
}
