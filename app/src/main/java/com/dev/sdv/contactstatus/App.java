package com.dev.sdv.contactstatus;

import android.app.Application;

import com.dev.sdv.contactstatus.base.AppComponent;
import com.dev.sdv.contactstatus.base.Authentication;
import com.dev.sdv.contactstatus.base.DaggerAppComponent;
import com.dev.sdv.contactstatus.modules.AppModule;
import com.dev.sdv.contactstatus.modules.PrefsModule;
import com.dev.sdv.contactstatus.modules.UserModule;

import dagger.internal.DaggerCollections;

public class App extends Application {

    private AppComponent component;
    private static App singleton;
    private static Authentication auth;

    public App() {
    }

    @Override public void onCreate(){
        super.onCreate();
        singleton = this;
        component = buildComponent();
    }

    public static Authentication getAuth(){
        if(auth == null)
            auth = new Authentication();
        return auth;
    }

    public AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .userModule(new UserModule())
                .prefsModule(new PrefsModule())
                .build();
    }

    public static App getInstance() {
        return singleton;
    }

    public AppComponent getComponent() {
        return component;
    }
}
