package com.dev.sdv.contactstatus.main.contacts;

import android.content.Context;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.repository.StatusRepository;

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
