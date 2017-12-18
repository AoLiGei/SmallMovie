package com.happy.bwiesample.di.moudle;

import android.content.Context;

import com.happy.bwiesample.app.MyApp;

import com.happy.bwiesample.helper.DbRegistry;
import com.happy.bwiesample.helper.GlideHelper;
import com.happy.bwiesample.helper.LogHelper;
import com.happy.bwiesample.helper.NetWorkHelper;
import com.happy.bwiesample.helper.RetrofitHelper;
import com.happy.bwiesample.helper.SDCardHelper;
import com.happy.bwiesample.helper.SPHelper;
import com.happy.bwiesample.helper.ScreenHelper;
import com.happy.bwiesample.helper.ToastHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 红玫瑰 on 2017/12/12.
 */

@Module
public class AppMoudle {
    private MyApp mApplication;

    public AppMoudle(MyApp mApplication) {
        this.mApplication=mApplication;
    }


    //提供全局上下文
    @Provides
    Context getAppContext(){
        return mApplication.getApplicationContext();
    }

    //提供Application
    @Provides
    MyApp getApp(){
        return mApplication;
    }
    //提供RetrofitHelper
    @Singleton
    @Provides
    RetrofitHelper getHelper(){
        return new RetrofitHelper();
    }
    //提供GlideHelper
    @Singleton
    @Provides
    GlideHelper getGlideHelper(Context context){
        return new GlideHelper(context);
    }

    //提供网络帮助类
    @Singleton
    @Provides
    NetWorkHelper getNetWorkHelper(Context context){
        return new NetWorkHelper(context);
    }

    //提供SharedPreferences帮助类
    @Singleton
    @Provides
    SPHelper getSPHelper(Context context){
        return new SPHelper(context);
    }

    //提供db帮助类
    @Singleton
    @Provides
    DbRegistry getDbRegistry(Context context){
        return new DbRegistry(context);
    }

    //提供log帮助类
    @Singleton
    @Provides
    LogHelper getLogHelper(){
        return new LogHelper();
    }
    //提供Toast帮助类
    @Singleton
    @Provides
    ToastHelper getToastHelper(Context context){
        return new ToastHelper(context);
    }
    //提供屏幕帮助类
    @Singleton
    @Provides
    ScreenHelper getScreenHelper(Context context){
        return new ScreenHelper(context);
    }
    //提供sd卡帮助类
    @Singleton
    @Provides
    SDCardHelper getSDCardHelper(){
        return new SDCardHelper();
    }



}
