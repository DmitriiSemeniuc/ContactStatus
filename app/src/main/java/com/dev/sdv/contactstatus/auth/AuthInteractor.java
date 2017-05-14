package com.dev.sdv.contactstatus.auth;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.dev.sdv.contactstatus.db.DbHelper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public interface AuthInteractor {

    void initAuth(Context context, FragmentActivity fragmentActivity);

    void onSignInWithGoogle(Intent data, OnGoogleLoginListener listener);

    void firebaseAuthWithGoogle(GoogleSignInAccount account, OnLoginListener listener);

    void createAccount(String email, String password, OnLoginListener listener);

    void loginWithEmail(String email, String password, OnLoginListener listener);

    void verifyIfUserSignedIn(OnLoginListener listener);

    void setCurrentUser();

    void createUser(DbHelper.OnUserChangeListener listener);

    interface OnGoogleLoginListener {

        void onGoogleLoginSuccess(GoogleSignInAccount account);

        void onGoogleLoginFailed(String error);
    }

    interface OnLoginListener {

        void onLoginSuccess();

        void onLoginFailed(String error);
    }
}
