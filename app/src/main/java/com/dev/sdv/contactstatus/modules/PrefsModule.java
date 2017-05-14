package com.dev.sdv.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dev.sdv.contactstatus.models.User;
import com.dev.sdv.contactstatus.utils.PrefsImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PrefsModule {

    @Provides
    @NonNull
    @Singleton
    public PrefsImpl providePrefs() {
        return new PrefsImpl();
    }
}
