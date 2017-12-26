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

public class JXModel {

    @Inject
    public JXModel(){
    }
    @Inject
    RetrofitHelper helper;

    public void getVideoData(final GetHomePage getHomePage){
         helper.getVideoNetClass(IVideo.class).getHomePage()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoHttpResponse<VideoRes>>() {
                    @Override
                    public void onNext(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                       getHomePage.getHomePageData(videoResVideoHttpResponse);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public interface GetHomePage{
        void getHomePageData(VideoHttpResponse<VideoRes> videoResVideoHttpResponse);
    }
}
