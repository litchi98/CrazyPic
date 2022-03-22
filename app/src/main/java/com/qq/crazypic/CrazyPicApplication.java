package com.qq.crazypic;

import android.app.Application;

import com.qq.crazypic.utilities.ContextUtil;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class CrazyPicApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ContextUtil.init(this);
    }
}
