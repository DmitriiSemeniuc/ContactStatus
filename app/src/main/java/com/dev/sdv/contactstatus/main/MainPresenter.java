package com.dev.sdv.contactstatus.main;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.dev.sdv.contactstatus.base.BasePresenter;

interface MainPresenter extends BasePresenter {

    void reconnectGoogleApiClient(FragmentActivity fragmentActivity);

    void signOut();

    void disconnect();
}
