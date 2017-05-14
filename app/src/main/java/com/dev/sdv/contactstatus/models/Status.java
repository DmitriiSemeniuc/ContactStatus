package com.dev.sdv.contactstatus.models;

import android.location.Location;

import java.util.Date;

public class Status {

    private boolean autoChange;
    private  boolean showLocation;
    private Location location;
    private boolean freeLine;
    private boolean batteryNormal;
    private boolean networkUnlimited;
    private boolean soundModeNormal;
    private String statusMessage;
    private Date lastTimeUpdated;
    private String uid;

    public Status() {
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public Date getLastTimeUpdated() {
        return lastTimeUpdated;
    }

    public void setLastTimeUpdated(Date lastTimeUpdated) {
        this.lastTimeUpdated = lastTimeUpdated;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override public String toString() {
        return "Status{" +
                "autoChange=" + autoChange +
                ", showLocation=" + showLocation +
                ", location=" + location +
                ", freeLine=" + freeLine +
                ", batteryNormal=" + batteryNormal +
                ", networkUnlimited=" + networkUnlimited +
                ", soundModeNormal=" + soundModeNormal +
                ", statusMessage='" + statusMessage + '\'' +
                ", lastTimeUpdated=" + lastTimeUpdated +
                '}';
    }
}
