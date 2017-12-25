package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.mvp.model.CommentModel;
import com.happy.bwiesample.mvp.view.CommentView;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/24
 * @Time 19:56
 */

public class CommentPresenter extends BasePresenter<CommentView,CommentModel> {
    @Inject
    public CommentPresenter(){}

    public void getVideoRes(String mediaId){
        model.getVideoRes(new CommentModel.CommentListener() {
            @Override
            public void getVideoRes(VideoHttpResponse<VideoRes> videoRes) {
                getView().showComment(videoRes);
            }
        },mediaId);
    }

}
