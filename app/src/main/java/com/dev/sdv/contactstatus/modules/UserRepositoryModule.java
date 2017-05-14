package com.dev.sdv.contactstatus.modules;

import android.support.annotation.NonNull;

import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.db.FireBaseDbHelper;
import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.models.User;
import com.dev.sdv.contactstatus.repository.UserRepository;

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
