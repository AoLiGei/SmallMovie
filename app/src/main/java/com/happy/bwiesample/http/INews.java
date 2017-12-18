package com.happy.bwiesample.http;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * Created by 红玫瑰 on 2017/12/13.
 */

public interface INews {
    @GET("/")
    Flowable<String> getNews();
}
