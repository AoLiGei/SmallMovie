package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.entry.RecommendBean;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.mvp.model.JXModel;
import com.happy.bwiesample.mvp.view.JXView;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:07
 */

public class JXPresenter extends BasePresenter<JXView,JXModel>{
    @Inject
    JXModel model;
    @Inject
    public JXPresenter(){

    }

    public void showVideoData(){
       model.getVideoData().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(new DisposableSubscriber<VideoHttpResponse<VideoRes>>() {
                   @Override
                   public void onNext(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                       getView().setVideoData(videoResVideoHttpResponse);
                   }

                   @Override
                   public void onError(Throwable t) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }
}
