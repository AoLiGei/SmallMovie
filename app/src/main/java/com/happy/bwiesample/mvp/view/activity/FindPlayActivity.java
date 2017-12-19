package com.happy.bwiesample.mvp.view.activity;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.mvp.presenter.FindPlayPresenter;

/**
 * 作者： 方冬冬
 * 时间： 2017/12/19 19:32
 * 功能：
 */

public class FindPlayActivity extends BaseMvpActivity<FindPlayPresenter> {
    @Override
    public void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_find_play;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
    }
}
