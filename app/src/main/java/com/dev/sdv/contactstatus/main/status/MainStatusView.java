package com.dev.sdv.contactstatus.main.status;

import com.dev.sdv.contactstatus.base.BaseView;

public interface MainStatusView extends BaseView<MainStatusPresenter> {

    void showProgress();

    void hideProgress();

    void showMessage(String message);

    void updateStatusUI(boolean showLocation, boolean autoChange, boolean freeLine,
                        boolean batteryNormal, boolean networkUnlimited,
                        boolean soundNormal, String message);
}
