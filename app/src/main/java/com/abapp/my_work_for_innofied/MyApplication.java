package com.abapp.my_work_for_innofied;

import android.app.Activity;
import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

import com.abapp.my_work_for_innofied.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class MyApplication extends Application implements HasActivityInjector {

    private static MyApplication sInstance;


    public static MyApplication getAppContext() {
        return sInstance;
    }



    private static synchronized void setInstance(MyApplication app) {
        sInstance = app;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
