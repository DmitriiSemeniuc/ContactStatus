package com.dmitrii.semeniuc.contactstatus.main.contacts;

import android.content.Context;

public class MainContactsPresenterImpl implements MainContactsPresenter {

    private MainContactsView contactsView;
    private MainContactsInteractor contactsInteractor;
    private Context context;

    public MainContactsPresenterImpl(){
    }

    public MainContactsPresenterImpl(MainContactsView contactsView, Context context) {
        this.contactsView = contactsView;
        this.context = context;
        this.contactsInteractor = new MainContactsInteractorImpl(context);
    }

    @Override public void registerOnStatusChangeListener(String uid) {
        contactsInteractor.registerOnStatusChangeListener(uid);
    }
}
