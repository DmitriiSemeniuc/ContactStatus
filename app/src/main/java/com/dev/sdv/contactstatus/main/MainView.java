package com.dev.sdv.contactstatus.main;

import com.dev.sdv.contactstatus.base.BaseView;

interface MainView extends BaseView<MainPresenter> {

    void startAuthActivity();

    void showProgress();

    void hideProgress();
}
