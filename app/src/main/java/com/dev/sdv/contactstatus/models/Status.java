package com.dev.sdv.contactstatus.models;

import android.location.Location;

public class Status {

    long id;
    boolean autoChange;
    boolean showLocation;
    Location location;
    boolean lineAvailable;
    boolean batteryNormal;
    boolean networkUnlimited;
    boolean soundModeNormal;
    String statusMessage;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isLineAvailable() {
        return lineAvailable;
    }

    public void setLineAvailable(boolean lineAvailable) {
        this.lineAvailable = lineAvailable;
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

    @Override public String toString() {
        return "Status{" +
                "id=" + id +
                ", autoChange=" + autoChange +
                ", showLocation=" + showLocation +
                ", location=" + location +
                ", lineAvailable=" + lineAvailable +
                ", batteryNormal=" + batteryNormal +
                ", networkUnlimited=" + networkUnlimited +
                ", soundModeNormal=" + soundModeNormal +
                ", statusMessage='" + statusMessage + '\'' +
                '}';
    }
}
