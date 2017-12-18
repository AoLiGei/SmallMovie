package com.happy.bwiesample.helper;

import android.content.Context;
import android.widget.Toast;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 14:03
 */

public class ToastHelper {
    private Context mContext;
    private Toast mToast;
    public ToastHelper(Context context)
    {
        this.mContext=context;
        mToast=new Toast(context);
    }

    public static boolean isShow = true;

    /**
     * 短时间显示Toast
     * @param message
     */
    public void showShort( CharSequence message)
    {
        if (isShow)
            mToast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短时间显示Toast
     * @param message
     */
    public void showShort(int message)
    {
        if (isShow)
            mToast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     * @param message
     */
    public void showLong(CharSequence message)
    {
        if (isShow)
            mToast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     * @param message
     */
    public void showLong( int message)
    {
        if (isShow)
            mToast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     * @param message
     * @param duration
     */
    public void show( CharSequence message, int duration)
    {
        if (isShow)
            mToast.makeText(mContext, message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     * @param message
     * @param duration
     */
    public void show(int message, int duration)
    {
        if (isShow)
            mToast.makeText(mContext, message, duration).show();
    }
}
