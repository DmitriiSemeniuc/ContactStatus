package com.dev.sdv.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dev.sdv.contactstatus.models.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UserModule {

    @Provides
    @NonNull
    @Singleton
    public User provideUser() {
        return new User();
    }
}
