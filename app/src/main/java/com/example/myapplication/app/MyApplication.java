package com.example.myapplication.app;

import android.app.AlarmManager;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LifecycleObserver;
import androidx.multidex.MultiDexApplication;

import com.example.myapplication.Helpers.PrefManager;
import com.jakewharton.threetenabp.AndroidThreeTen;




public class MyApplication extends MultiDexApplication implements LifecycleObserver {
    static { AppCompatDelegate.setCompatVectorFromResourcesEnabled(true); }

    PrefManager prefManager;
    private static final String LOGTAG ="MYAPPLICATION";


    private static com.example.myapplication.app.MyApplication mContext;


    private static long cacheFile = 2 * 1024 * 1024;

    private AlarmManager alarmManager;

    boolean isForeground = false;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        prefManager = PrefManager.getInstance();
        AndroidThreeTen.init(this);
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //Force fully signing out the user on who already logged in without oAuth

    }
    public static MyApplication getContext() {
        return mContext;
    }
//    public static void setToroConfig(Config config){
//        toroConfig = config;
//        exoCreator = ToroExo.with(MyApplication.getContext()).getCreator(toroConfig);
//    }
//    public static Config getToroConfig(){
//        return toroConfig;
//    }



}
