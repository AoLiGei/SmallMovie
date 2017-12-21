package com.happy.bwiesample.mvp.model;

import com.happy.bwiesample.entry.RecommendBean;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.entry.VideoType;
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

public class ZTModel {
    @Inject
    RetrofitHelper helper;

    @Inject
    public ZTModel(){

    }

    public Flowable<VideoHttpResponse<VideoRes>>getTypeData(){
        return helper.getVideoNetClass(IVideo.class).getHomePage();
    }
}
