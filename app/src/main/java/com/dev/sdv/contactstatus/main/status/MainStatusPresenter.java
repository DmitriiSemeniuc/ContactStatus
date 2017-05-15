package com.dev.sdv.contactstatus.main.status;

import com.dev.sdv.contactstatus.models.Status;

public interface MainStatusPresenter {

    void onDestroy();

    void getStatusFromDb(String uid);

    void saveStatusToDb(Status status);
}
