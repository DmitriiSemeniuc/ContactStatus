package com.dev.sdv.contactstatus.repository;

import com.dev.sdv.contactstatus.auth.AuthInteractor;
import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.db.FireBaseDbHelper;
import com.dev.sdv.contactstatus.main.contacts.MainContactsInteractor;
import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.models.User;

import java.util.List;

public class StatusRepository implements Repository.StatusCRUD {

    private FireBaseDbHelper dbHelper;

    public StatusRepository(FireBaseDbHelper dbHelper){
        this.dbHelper = dbHelper;
    }

    @Override public void saveStatus(Status status, DbHelper.OnStatusChangeListener listener) {
        dbHelper.saveStatus(status, listener);
    }

    @Override
    public void getStatusById(String statusId, DbHelper.OnStatusRetrievedListener listener) {
        dbHelper.getStatusById(statusId, listener);
    }

    @Override public void registerOnStatusChangeListener(String uid) {
        dbHelper.registerStatusChangeListener(uid, null);
    }
}
