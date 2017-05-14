package com.dev.sdv.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.db.FireBaseDbHelper;

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
