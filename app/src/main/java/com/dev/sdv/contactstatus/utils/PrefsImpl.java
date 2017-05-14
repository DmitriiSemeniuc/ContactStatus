package com.dev.sdv.contactstatus.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefsImpl implements Prefs, Prefs.Status {

    @Override public void setAutoChangeStatus(boolean autoChange, Context context) {
        SharedPreferences.Editor editor = getEditor(Const.STATUS.PREFS, context);
        editor.putBoolean(Const.STATUS.AUTO_CHANGE, autoChange);
        editor.commit();
    }

    @Override public boolean isAutoChangeStatus(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Const.STATUS.PREFS, Context.MODE_PRIVATE);
        return settings.getBoolean(Const.STATUS.AUTO_CHANGE, true);
    }

    @Override public void setShowLocation(boolean show, Context context) {
        SharedPreferences.Editor editor = getEditor(Const.STATUS.PREFS, context);
        editor.putBoolean(Const.STATUS.SHOW_LOCATION, show);
        editor.commit();
    }

    @Override public boolean isShowLocation(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Const.STATUS.PREFS, Context.MODE_PRIVATE);
        return settings.getBoolean(Const.STATUS.SHOW_LOCATION, true);
    }

    @Override public void setAvailableForCall(boolean available, Context context) {
        SharedPreferences.Editor editor = getEditor(Const.STATUS.PREFS, context);
        editor.putBoolean(Const.STATUS.AVAILABLE, available);
        editor.commit();
    }

    @Override public boolean isAvailableForCall(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Const.STATUS.PREFS, Context.MODE_PRIVATE);
        return settings.getBoolean(Const.STATUS.AVAILABLE, true);
    }

    @Override public void setBatteryStateNormal(boolean normal, Context context) {
        SharedPreferences.Editor editor = getEditor(Const.STATUS.PREFS, context);
        editor.putBoolean(Const.STATUS.BATTERY_NORMAL, normal);
        editor.commit();
    }

    @Override public boolean isBatteryStateNormal(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Const.STATUS.PREFS, Context.MODE_PRIVATE);
        return settings.getBoolean(Const.STATUS.BATTERY_NORMAL, true);
    }

    @Override public void setNetworkUnlimited(boolean unlimited, Context context) {
        SharedPreferences.Editor editor = getEditor(Const.STATUS.PREFS, context);
        editor.putBoolean(Const.STATUS.NETWORK, unlimited);
        editor.commit();
    }

    @Override public boolean isNetworkUnlimited(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Const.STATUS.PREFS, Context.MODE_PRIVATE);
        return settings.getBoolean(Const.STATUS.NETWORK, true);
    }

    @Override public void setSoundModeNormal(boolean normal, Context context) {
        SharedPreferences.Editor editor = getEditor(Const.STATUS.PREFS, context);
        editor.putBoolean(Const.STATUS.SOUND_MODE, normal);
        editor.commit();
    }

    @Override public boolean isSoundModeNormal(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Const.STATUS.PREFS, Context.MODE_PRIVATE);
        return settings.getBoolean(Const.STATUS.SOUND_MODE, true);
    }

    @Override public void setStatusMessage(String message, Context context) {
        SharedPreferences.Editor editor = getEditor(Const.STATUS.PREFS, context);
        editor.putString(Const.STATUS.MESSAGE, message);
        editor.commit();
    }

    @Override public String getStatusMessage(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Const.STATUS.PREFS, Context.MODE_PRIVATE);
        return settings.getString(Const.STATUS.MESSAGE, "");
    }

    @Override public SharedPreferences.Editor getEditor(String prefs, Context context) {
        SharedPreferences settings = context.getSharedPreferences(prefs, Context.MODE_PRIVATE);
        return settings.edit();
    }
}
