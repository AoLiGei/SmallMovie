package com.happy.bwiesample.mvp.model;

import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.helper.RetrofitHelper;
import com.happy.bwiesample.http.IVideo;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:04
 */

public class FindModel {

    final String catalogId = "402834815584e463015584e539330016";

    @Inject
    public FindModel(){
        
    }

    @Inject
    RetrofitHelper retrofitHelper;


    public void getFindData(final SetFindDataListener setFindDataListener){
        Flowable<VideoHttpResponse<VideoRes>> homePage = retrofitHelper.getVideoNetClass(IVideo.class).getVideoList(catalogId,30+"");
        homePage.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoHttpResponse<VideoRes>>() {
                    @Override
                    public void onNext(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                        if (videoResVideoHttpResponse != null) {
                            setFindDataListener.findData(videoResVideoHttpResponse);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//    SetFindDataListener setFindDataListener;

    public interface SetFindDataListener{
        void findData(VideoHttpResponse<VideoRes> videoResVideoHttpResponse);
    }


}
