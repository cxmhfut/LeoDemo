package com.hfut.leodemo.app;

import android.app.Application;

public class LeoDemoApplication extends Application {

    public static LeoDemoApplication instance;

    public static LeoDemoApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
