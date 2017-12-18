package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.mvp.model.FindModel;
import com.happy.bwiesample.mvp.view.FindView;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:07
 */

public class FindPresenter extends BasePresenter<FindView,FindModel>{
    @Inject
    public FindPresenter(){
        
    }
}
