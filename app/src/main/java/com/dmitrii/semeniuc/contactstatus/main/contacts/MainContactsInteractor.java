package com.dmitrii.semeniuc.contactstatus.main.contacts;

public interface MainContactsInteractor {

    void onStatusChanged();

    void registerOnStatusChangeListener(String uid);
}
