package com.dmitrii.semeniuc.contactstatus.main;

import android.support.v4.app.FragmentActivity;

import com.dmitrii.semeniuc.contactstatus.base.BasePresenter;

public interface MainPresenter extends BasePresenter {

    void reconnectGoogleApiClient(FragmentActivity fragmentActivity);

    void signOut();

    void disconnect();

    void onDestroy();
}
