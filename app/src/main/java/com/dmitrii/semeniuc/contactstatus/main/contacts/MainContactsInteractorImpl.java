package com.dmitrii.semeniuc.contactstatus.main.contacts;

import android.content.Context;

import com.dmitrii.semeniuc.contactstatus.App;
import com.dmitrii.semeniuc.contactstatus.repository.StatusRepository;

import javax.inject.Inject;

public class MainContactsInteractorImpl implements MainContactsInteractor {

    private Context context;
    @Inject StatusRepository statusRepository;

    public MainContactsInteractorImpl(Context context) {
        App.getInstance().getComponent().inject(this);
    }

    @Override public void onStatusChanged() {

    }

    @Override public void registerOnStatusChangeListener(String uid) {
        statusRepository.registerOnStatusChangeListener(uid);
    }
}
