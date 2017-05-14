package com.dev.sdv.contactstatus.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SwitchCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.dev.sdv.contactstatus.App;
import com.dev.sdv.contactstatus.R;
import com.dev.sdv.contactstatus.models.Status;
import com.dev.sdv.contactstatus.models.User;
import com.dev.sdv.contactstatus.utils.PrefsImpl;
import com.dev.sdv.contactstatus.utils.Utils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import butterknife.Unbinder;

public class StatusFragment extends Fragment {

    @BindView(R.id.auto_change_status_switch)
    SwitchCompat autoChangeStatusSwitch;
    @BindView(R.id.show_location_switch)
    SwitchCompat showLocationSwitch;
    @BindView(R.id.available_for_call_radiogroup)
    RadioGroup callAvailabilityRadioGroup;
    @BindView(R.id.available_for_call_radiobtn)
    RadioButton availableForCallRadioBtn;
    @BindView(R.id.oncall_radiobtn)
    RadioButton onCallRadioBtn;
    @BindView(R.id.battery_radiogroup)
    RadioGroup batteryStateRadioGroup;
    @BindView(R.id.battery_full_radiobtn)
    RadioButton batteryFullRadioBtn;
    @BindView(R.id.battery_low_radiobtn)
    RadioButton batteryLowRadioBtn;
    @BindView(R.id.network_radiogroup)
    RadioGroup networkStateRadioGroup;
    @BindView(R.id.network_unlimited_radiobtn)
    RadioButton networkUnlimitedRadioBtn;
    @BindView(R.id.network_limited_radiobtn)
    RadioButton networkLimitedRadioBtn;
    @BindView(R.id.sound_mode_radiogroup)
    RadioGroup soundModeRadioGroup;
    @BindView(R.id.sound_mode_normal_radiobtn)
    RadioButton soundModeNormalRadioBtn;
    @BindView(R.id.sound_mode_silent_radiobtn)
    RadioButton soundModeSilentRadioBtn;
    @BindView(R.id.status_msg_et)
    AppCompatEditText statusMessageET;
    @BindView(R.id.edit_status_msg_iv)
    AppCompatImageView editStatusMsgIV;
    @BindView(R.id.save_status_msg_iv)
    AppCompatImageView saveStatusMsgIV;
    @BindView(R.id.save_status_btn)
    AppCompatButton saveStatusBtn;
    @BindView(R.id.main_status_layout)
    ScrollView mainStatusLayout;
    private ArrayList<RadioButton> radioBtns = new ArrayList<>();

    private Unbinder unbinder;

    @Inject User user;
    @Inject PrefsImpl prefs;

    public StatusFragment() {
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                       Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        unbinder = ButterKnife.bind(this, view);
        ((App) getActivity().getApplication()).getComponent().inject(this);
        return view;
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusValues();
        setListOfRadioBtns();
    }

    private void setListOfRadioBtns() {
        radioBtns.add(availableForCallRadioBtn);
        radioBtns.add(onCallRadioBtn);
        radioBtns.add(batteryFullRadioBtn);
        radioBtns.add(batteryLowRadioBtn);
        radioBtns.add(networkUnlimitedRadioBtn);
        radioBtns.add(networkLimitedRadioBtn);
        radioBtns.add(soundModeNormalRadioBtn);
        radioBtns.add(soundModeSilentRadioBtn);
    }

    private void initStatusValues() {
        Status userStatus = user.getStatus();
        // Show location
        boolean showLocation = prefs.isShowLocation(getContext());
        showLocationSwitch.setChecked(showLocation);
        userStatus.setShowLocation(showLocation);
        // Change status automatically
        boolean autoChangeStatus = prefs.isAutoChangeStatus(getContext());
        autoChangeStatusSwitch.setChecked(autoChangeStatus);
        userStatus.setAutoChange(autoChangeStatus);
        // Call availability
        boolean availableForCall = prefs.isAvailableForCall(getContext());
        if(availableForCall){
            availableForCallRadioBtn.setChecked(true);
        } else {
            onCallRadioBtn.setChecked(true);
        }
        userStatus.setLineAvailable(availableForCall);
        // Battery state
        boolean batteryStateNormal = prefs.isBatteryStateNormal(getContext());
        if(batteryStateNormal){
            batteryFullRadioBtn.setChecked(true);
        } else {
            batteryLowRadioBtn.setChecked(true);
        }
        userStatus.setBatteryNormal(batteryStateNormal);
        // Network state
        boolean networkUnlimited = prefs.isNetworkUnlimited(getContext());
        if(networkUnlimited){
            networkUnlimitedRadioBtn.setChecked(true);
        } else {
            networkLimitedRadioBtn.setChecked(true);
        }
        userStatus.setNetworkUnlimited(networkUnlimited);
        // Sound mode
        boolean soundModeNormal = prefs.isSoundModeNormal(getContext());
        if(soundModeNormal){
            soundModeNormalRadioBtn.setChecked(true);
        } else {
            soundModeSilentRadioBtn.setChecked(true);
        }
        userStatus.setSoundModeNormal(soundModeNormal);
        // Status message
        String statusMsg = prefs.getStatusMessage(getContext());
        if(!TextUtils.isEmpty(statusMsg)) {
            statusMessageET.setText(statusMsg.trim());
            editStatusMsgIV.setVisibility(View.VISIBLE);
        }
        saveStatusMsgIV.setVisibility(View.INVISIBLE);
        saveStatusBtn.setEnabled(false);
    }

