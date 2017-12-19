package com.happy.bwiesample.mvp.view;

import com.happy.bwiesample.entry.VrImageItem;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 20:29
 */

public interface VRView {
    //展示vr图片列表页

    void showVrImg(List<VrImageItem> datas);
}
