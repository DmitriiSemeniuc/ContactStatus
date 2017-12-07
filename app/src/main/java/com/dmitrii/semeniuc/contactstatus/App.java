package com.dmitrii.semeniuc.contactstatus;

import android.app.Application;

import com.dmitrii.semeniuc.contactstatus.base.AppComponent;
import com.dmitrii.semeniuc.contactstatus.base.Authentication;
import com.dmitrii.semeniuc.contactstatus.base.DaggerAppComponent;
import com.dmitrii.semeniuc.contactstatus.modules.AppModule;
import com.dmitrii.semeniuc.contactstatus.modules.PrefsModule;
import com.dmitrii.semeniuc.contactstatus.modules.UserModule;

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
