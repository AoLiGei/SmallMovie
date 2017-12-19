package com.happy.bwiesample.mvp.model;

import com.happy.bwiesample.entry.VrImageItem;
import com.happy.bwiesample.helper.VRApiHelper;

import java.util.List;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 20:28
 */

public class VRModel {
    @Inject
    public VRModel(){

    }

    public List<VrImageItem> getVrImgDatas(){
        return VRApiHelper.getImageItems();
    }
}
