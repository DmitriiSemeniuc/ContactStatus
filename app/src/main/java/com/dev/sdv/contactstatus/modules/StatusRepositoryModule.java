package com.dev.sdv.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dev.sdv.contactstatus.db.FireBaseDbHelper;
import com.dev.sdv.contactstatus.repository.StatusRepository;
import com.dev.sdv.contactstatus.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class StatusRepositoryModule {

    @Provides
    @NonNull
    @Singleton
    public StatusRepository provideStatusRepository(FireBaseDbHelper dbHelper) {
        return new StatusRepository(dbHelper);
    }
}
