package com.happy.bwiesample.mvp.model;

import com.happy.bwiesample.entry.RecommendBean;
import com.happy.bwiesample.helper.RetrofitHelper;
import com.happy.bwiesample.http.IVideo;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:04
 */

public class JXModel {

    @Inject
    public JXModel(){
    }
    @Inject
    RetrofitHelper helper;

    public Flowable<RecommendBean> getVideoData(){
        return helper.getVideoRetrofitInstance().create(IVideo.class).getHomePage();
    }
}
