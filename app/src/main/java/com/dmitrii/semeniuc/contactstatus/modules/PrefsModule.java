package com.dmitrii.semeniuc.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dmitrii.semeniuc.contactstatus.utils.PrefsImpl;

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
