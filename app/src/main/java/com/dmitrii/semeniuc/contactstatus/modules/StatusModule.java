package com.dmitrii.semeniuc.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dmitrii.semeniuc.contactstatus.models.Status;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class StatusModule {

    @Provides
    @NonNull
    @Singleton
    public Status provideStatus() {
        return new Status();
    }
}
