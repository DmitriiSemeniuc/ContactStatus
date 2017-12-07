package com.dmitrii.semeniuc.contactstatus.base;

import com.dmitrii.semeniuc.contactstatus.auth.AuthActivity;
import com.dmitrii.semeniuc.contactstatus.auth.AuthInteractorImpl;
import com.dmitrii.semeniuc.contactstatus.main.StatusService;
import com.dmitrii.semeniuc.contactstatus.main.contacts.ContactsFragment;
import com.dmitrii.semeniuc.contactstatus.main.contacts.MainContactsInteractorImpl;
import com.dmitrii.semeniuc.contactstatus.main.status.StatusFragment;
import com.dmitrii.semeniuc.contactstatus.main.MainActivity;
import com.dmitrii.semeniuc.contactstatus.main.MainInteractorImpl;
import com.dmitrii.semeniuc.contactstatus.main.status.MainStatusInteractorImpl;
import com.dmitrii.semeniuc.contactstatus.modules.AppModule;
import com.dmitrii.semeniuc.contactstatus.modules.DbHelperModule;
import com.dmitrii.semeniuc.contactstatus.modules.PrefsModule;
import com.dmitrii.semeniuc.contactstatus.modules.StatusModule;
import com.dmitrii.semeniuc.contactstatus.modules.StatusRepositoryModule;
import com.dmitrii.semeniuc.contactstatus.modules.UserModule;
import com.dmitrii.semeniuc.contactstatus.modules.UserRepositoryModule;

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
    void inject(MainContactsInteractorImpl contactsInteractor);
    void inject(MainActivity mainActivity);
    void inject(StatusFragment statusFragment);
    void inject(ContactsFragment contactsFragment);
    void inject(StatusService statusService);
}
