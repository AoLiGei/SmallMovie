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
 * @Date 2017/12/20
 * @Time 14:22
 */

public class VideoPlayModel {
    @Inject
    public VideoPlayModel(){

    }

    @Inject
    RetrofitHelper retrofitHelper;
    public void getMovieRes(String mediaId, final MovieResListener listener){
        retrofitHelper.getVideoNetClass(IVideo.class)
                .getVideoDetail(mediaId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoHttpResponse<VideoRes>>() {
                    @Override
                    public void onNext(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                        listener.getMovieRes(videoResVideoHttpResponse);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public interface MovieResListener{
        void getMovieRes(VideoHttpResponse<VideoRes> videoRes);
    }
}
