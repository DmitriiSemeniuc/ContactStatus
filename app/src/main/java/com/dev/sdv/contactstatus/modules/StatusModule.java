package com.dev.sdv.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.models.User;

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
