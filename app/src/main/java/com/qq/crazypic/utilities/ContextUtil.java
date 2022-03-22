package com.qq.crazypic.utilities;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import java.lang.reflect.Method;

public class ContextUtil {

    private static Application application;

    private ContextUtil() {
    }

    public static void init(Application application) {
        if (application == null) {
            throw new IllegalArgumentException("application can't be null.");
        }
        ContextUtil.application = application;
    }

    public static Context getContext() {
        if (application == null) {
            application = ContextHelper.getApplication();
        }
        if (application == null) {
            throw new IllegalStateException("can't getContext() before init().");
        }
        return application.getApplicationContext();
    }

    public static Application getApplication() {
        if (application == null) {
            application = ContextHelper.getApplication();
        }
        if (application == null) {
            throw new IllegalStateException("can't getApplication() before init().");
        }
        return application;
    }

    private static final class ContextHelper {
        @SuppressLint({"StaticFieldLeak"})
        private static volatile Application context;

        public static Application getApplication() {
            return context;
        }

        private static Object getActivityThread() {
            Object activityThread = null;
            try {
                @SuppressLint("PrivateApi")
                Method method = Class.forName("android.app.ActivityThread")
                        .getMethod("currentActivityThread");
                method.setAccessible(true);
                activityThread = method.invoke(null);
            } catch (Throwable ignored) {

            }
            return activityThread;
        }

        static {
            try {
                Object activityThread = getActivityThread();
                Object application = activityThread.getClass()
                        .getMethod("getApplication").invoke(activityThread);
                context = (Application) application;
            } catch (Throwable ignored) {

            }
        }
    }
}