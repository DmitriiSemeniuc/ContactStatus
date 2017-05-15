package com.dev.sdv.contactstatus.main.status;

import com.dev.sdv.contactstatus.db.DbHelper;
import com.dev.sdv.contactstatus.models.Status;

public interface MainStatusInteractor {

    void getStatusFromDb(String uid, DbHelper.OnStatusRetrievedListener listener);

    void saveStatusToDb(Status status, DbHelper.OnStatusChangeListener listener);
}
