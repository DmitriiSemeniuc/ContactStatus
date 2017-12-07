package com.dmitrii.semeniuc.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dmitrii.semeniuc.contactstatus.db.FireBaseDbHelper;
import com.dmitrii.semeniuc.contactstatus.repository.StatusRepository;

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
