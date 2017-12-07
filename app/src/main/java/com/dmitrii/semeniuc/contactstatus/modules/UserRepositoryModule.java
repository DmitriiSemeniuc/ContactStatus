package com.dmitrii.semeniuc.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dmitrii.semeniuc.contactstatus.db.FireBaseDbHelper;
import com.dmitrii.semeniuc.contactstatus.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class UserRepositoryModule {

    @Provides
    @NonNull
    @Singleton
    public UserRepository provideUserRepository(FireBaseDbHelper dbHelper) {
        return new UserRepository(dbHelper);
    }
}
