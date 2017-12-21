package com.happy.bwiesample.mvp.model;

import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.helper.RetrofitHelper;
import com.happy.bwiesample.http.IVideo;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * Created by 蔡华铎 on 2017/12/19.
 */

public class SearchModel {
    @Inject
    public SearchModel() {
    }
    @Inject
    RetrofitHelper helper;

    public Flowable<VideoHttpResponse<VideoRes>> getSearchVideoList(String keyword){
        return helper.getVideoNetClass(IVideo.class).getVideoListByKeyWord(keyword,null);
    }

    public Flowable<VideoHttpResponse<VideoRes>> getHotVideoList(){
        return helper.getVideoNetClass(IVideo.class).getHomePage();
    }

}
