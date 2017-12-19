package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.mvp.model.FindPlayModel;
import com.happy.bwiesample.mvp.view.fragment.FindPlayView;

import javax.inject.Inject;

/**
 * 作者： 方冬冬
 * 时间： 2017/12/19 19:34
 * 功能：
 */

public class FindPlayPresenter extends BasePresenter<FindPlayView,FindPlayModel> {

    @Inject
    public FindPlayPresenter() {
    }

    @Inject
    FindPlayModel findPlayModel;






}
