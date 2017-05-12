package com.dev.sdv.contactstatus.main;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.base.Authentication;
import com.dev.sdv.contactstatus.base.GoogleAuthenticator;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

class MainInteractorImpl implements MainInteractor {

    private Context context;

    MainInteractorImpl(Context context){
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
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            listener.onSignOut();
                        }
                    });
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
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            listener.onSignOut();
                        }
                    });
        } else {
            listener.onSignOut();
        }
    }
}
