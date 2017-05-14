package com.dev.sdv.contactstatus.base;

import com.dev.sdv.contactstatus.auth.AuthActivity;
import com.dev.sdv.contactstatus.auth.AuthInteractorImpl;
import com.dev.sdv.contactstatus.auth.AuthPresenterImpl;
import com.dev.sdv.contactstatus.fragments.StatusFragment;
import com.dev.sdv.contactstatus.main.MainActivity;
import com.dev.sdv.contactstatus.main.MainInteractorImpl;
import com.dev.sdv.contactstatus.modules.AppModule;
import com.dev.sdv.contactstatus.modules.DbHelperModule;
import com.dev.sdv.contactstatus.modules.PrefsModule;
import com.dev.sdv.contactstatus.modules.StatusModule;
import com.dev.sdv.contactstatus.modules.StatusRepositoryModule;
import com.dev.sdv.contactstatus.modules.UserModule;
import com.dev.sdv.contactstatus.modules.UserRepositoryModule;
import com.dev.sdv.contactstatus.repository.StatusRepository;

import javax.inject.Singleton;

import dagger.Component;

@Component(
        modules = {
                AppModule.class,
                UserModule.class,
                StatusModule.class,
                PrefsModule.class,
                UserRepositoryModule.class,
                StatusRepositoryModule.class,
                DbHelperModule.class
        }
)
@Singleton
public interface AppComponent {
    void inject(AuthActivity authActivity);
    void inject(AuthInteractorImpl authInteractor);
    void inject(MainInteractorImpl mainInteractor);
    void inject(MainActivity mainActivity);
    void inject(StatusFragment statusFragment);
}
