package com.happy.bwiesample.mvp.view;

import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;

/**
 * Created by 蔡华铎 on 2017/12/19.
 */

public interface SearchView {

    String getEditText();

    void showSearchData(VideoHttpResponse<VideoRes> videobean);

    void showHotData(VideoHttpResponse<VideoRes> videobean);
}
