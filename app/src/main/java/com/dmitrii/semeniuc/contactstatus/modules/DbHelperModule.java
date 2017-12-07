package com.dmitrii.semeniuc.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dmitrii.semeniuc.contactstatus.db.FireBaseDbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbHelperModule {

    @Provides
    @NonNull
    @Singleton
    public FireBaseDbHelper provideDbHelperModule() {
        return new FireBaseDbHelper();
    }
}
