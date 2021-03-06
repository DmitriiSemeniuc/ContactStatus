package com.dmitrii.semeniuc.contactstatus.auth;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.dmitrii.semeniuc.contactstatus.db.DbHelper;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class AuthPresenterImpl implements AuthPresenter, AuthInteractor.OnGoogleLoginListener,
        AuthInteractor.OnLoginListener, DbHelper.OnUserChangeListener {

    private AuthView authView;
    private AuthInteractor authInteractor;

    AuthPresenterImpl(AuthView authView, Context context) {
        this.authView = authView;
        this.authInteractor = new AuthInteractorImpl(context);
    }

    @Override public void initAuth(Context context, FragmentActivity fragmentActivity) {
        authInteractor.initAuth(context, fragmentActivity);
    }

    @Override public void verifyIfUserSignedIn() {
        authInteractor.verifyIfUserSignedIn(this);
    }

    @Override public void onSignInWithGoogle(Intent data) {
        authInteractor.onSignInWithGoogle(data, this);
    }

    @Override public void createAccount(String email, String password) {
        if (authView != null) authView.showProgress();
        authInteractor.createAccount(email, password, this);
    }

    @Override public void loginWithEmail(String email, String password) {
        authInteractor.loginWithEmail(email, password, this);
    }

    @Override public void onGoogleLoginSuccess(GoogleSignInAccount account) {
        if (authView != null) authView.showProgress();
        // Google Sign In was successful, authenticate with Firebase
        authInteractor.firebaseAuthWithGoogle(account, this);
    }

    @Override public void onGoogleLoginFailed(String error) {
        if (authView != null) {
            authView.hideProgress();
            authView.showLoginError(error);
        }
    }

    @Override public void onLoginSuccess() {
        authInteractor.setCurrentUser();
        authInteractor.createUser(this);
    }

    @Override public void onLoginFailed(String error) {
        if (authView != null) {
            authView.hideProgress();
            authView.showLoginError(error);
        }
    }

    @Override public void onDestroy() {
        authView = null;
    }

    @Override public void onUserChangeSuccess() {
        if (authView != null) {
            authView.hideProgress();
            authView.navigateToMain();
        }
    }

    @Override public void onUserChangeFailed(String error) {
        if (authView != null) {
            authView.hideProgress();
            authView.showLoginError(error);
        }
    }
}
