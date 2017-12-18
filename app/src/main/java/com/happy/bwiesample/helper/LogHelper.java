package com.happy.bwiesample.helper;

import android.content.Context;
import android.util.Log;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 14:01
 */

public class LogHelper {
    public LogHelper()
    {
    }

    public static boolean isDebug = true;// 是否需要打印bug，可以在application的onCreate函数里面初始化
    private static final String TAG = "way";

    // 下面四个是默认tag的函数
    public void i(String msg)
    {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public void d(String msg)
    {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public void e(String msg)
    {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public void v(String msg)
    {
        if (isDebug)
            Log.v(TAG, msg);
    }

    // 下面是传入自定义tag的函数
    public void i(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public void d(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public void e(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }

    public void v(String tag, String msg)
    {
        if (isDebug)
            Log.i(tag, msg);
    }
}
