package com.dmitrii.semeniuc.contactstatus.main;

import com.dmitrii.semeniuc.contactstatus.base.BaseView;

interface MainView extends BaseView<MainPresenter> {

    void startAuthActivity();

    void showProgress();

    void hideProgress();

    void startStatusService();

    void stopStatusService();
}
