package com.happy.bwiesample.di.component;

import android.content.Context;

import com.happy.bwiesample.helper.DbRegistry;
import com.happy.bwiesample.helper.GlideHelper;
import com.happy.bwiesample.helper.LogHelper;
import com.happy.bwiesample.helper.NetWorkHelper;
import com.happy.bwiesample.helper.RetrofitHelper;
import com.happy.bwiesample.di.moudle.AppMoudle;
import com.happy.bwiesample.helper.SDCardHelper;
import com.happy.bwiesample.helper.SPHelper;
import com.happy.bwiesample.helper.ScreenHelper;
import com.happy.bwiesample.helper.ToastHelper;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Provides;

/**
 * Created by 红玫瑰 on 2017/12/12.
 */
@Singleton
@Component(modules = AppMoudle.class)
public interface AppComponent {
    //提供全局上下文
    Context getContext();

    //提供retrofit帮助类
    RetrofitHelper getHelper();

    //提供Glide帮助类
    GlideHelper getGlideHelper();

    //提供网络帮助类
    NetWorkHelper getNetWorkHelper();

    //提供sp
    SPHelper getSPHelper();

    //提供数据库帮助
    DbRegistry getDbRegistry();

    //提供log帮助类
    LogHelper getLogHelper();

    //提供Toast帮助类
    ToastHelper getToastHelper();

    //提供屏幕帮助类
    ScreenHelper getScreenHelper();

    //提供sd卡帮助类
    SDCardHelper getSDCardHelper();
}