    private void clearRadioButtonsValues() {
        for(RadioButton btn : radioBtns){
            btn.setEnabled(false);
        }
    }

    public void resetRadioButtonsValues(){
        for(RadioButton btn : radioBtns){
            btn.setEnabled(true);
        }
        if(user.getStatus().isLineAvailable()) availableForCallRadioBtn.setChecked(true);
        else onCallRadioBtn.setChecked(true);
        if(user.getStatus().isBatteryNormal()) batteryFullRadioBtn.setChecked(true);
        else batteryLowRadioBtn.setChecked(true);
        if(user.getStatus().isNetworkUnlimited()) networkUnlimitedRadioBtn.setChecked(true);
        else networkLimitedRadioBtn.setChecked(true);
        if(user.getStatus().isSoundModeNormal()) soundModeNormalRadioBtn.setChecked(true);
        else soundModeSilentRadioBtn.setChecked(true);
    }

    @OnCheckedChanged(R.id.auto_change_status_switch)
    public void autoChangeStatusSwitch(boolean isChecked){
        saveStatusBtn.setEnabled(true);
        if(isChecked) clearRadioButtonsValues();
        else resetRadioButtonsValues();
    }

    @OnCheckedChanged(R.id.show_location_switch)
    public void onShowLocationSwitch(boolean isChecked){
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.available_for_call_radiobtn)
    public void onAvailableRadioBtnClicked(){
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.oncall_radiobtn)
    public void onOnCallRadioBtnClicked(){
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.battery_full_radiobtn)
    public void onBatteryFullRadioBtnClicked(){
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.battery_low_radiobtn)
    public void onBatteryLowRadioBtnClicked(){
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.network_unlimited_radiobtn)
    public void onNetworkUnlimitedRadioBtnClicked(){
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.network_limited_radiobtn)
    public void onNetworkLimitedRadioBtnClicked(){
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.sound_mode_normal_radiobtn)
    public void onSoundModeNormalRadioBtnClicked(){
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.sound_mode_silent_radiobtn)
    public void onSoundModeSilentRadioBtnClicked(){
        saveStatusBtn.setEnabled(true);
    }

    @OnTextChanged(R.id.status_msg_et)
    public void onStatusMessageChanged(CharSequence text) {
        if (TextUtils.isEmpty(text)) user.getStatus().setStatusMessage("");
        else user.getStatus().setStatusMessage(text.toString());
        editStatusMsgIV.setVisibility(View.INVISIBLE);
        saveStatusMsgIV.setVisibility(View.VISIBLE);
        saveStatusBtn.setEnabled(true);
    }

    @OnClick(R.id.edit_status_msg_iv)
    public void onChangeStatusIconClicked(){
        statusMessageET.setEnabled(true);
        statusMessageET.moveCursorToVisibleOffset();
        statusMessageET.setSelection(statusMessageET.getText().length());
        editStatusMsgIV.setVisibility(View.INVISIBLE);
        statusMessageET.requestFocus();
        Utils.showKeyboard(getContext());
    }

    @OnClick(R.id.save_status_msg_iv)
    public void onSaveStatusIconClicked(){
        saveStatusMsgIV.setVisibility(View.INVISIBLE);
        statusMessageET.clearFocus();
        if(!TextUtils.isEmpty(statusMessageET.getText().toString())){
            editStatusMsgIV.setVisibility(View.VISIBLE);
            statusMessageET.setEnabled(false);
        } else {
            editStatusMsgIV.setVisibility(View.INVISIBLE);
            statusMessageET.setEnabled(true);
        }
        Utils.hideKeyboard(mainStatusLayout, getContext());
    }

    @OnClick(R.id.save_status_btn)
    public void onSaveStatusBtnClicked(){
        saveStatusBtn.setEnabled(false);
        statusMessageET.clearFocus();
        saveStatusMsgIV.setVisibility(View.INVISIBLE);
        if(!TextUtils.isEmpty(statusMessageET.getText().toString())){
            statusMessageET.setEnabled(false);
            editStatusMsgIV.setVisibility(View.VISIBLE);
        } else {
            statusMessageET.setEnabled(true);
            statusMessageET.clearFocus();
        }
        setUserStatus();
        saveUserStateToPrefs();
    }

    private void setUserStatus() {
        Status status = user.getStatus();
        status.setStatusMessage(statusMessageET.getText().toString());
        status.setShowLocation(showLocationSwitch.isChecked());
        status.setAutoChange(autoChangeStatusSwitch.isChecked());
        if(!autoChangeStatusSwitch.isChecked()){
            status.setLineAvailable(availableForCallRadioBtn.isChecked());
            status.setNetworkUnlimited(networkUnlimitedRadioBtn.isChecked());
            status.setBatteryNormal(batteryFullRadioBtn.isChecked());
            status.setSoundModeNormal(soundModeNormalRadioBtn.isChecked());
        }
        Log.d("Status", status.toString());
    }

    private void saveUserStateToPrefs() {
        Status status = user.getStatus();
        prefs.setAutoChangeStatus(status.isAutoChange(), getContext());
        prefs.setAvailableForCall(status.isLineAvailable(), getContext());
        prefs.setBatteryStateNormal(status.isBatteryNormal(), getContext());
        prefs.setNetworkUnlimited(status.isNetworkUnlimited(), getContext());
        prefs.setShowLocation(status.isShowLocation(), getContext());
        prefs.setSoundModeNormal(status.isSoundModeNormal(), getContext());
        prefs.setStatusMessage(status.getStatusMessage(), getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
