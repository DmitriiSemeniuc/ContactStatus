package com.dev.sdv.contactstatus.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.R;
import com.dev.sdv.contactstatus.base.BaseActivity;
import com.dev.sdv.contactstatus.fragments.LoginFragment;
import com.dev.sdv.contactstatus.fragments.SignInFragment;
import com.dev.sdv.contactstatus.main.MainActivity;
import com.dev.sdv.contactstatus.models.User;
import com.google.android.gms.auth.api.Auth;

import javax.inject.Inject;

public class AuthActivity extends BaseActivity implements AuthView {

    private static final String TAG = AuthActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 9001;
    private AuthPresenter presenter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        ((App) getApplication()).getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setStatusBarColor(ContextCompat.getColor(this, R.color.blue_900));
        addFragment(R.id.fragment_container_ll, new LoginFragment(), LoginFragment.FRAGMENT_TAG);
        setPresenter(new AuthPresenterImpl(this, this));
        presenter.initAuth(this, this);
    }

    @Override public void onStart() {
        super.onStart();
        presenter.verifyIfUserSignedIn();
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) presenter.onSignInWithGoogle(data);
    }

    @Override public void setPresenter(AuthPresenter presenter) {
        this.presenter = presenter;
    }

    @Override public void signInWithGoogle() {
        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(App.getAuth().getGoogleApiClient()), RC_SIGN_IN);
    }

    @Override public void createAccount(String email, String password){
        presenter.createAccount(email, password);
    }

    @Override public void loginWithEmail(String email, String password) {
        presenter.loginWithEmail(email, password);
    }

    @Override public void showProgress() {
        showProgressDialog();
    }

    @Override public void hideProgress() {
        hideProgressDialog();
    }

    @Override public void navigateToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override public void showLoginError(String error){
        showToast(error);
    }

    public void displaySignInForm(){
        replaceFragment(R.id.fragment_container_ll, new SignInFragment(), LoginFragment.FRAGMENT_TAG, SignInFragment.FRAGMENT_TAG, null);
    }

    public void displayLoginForm(){
        replaceFragment(R.id.fragment_container_ll, new LoginFragment(), SignInFragment.FRAGMENT_TAG, LoginFragment.FRAGMENT_TAG, null);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
