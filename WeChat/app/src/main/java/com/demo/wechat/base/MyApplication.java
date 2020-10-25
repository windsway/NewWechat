package com.demo.wechat.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

public class MyApplication extends Application {

    public static ArrayList<Activity> allActivities = new ArrayList<Activity>();
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static void addActivity(Activity activity) {
        allActivities.add(activity);
    }

    public static void delActivity(Activity activity) {
        allActivities.remove(activity);
    }
//    public static Context getContext() {
//        return context;
//    }
}
