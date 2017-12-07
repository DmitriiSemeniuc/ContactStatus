package com.dmitrii.semeniuc.contactstatus.auth;

import com.dmitrii.semeniuc.contactstatus.base.BaseView;

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
