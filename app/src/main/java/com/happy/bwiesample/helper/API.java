package com.happy.bwiesample.helper;

/**
 * Created by 红玫瑰 on 2017/12/12.
 */

public class API {


    private static final String debugVideoBaseUrl="http://api.svipmovie.com/front/";
    private static final String relaseVidoeBaseUrl="http://api.svipmovie.com/front/";
    private static final String debugGankBaseUrl="http://api.svipmovie.com/front/";
    private static final String relaseGankBaseUrl="http://api.svipmovie.com/front/";
    public static boolean isRelase;
    //video
    public static String getVideoBaseUrl(){
        return isRelase?relaseVidoeBaseUrl:debugVideoBaseUrl;
    }
    //gank
    public static String getGankBaseUrl(){
        return isRelase?relaseGankBaseUrl:debugGankBaseUrl;
    }

}