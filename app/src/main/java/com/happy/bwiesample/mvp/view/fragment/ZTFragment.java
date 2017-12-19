package com.happy.bwiesample.mvp.view.fragment;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.presenter.ZTPresenter;
import com.happy.bwiesample.mvp.view.ZTView;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class ZTFragment extends BaseMvpFragment<ZTPresenter> implements ZTView {
    @Override
    public int setLayout() {
        return R.layout.fragment_zt;
    }

    @Override
    public void inject() {

        getFragmentComponent().inject(this);
    }

    @Override
    public void showZTList(List<VideoType> typeList) {

    }
}
