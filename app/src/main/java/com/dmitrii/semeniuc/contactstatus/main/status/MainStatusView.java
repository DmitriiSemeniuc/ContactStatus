package com.dmitrii.semeniuc.contactstatus.main.status;

import com.dmitrii.semeniuc.contactstatus.base.BaseView;

public interface MainStatusView extends BaseView<MainStatusPresenter> {

    void showProgress();

    void hideProgress();

    void showSuccessMessage(String message);

    void showErrorMessage(String message);

    void updateStatusUI(boolean showLocation, boolean autoChange, boolean freeLine,
                        boolean batteryNormal, boolean networkUnlimited, boolean networkFast,
                        boolean soundNormal, String message);
}
