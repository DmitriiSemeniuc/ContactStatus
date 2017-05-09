package com.dev.sdv.contactstatus.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.R;
import com.dev.sdv.contactstatus.auth.AuthActivity;
import com.dev.sdv.contactstatus.base.Authentication;
import com.dev.sdv.contactstatus.base.GoogleAuthenticator;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.disconnect_btn)
    AppCompatButton disconnectBtn;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override protected void onStart() {
        if(Authentication.UserType.GOOGLE.toString().equals(App.getAuth().getUserType())){
            if(!App.getAuth().getGoogleApiClient().isConnected()){
                App.getAuth().getGoogleApiClient().disconnect();
            }
            App.getAuth().setGoogleApiClient(new GoogleAuthenticator(this, this).getApiClient());
            App.getAuth().getGoogleApiClient().connect();
            showDisconnectBtn();
        }
        super.onStart();
    }

    @OnClick(R.id.sign_out_btn)
    public void signOut() {
        // Firebase sign out
        App.getAuth().getFirebaseAuth().signOut();

        if (Authentication.UserType.GOOGLE.toString().equals(App.getAuth().getUserType())) {
            // Google sign out
            Auth.GoogleSignInApi.signOut(App.getAuth().getGoogleApiClient()).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            startActivity(new Intent(MainActivity.this, AuthActivity.class));
                            finish();
                        }
                    });
        } else {
            startActivity(new Intent(MainActivity.this, AuthActivity.class));
            finish();
        }
    }

    @OnClick(R.id.disconnect_btn)
    public void disconnect() {
        // Firebase sign out
        App.getAuth().getFirebaseAuth().signOut();

        if (Authentication.UserType.GOOGLE.toString().equals(App.getAuth().getUserType())) {
            // Google revoke access
            Auth.GoogleSignInApi.revokeAccess(App.getAuth().getGoogleApiClient()).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            startActivity(new Intent(MainActivity.this, AuthActivity.class));
                            finish();
                        }
                    });
        } else {
            startActivity(new Intent(MainActivity.this, AuthActivity.class));
            finish();
        }
    }

    private void showDisconnectBtn() {
        disconnectBtn.setVisibility(View.VISIBLE);
    }
}
