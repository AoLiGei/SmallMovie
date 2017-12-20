package com.happy.bwiesample.mvp.model;

import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
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

    public Flowable<VideoHttpResponse<VideoRes>> getVideoData(){
        return helper.getVideoRetrofitInstance().create(IVideo.class).getHomePage();
    }
}
