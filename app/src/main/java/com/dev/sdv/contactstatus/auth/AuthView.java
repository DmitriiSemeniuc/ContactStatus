package com.dev.sdv.contactstatus.auth;

import android.content.Intent;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.base.BaseView;
import com.google.android.gms.auth.api.Auth;

public interface AuthView extends BaseView<AuthPresenter> {

    interface LoginView {

        void loginWithGoogle();

        void loginWithEmail();

        boolean validateForm();

        void goToSignInScreen();
    }

    interface SignInView {

        void signIn();

        boolean validateForm();

        void goToLoginScreen();
    }

    void signInWithGoogle();

    void createAccount(String email, String password);

    void loginWithEmail(String email, String password);

    void showProgress();

    void hideProgress();

    void navigateToMain();

    void showLoginError(String error);
}
