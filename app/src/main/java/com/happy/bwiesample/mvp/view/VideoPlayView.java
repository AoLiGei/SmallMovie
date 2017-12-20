package com.happy.bwiesample.mvp.view;

import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoInfo;
import com.happy.bwiesample.entry.VideoRes;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/20
 * @Time 14:23
 */

public interface VideoPlayView {
    void setMovieRes(VideoHttpResponse<VideoRes> videoInfo);
}
