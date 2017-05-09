package com.dev.sdv.contactstatus.auth;

import android.content.Context;
import android.util.Log;

import com.dev.sdv.contactstatus.utils.Utils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

public class Authentication {

    public static final String TAG = Authentication.class.getSimpleName();
    private String userType;
    private FirebaseAuth firebaseAuth;
    private GoogleApiClient googleApiClient;

    public static enum UserType{NONE, EMAIL, GOOGLE}

    public void setUserType(String userType, Context context){
        this.userType = userType;
        Utils.setUserInPrefs(userType.toString(), context);
        Log.d(TAG, "user type: " + userType.toString());
    }

    public String getUserType(){
        return userType;
    }

    public void setGoogleApiClient(GoogleApiClient client){
        googleApiClient = client;
    }

    public GoogleApiClient getGoogleApiClient(){
        return googleApiClient;
    }

    public void setFirebaseAuth(FirebaseAuth fbAuth){
        firebaseAuth = fbAuth;
    }

    public FirebaseAuth getFirebaseAuth(){
        return firebaseAuth;
    }
}
