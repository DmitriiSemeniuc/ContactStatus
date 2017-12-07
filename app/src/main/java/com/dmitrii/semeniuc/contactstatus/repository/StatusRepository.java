package com.dmitrii.semeniuc.contactstatus.repository;

import com.dmitrii.semeniuc.contactstatus.db.DbHelper;
import com.dmitrii.semeniuc.contactstatus.db.FireBaseDbHelper;
import com.dmitrii.semeniuc.contactstatus.models.Status;

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
