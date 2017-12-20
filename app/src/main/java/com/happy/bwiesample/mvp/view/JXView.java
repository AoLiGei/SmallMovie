package com.happy.bwiesample.mvp.view;

import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:06
 */

public interface JXView {

    void setVideoData(VideoHttpResponse<VideoRes> videoResVideoHttpResponse);
}
