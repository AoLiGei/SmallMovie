package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.mvp.model.VideoPlayModel;
import com.happy.bwiesample.mvp.view.VideoPlayView;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/20
 * @Time 14:23
 */

public class VideoPlayPresenter extends BasePresenter<VideoPlayView,VideoPlayModel> {
    @Inject
    public VideoPlayPresenter(){

    }

    public void getVideoRes(String mediaId){
        model.getMovieRes(mediaId, new VideoPlayModel.MovieResListener() {
            @Override
            public void getMovieRes(VideoHttpResponse<VideoRes> videoRes) {
                getView().setMovieRes(videoRes);
            }
        });
    }
}
