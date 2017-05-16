package com.dev.sdv.contactstatus.base;

import com.dev.sdv.contactstatus.auth.AuthActivity;
import com.dev.sdv.contactstatus.auth.AuthInteractorImpl;
import com.dev.sdv.contactstatus.main.StatusService;
import com.dev.sdv.contactstatus.main.status.StatusFragment;
import com.dev.sdv.contactstatus.main.MainActivity;
import com.dev.sdv.contactstatus.main.MainInteractorImpl;
import com.dev.sdv.contactstatus.main.status.MainStatusInteractorImpl;
import com.dev.sdv.contactstatus.modules.AppModule;
import com.dev.sdv.contactstatus.modules.DbHelperModule;
import com.dev.sdv.contactstatus.modules.PrefsModule;
import com.dev.sdv.contactstatus.modules.StatusModule;
import com.dev.sdv.contactstatus.modules.StatusRepositoryModule;
import com.dev.sdv.contactstatus.modules.UserModule;
import com.dev.sdv.contactstatus.modules.UserRepositoryModule;

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
    void inject(MainStatusInteractorImpl mainStatusInteractor);
    void inject(MainActivity mainActivity);
    void inject(StatusFragment statusFragment);
    void inject(StatusService statusService);
}
