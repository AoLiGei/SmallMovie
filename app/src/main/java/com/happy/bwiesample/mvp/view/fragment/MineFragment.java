package com.happy.bwiesample.mvp.view.fragment;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.view.MineView;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class MineFragment extends BaseMvpFragment<JXPresenter> implements MineView {
    @Override
    public int setLayout() {
        return R.layout.fragment_mine;
    }
    @Override
    public void inject() {
        getFragmentComponent().inject(this);
    }

}
