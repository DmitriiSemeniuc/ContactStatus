package com.dev.sdv.contactstatus.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.dev.sdv.contactstatus.base.Authentication;

public class Utils {

    public static String getUserFromPrefs(Context context) {
        SharedPreferences settings = context.getSharedPreferences(
                Const.LOGIN_PREFS, Context.MODE_PRIVATE);
        return settings.getString(Const.USER_TYPE, Authentication.UserType.NONE.toString());
    }

    public static void setUserInPrefs(String userType, Context context) {
        SharedPreferences.Editor editor = getEditor(Const.LOGIN_PREFS, context);
        editor.putString(Const.USER_TYPE, userType);
        editor.commit();
    }

    private static SharedPreferences.Editor getEditor(String prefs, Context context) {
        SharedPreferences settings = context.getSharedPreferences(prefs, Context.MODE_PRIVATE);
        return settings.edit();
    }
}
