package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.entry.VrVideoBean;
import com.happy.bwiesample.mvp.model.VRModel;
import com.happy.bwiesample.mvp.view.VRView;

import java.util.List;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 20:28
 */

public class VRPresenter extends BasePresenter<VRView,VRModel> {

    @Inject
    public VRPresenter(){

    }

    public void setVrImg(){
        getView().showVrImg(model.getVrImgDatas());
    }
    public void setVrVideo(){
        model.setVrvideo(new VRModel.VrVideoListener() {
            @Override
            public void setVrVideo(List<VrVideoBean.ContentBean> datas) {
                getView().showVrVideo(datas);
            }
        });
    }
}
