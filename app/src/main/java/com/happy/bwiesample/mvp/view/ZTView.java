package com.happy.bwiesample.mvp.view;

import com.happy.bwiesample.entry.RecommendBean;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.entry.VideoType;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:06
 */

public interface ZTView {
    void showZTList(VideoHttpResponse<VideoRes> videoResVideoHttpResponse);
}
