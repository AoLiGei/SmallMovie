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
 * @Date 2017/12/24
 * @Time 19:56
 */

public class CommentModel {
    @Inject
    public CommentModel(){}
    @Inject
    RetrofitHelper helper;
    public void getVideoRes(final CommentListener listener, String media){
        IVideo iVideo = helper.getVideoNetClass(IVideo.class);
        iVideo.getCommentList(media,null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoHttpResponse<VideoRes>>() {
                    @Override
                    public void onNext(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {

                        listener.getVideoRes(videoResVideoHttpResponse);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public interface CommentListener{
        void getVideoRes(VideoHttpResponse<VideoRes> videoRes);
    }
}
