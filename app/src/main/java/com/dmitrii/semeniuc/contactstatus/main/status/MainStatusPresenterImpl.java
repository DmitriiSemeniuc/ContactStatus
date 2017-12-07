package com.dmitrii.semeniuc.contactstatus.main.status;

import android.content.Context;

import com.dmitrii.semeniuc.contactstatus.R;
import com.dmitrii.semeniuc.contactstatus.db.DbHelper;
import com.dmitrii.semeniuc.contactstatus.models.Status;

public class MainStatusPresenterImpl implements MainStatusPresenter, DbHelper.OnStatusRetrievedListener, DbHelper.OnStatusChangeListener {

    private MainStatusView statusView;
    private MainStatusInteractor statusInteractor;
    private Context context;

    public MainStatusPresenterImpl(){
    }

    public MainStatusPresenterImpl(MainStatusView statusView, Context context) {
        this.statusView = statusView;
        this.context = context;
        this.statusInteractor = new MainStatusInteractorImpl(context);
    }

    @Override public void onDestroy() {
        statusView = null;
    }

    @Override public void getStatusFromDb(String uid) {
        statusInteractor.getStatusFromDb(uid, this);
    }

    @Override public void saveStatusToDb(Status status) {
        if(statusView != null) statusView.showProgress();
        statusInteractor.saveStatusToDb(status, this);
    }

    @Override public void onStatusRetrieveSuccess(Status status) {
        if(status != null && statusView != null){
            statusView.updateStatusUI(status.isShowLocation(), status.isAutoChange(),
                    status.isFreeLine(), status.isBatteryNormal(), status.isNetworkUnlimited(),
                    status.isNetworkFast(), status.isSoundModeNormal(), status.getStatusMessage());
        }
    }

    @Override public void onStatusRetrieveFailed(String error) {
        if(statusView != null){
            statusView.showErrorMessage(context.getString(R.string.fail_to_retreive_status_from_db));
        }
    }

    @Override public void onStatusChangeSuccess() {
        if(statusView != null) {
            statusView.hideProgress();
            statusView.showSuccessMessage(context.getString(R.string.status_changed_successfully));
        }
    }

    @Override public void onStatusChangeFailed(String error) {
        if(statusView != null) {
            statusView.hideProgress();
            statusView.showErrorMessage(context.getString(R.string.status_change_failed));
        }
    }
}
