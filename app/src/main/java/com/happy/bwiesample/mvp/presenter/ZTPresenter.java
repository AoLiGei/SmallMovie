package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.mvp.model.ZTModel;
import com.happy.bwiesample.mvp.view.ZTView;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:07
 */

public class ZTPresenter extends BasePresenter<ZTView,ZTModel>{

    @Inject
    public ZTPresenter(){
        
    }

    public void getCommit(){
        model.getTypeData(new ZTModel.GetTypeData() {
            @Override
            public void getTypeData(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                getView().showZTList(videoResVideoHttpResponse);
            }
        });
    }
}
