package com.dmitrii.semeniuc.contactstatus.main.status;

import android.content.Context;

import com.dmitrii.semeniuc.contactstatus.App;
import com.dmitrii.semeniuc.contactstatus.db.DbHelper;
import com.dmitrii.semeniuc.contactstatus.repository.StatusRepository;

import javax.inject.Inject;

public class MainStatusInteractorImpl implements MainStatusInteractor {

    @Inject StatusRepository statusRepository;
    private Context context;

    public MainStatusInteractorImpl(Context context) {
        App.getInstance().getComponent().inject(this);
    }

    @Override public void getStatusFromDb(String uid, DbHelper.OnStatusRetrievedListener listener) {
        statusRepository.getStatusById(uid, listener);
    }

    @Override public void saveStatusToDb(com.dmitrii.semeniuc.contactstatus.models.Status status, DbHelper.OnStatusChangeListener listener) {
        statusRepository.saveStatus(status, listener);
    }
}
