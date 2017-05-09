package com.dev.sdv.contactstatus;

import android.app.Application;

import com.dev.sdv.contactstatus.auth.Authentication;

public class App extends Application {
    private static Authentication auth;

    public App() {
    }

    public static Authentication getAuth(){
        if(auth == null)
            auth = new Authentication();
        return auth;
    }
}
