package com.dev.sdv.contactstatus.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.auth.AuthView;
import com.dev.sdv.contactstatus.base.Authentication;
import com.dev.sdv.contactstatus.base.GoogleAuthenticator;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnSignOutListener {

    private MainView mainView;
    private MainInteractor mainInteractor;

    MainPresenterImpl(MainView mainView, Context context) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl(context);
    }

    @Override public void reconnectGoogleApiClient(FragmentActivity fragmentActivity) {
        mainInteractor.reconnectGoogleApiClient(fragmentActivity);
    }

    @Override public void signOut() {
        mainInteractor.signOut(this);
    }

    @Override public void disconnect() {
        mainInteractor.disconnect(this);
    }

    @Override public void onSignOut() {
        mainView.startAuthActivity();
    }
}
