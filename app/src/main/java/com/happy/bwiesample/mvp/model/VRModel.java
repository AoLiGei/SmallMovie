package com.happy.bwiesample.mvp.model;

import com.google.gson.Gson;
import com.happy.bwiesample.entry.VrImageItem;
import com.happy.bwiesample.entry.VrVideoBean;
import com.happy.bwiesample.helper.VRApiHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Response;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 20:28
 */

public class VRModel {
    @Inject
    public VRModel(){

    }

    public List<VrImageItem> getVrImgDatas(){
        return VRApiHelper.getImageItems();
    }

    public void setVrvideo(final VrVideoListener listener){
        OkGo.get(VRApiHelper.URL_Query)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Gson gson = new Gson();
                        VrVideoBean vrVideoBean = gson.fromJson(s, VrVideoBean.class);
                        listener.setVrVideo(vrVideoBean.getContent());
                    }
                });
    }
    public interface VrVideoListener{
        void setVrVideo(List<VrVideoBean.ContentBean> datas);
    }
}
