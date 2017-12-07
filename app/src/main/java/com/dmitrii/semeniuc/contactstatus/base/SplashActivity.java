package com.dmitrii.semeniuc.contactstatus.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.dmitrii.semeniuc.contactstatus.R;
import com.dmitrii.semeniuc.contactstatus.auth.AuthActivity;
import es.dmoral.toasty.Toasty;

public class SplashActivity extends BaseActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setStatusBarColor(ContextCompat.getColor(this, R.color.blue_800));
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, AuthActivity.class));
            finish();
        }, 2000);
        configureToast();
    }

    private void configureToast(){
        Toasty.Config.getInstance()
            .setErrorColor(getResources().getColor(R.color.red_800))
            .setInfoColor(getResources().getColor(R.color.colorAccent))
            .setSuccessColor(getResources().getColor(R.color.green_800))
            .setWarningColor(getResources().getColor(R.color.red_800))
            .setTextColor(getResources().getColor(android.R.color.white))
            .apply();
    }
}
