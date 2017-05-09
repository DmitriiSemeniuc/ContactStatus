package com.dev.sdv.contactstatus;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;

import com.dev.sdv.contactstatus.auth.AuthActivity;
import com.dev.sdv.contactstatus.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setStatusBarColor(ContextCompat.getColor(this, R.color.blue_800));
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override public void run() {
                startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                finish();
            }
        }, 2000);
    }
}
