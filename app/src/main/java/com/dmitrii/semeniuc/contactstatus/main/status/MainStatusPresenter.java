package com.dmitrii.semeniuc.contactstatus.main.status;

import com.dmitrii.semeniuc.contactstatus.models.Status;

public interface MainStatusPresenter {

    void onDestroy();

    void getStatusFromDb(String uid);

    void saveStatusToDb(Status status);
}
