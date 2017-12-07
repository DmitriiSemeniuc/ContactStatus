package com.dmitrii.semeniuc.contactstatus.main.status;

import com.dmitrii.semeniuc.contactstatus.db.DbHelper;
import com.dmitrii.semeniuc.contactstatus.models.Status;

public interface MainStatusInteractor {

    void getStatusFromDb(String uid, DbHelper.OnStatusRetrievedListener listener);

    void saveStatusToDb(Status status, DbHelper.OnStatusChangeListener listener);
}
