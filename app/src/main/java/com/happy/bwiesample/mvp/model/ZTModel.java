package com.happy.bwiesample.mvp.model;

import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.helper.RetrofitHelper;
import com.happy.bwiesample.http.IVideo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

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

    public void getTypeData(final GetTypeData getTypeData){
      helper.getVideoNetClass(IVideo.class).getHomePage().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoHttpResponse<VideoRes>>() {
                    @Override
                    public void onNext(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                        getTypeData.getTypeData(videoResVideoHttpResponse);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });;
    }

    public interface GetTypeData{
        void getTypeData(VideoHttpResponse<VideoRes> videoResVideoHttpResponse);
    }
}
