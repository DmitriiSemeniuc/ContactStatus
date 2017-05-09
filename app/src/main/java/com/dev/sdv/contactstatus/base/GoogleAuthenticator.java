package com.dev.sdv.contactstatus.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.dev.sdv.contactstatus.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class GoogleAuthenticator implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = GoogleAuthenticator.class.getSimpleName();
    private GoogleSignInOptions gso;
    private GoogleApiClient apiClient;
    private Context context;

    public GoogleAuthenticator(Context context, FragmentActivity fragmentActivity){
        this.context = context;
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.web_client_id))
                .requestEmail()
                .build();

        apiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(fragmentActivity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public GoogleApiClient getApiClient() {
        return apiClient;
    }

    @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
        Toast.makeText(context, "Google Play Services error.", Toast.LENGTH_SHORT).show();
    }
}
