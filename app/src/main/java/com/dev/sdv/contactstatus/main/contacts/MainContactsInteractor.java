package com.dev.sdv.contactstatus.main.contacts;

public interface MainContactsInteractor {

    void onStatusChanged();

    void registerOnStatusChangeListener(String uid);
}
