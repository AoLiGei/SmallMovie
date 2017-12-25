package com.happy.bwiesample.mvp.view;

import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/24
 * @Time 19:56
 */

public interface CommentView {
    void showComment(VideoHttpResponse<VideoRes> videoRes);
}
