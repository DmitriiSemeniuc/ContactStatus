package com.dev.sdv.contactstatus.base;

import android.content.Context;
import android.util.Log;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.utils.Utils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {

    private static final String TAG = Authentication.class.getSimpleName();
    private String userType;
    private FirebaseAuth firebaseAuth;
    private GoogleApiClient googleApiClient;

    public enum UserType{NONE, EMAIL, GOOGLE}

    public void setUserType(String userType, Context context){
        this.userType = userType;
        //Utils.setUserInPrefs(userType, context);
        Log.d(TAG, "user type: " + userType);
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

    public static boolean isGoogleUser(){
        return UserType.GOOGLE.toString().equals(App.getAuth().getUserType());
    }

    public static FirebaseUser getCurrentUser(){
        return App.getAuth().getFirebaseAuth().getCurrentUser();
    }
}
