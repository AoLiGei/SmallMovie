package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.mvp.model.JXModel;
import com.happy.bwiesample.mvp.view.JXView;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:07
 */

public class JXPresenter extends BasePresenter<JXView,JXModel>{

    @Inject
    public JXPresenter(){

    }

    public void showVideoData(){
        model.getVideoData(new JXModel.GetHomePage() {
            @Override
            public void getHomePageData(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                getView().setVideoData(videoResVideoHttpResponse);
            }
        });
    }
}
