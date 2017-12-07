package com.dmitrii.semeniuc.contactstatus.models;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

public class Status {

    private String uid;
    private boolean autoChange;
    private  boolean showLocation;
    private long latitude;
    private long longitude;
    private boolean freeLine;
    private boolean batteryNormal;
    private boolean networkUnlimited;
    private boolean networkFast;
    private boolean soundModeNormal;
    private String statusMessage;
    private long timeStamp;
    private String userName;
    private String userPhotoUrl;
    private String userPhoneNumber;

    public Status() {
    }

    public Status(String uid, boolean autoChange, boolean showLocation, long latitude, long longitude,
                  boolean freeLine, boolean batteryNormal, boolean networkUnlimited, boolean networkFast,
                  boolean soundModeNormal, String statusMessage, String userName, String userPhotoUrl, String userPhoneNumber) {
        this.uid = uid;
        this.autoChange = autoChange;
        this.showLocation = showLocation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.freeLine = freeLine;
        this.batteryNormal = batteryNormal;
        this.networkUnlimited = networkUnlimited;
        this.networkFast = networkFast;
        this.soundModeNormal = soundModeNormal;
        this.statusMessage = statusMessage;
        this.userName = userName;
        this.userPhotoUrl = userPhotoUrl;
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isAutoChange() {
        return autoChange;
    }

    public void setAutoChange(boolean autoChange) {
        this.autoChange = autoChange;
    }

    public boolean isShowLocation() {
        return showLocation;
    }

    public void setShowLocation(boolean showLocation) {
        this.showLocation = showLocation;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public boolean isFreeLine() {
        return freeLine;
    }

    public void setFreeLine(boolean freeLine) {
        this.freeLine = freeLine;
    }

    public boolean isBatteryNormal() {
        return batteryNormal;
    }

    public void setBatteryNormal(boolean batteryNormal) {
        this.batteryNormal = batteryNormal;
    }

    public boolean isNetworkUnlimited() {
        return networkUnlimited;
    }

    public void setNetworkUnlimited(boolean networkUnlimited) {
        this.networkUnlimited = networkUnlimited;
    }

    public boolean isNetworkFast() {
        return networkFast;
    }

    public void setNetworkFast(boolean networkFast) {
        this.networkFast = networkFast;
    }

    public boolean isSoundModeNormal() {
        return soundModeNormal;
    }

    public void setSoundModeNormal(boolean soundModeNormal) {
        this.soundModeNormal = soundModeNormal;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhotoUrl() {
        return userPhotoUrl;
    }

    public void setUserPhotoUrl(String userPhotoUrl) {
        this.userPhotoUrl = userPhotoUrl;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> mapValue = new HashMap<>();
        mapValue.put("uid", uid);
        mapValue.put("autoChange", autoChange);
        mapValue.put("showLocation", showLocation);
        mapValue.put("latitude", latitude);
        mapValue.put("longitude", longitude);
        mapValue.put("freeLine", freeLine);
        mapValue.put("batteryNormal", batteryNormal);
        mapValue.put("networkUnlimited", networkUnlimited);
        mapValue.put("networkFast", networkFast);
        mapValue.put("soundModeNormal", soundModeNormal);
        mapValue.put("statusMessage", statusMessage);
        mapValue.put("userName", userName);
        mapValue.put("userPhotoUrl", userPhotoUrl);
        mapValue.put("userPhoneNumber", userPhoneNumber);
        mapValue.put("timeStamp", ServerValue.TIMESTAMP);
        return mapValue;

    }
}
