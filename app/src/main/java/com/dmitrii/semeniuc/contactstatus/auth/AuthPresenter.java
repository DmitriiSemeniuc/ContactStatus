package com.dmitrii.semeniuc.contactstatus.auth;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.dmitrii.semeniuc.contactstatus.base.BasePresenter;

interface AuthPresenter extends BasePresenter {

    void initAuth(Context context, FragmentActivity fragmentActivity);

    void verifyIfUserSignedIn();

    void onSignInWithGoogle(Intent data);

    void createAccount(String email, String password);

    void loginWithEmail(String email, String password);

    void onDestroy();
}
