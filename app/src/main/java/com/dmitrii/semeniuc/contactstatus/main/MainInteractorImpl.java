package com.dmitrii.semeniuc.contactstatus.main;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.dmitrii.semeniuc.contactstatus.App;
import com.dmitrii.semeniuc.contactstatus.base.Authentication;
import com.dmitrii.semeniuc.contactstatus.base.GoogleAuthenticator;
import com.dmitrii.semeniuc.contactstatus.repository.StatusRepository;
import com.google.android.gms.auth.api.Auth;

import javax.inject.Inject;

public class MainInteractorImpl implements MainInteractor {

    private Context context;
    @Inject StatusRepository statusRepository;

    MainInteractorImpl(Context context){
        App.getInstance().getComponent().inject(this);
        this.context = context;
    }

    @Override public void reconnectGoogleApiClient(FragmentActivity fragmentActivity) {
        if (!App.getAuth().getGoogleApiClient().isConnected()) {
            App.getAuth().getGoogleApiClient().disconnect();
        }
        App.getAuth().setGoogleApiClient(new GoogleAuthenticator(context, fragmentActivity).getApiClient());
        App.getAuth().getGoogleApiClient().connect();
    }

    @Override public void signOut(final OnSignOutListener listener) {
        // Firebase sign out
        App.getAuth().getFirebaseAuth().signOut();
        if (Authentication.isGoogleUser()) {
            // Google sign out
            Auth.GoogleSignInApi.signOut(App.getAuth().getGoogleApiClient()).setResultCallback(
                    status -> listener.onSignOut());
        } else {
            listener.onSignOut();
        }
    }

    @Override public void disconnect(final OnSignOutListener listener) {
        // Firebase sign out
        App.getAuth().getFirebaseAuth().signOut();

        if (Authentication.isGoogleUser()) {
            // Google revoke access
            Auth.GoogleSignInApi.revokeAccess(App.getAuth().getGoogleApiClient()).setResultCallback(
                    status -> listener.onSignOut());
        } else {
            listener.onSignOut();
        }
    }
}
