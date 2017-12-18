package com.happy.bwiesample.app;

import android.app.Application;

import com.happy.bwiesample.di.component.AppComponent;
import com.happy.bwiesample.di.component.DaggerAppComponent;
import com.happy.bwiesample.di.moudle.AppMoudle;

/**
 * Created by 红玫瑰 on 2017/12/12.
 */

public class MyApp extends Application {

    private AppComponent appComponent;
    private static MyApp sApplication;


    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;
        appComponent = DaggerAppComponent.builder().appMoudle(new AppMoudle(getInstance())).build();
    }

    public static MyApp getInstance() {
        return sApplication;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
