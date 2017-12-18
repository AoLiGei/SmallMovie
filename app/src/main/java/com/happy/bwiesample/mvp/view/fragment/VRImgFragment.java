package com.happy.bwiesample.mvp.view.fragment;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.mvp.presenter.VRPresenter;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 20:49
 */

public class VRImgFragment extends BaseMvpFragment<VRPresenter> {
    @Override
    public int setLayout() {
        return R.layout.fragment_vrimg;
    }

    @Override
    public void inject() {

        getFragmentComponent().inject(this);
    }
}
