package com.dev.sdv.contactstatus.utils;

import android.content.Context;
import android.content.SharedPreferences;

interface Prefs {

    void setStatusSavedInPrefs(boolean changed, Context context);

    boolean isStatusSavedInPrefs(Context context);

    SharedPreferences.Editor getEditor(String prefs, Context context);

    interface Status {

        void setStatusId(String id, Context context);

        String getStatusId(Context context);

        void setAutoChangeStatus(boolean autoChange, Context context);

        boolean isAutoChangeStatus(Context context);

        void setShowLocation(boolean show, Context context);

        boolean isShowLocation(Context context);

        void setFreeLine(boolean available, Context context);

        boolean isFreeLine(Context context);

        void setBatteryStateNormal(boolean normal, Context context);

        boolean isBatteryStateNormal(Context context);

        void setNetworkUnlimited(boolean unlimited, Context context);

        boolean isNetworkUnlimited(Context context);

        void setNetworkFast(boolean fast, Context context);

        boolean isNetworkFast(Context context);

        void setSoundModeNormal(boolean normal, Context context);

        boolean isSoundModeNormal(Context context);

        void setStatusMessage(String message, Context context);

        String getStatusMessage(Context context);

        void setLatitude(long latitude, Context context);

        long getLatitude(Context context);

        void setLongitude(long longitude, Context context);

        long getLongitude(Context context);
    }
}
