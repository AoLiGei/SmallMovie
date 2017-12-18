package com.happy.bwiesample.helper;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by 红玫瑰 on 2017/12/12.
 */

public class RetrofitHelper {
    private final String TAG="httpRetrofit";
    private Retrofit videoRetrofit;
    private Object VideoObject=new Object();
    private Retrofit GanksRetrofit;
    private Object ganksObject=new Object();
    public Retrofit getVideoRetrofitInstance(){
        if(videoRetrofit==null){
            synchronized (VideoObject){
                if(videoRetrofit==null){
                    HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.d(TAG, "网络请求信息: "+message);
                        }
                    });

                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    OkHttpClient client=new OkHttpClient.Builder()
                            .addInterceptor(httpLoggingInterceptor)
                            .readTimeout(5000, TimeUnit.MILLISECONDS)
                            .connectTimeout(5000,TimeUnit.MILLISECONDS)
                            .build();
                    return videoRetrofit=new Retrofit.Builder()
                            .client(client)
                            .baseUrl(API.getVideoBaseUrl())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return videoRetrofit;
    }
    public Retrofit getGanksRetrofitInstance(){
        if(GanksRetrofit==null){
            synchronized (ganksObject){
                if(GanksRetrofit==null){
                    HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {
                            Log.d(TAG, "网络请求信息: "+message);
                        }
                    });

                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                    OkHttpClient client=new OkHttpClient.Builder()
                            .addInterceptor(httpLoggingInterceptor)
                            .readTimeout(5000, TimeUnit.MILLISECONDS)
                            .connectTimeout(5000,TimeUnit.MILLISECONDS)
                            .build();
                    return GanksRetrofit=new Retrofit.Builder()
                            .client(client)
                            .baseUrl(API.getGankBaseUrl())
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return GanksRetrofit;
    }

    public <T>T getVideoNetClass(Class<T> cla){
        return getVideoRetrofitInstance().create(cla);
    }
    public <T>T getGanksNetClass(Class<T> cla){
        return getVideoRetrofitInstance().create(cla);
    }
}
