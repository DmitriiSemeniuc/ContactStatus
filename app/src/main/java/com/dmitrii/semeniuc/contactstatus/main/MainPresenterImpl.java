package com.dmitrii.semeniuc.contactstatus.main;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

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

    @Override public void onDestroy() {
        mainView = null;
    }
}
