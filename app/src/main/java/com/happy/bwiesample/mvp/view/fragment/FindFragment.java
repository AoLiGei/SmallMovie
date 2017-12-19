package com.happy.bwiesample.mvp.view.fragment;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.view.FindView;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class FindFragment extends BaseMvpFragment<JXPresenter> implements FindView {
    @Override
    public int setLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void inject() {

        getFragmentComponent().inject(this);
    }

}
