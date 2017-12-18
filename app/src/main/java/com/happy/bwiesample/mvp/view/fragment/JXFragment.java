package com.happy.bwiesample.mvp.view.fragment;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.view.JXView;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class JXFragment extends BaseMvpFragment<JXPresenter> implements JXView {
    @Override
    public int setLayout() {
        return R.layout.fragment_jx;
    }

    @Override
    public void inject() {

        getFragmentComponent().inject(this);

    }

}
