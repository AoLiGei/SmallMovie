package com.happy.bwiesample.mvp.view.fragment;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.VrImageItem;
import com.happy.bwiesample.entry.VrVideoBean;
import com.happy.bwiesample.mvp.presenter.VRPresenter;
import com.happy.bwiesample.mvp.view.VRView;
import com.happy.bwiesample.mvp.view.adapter.VrImgAdapter;
import com.happy.bwiesample.mvp.view.adapter.VrVideoAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 20:49
 */

public class VRVideoFragment extends BaseMvpFragment<VRPresenter> implements VRView{
    @Inject
    Context context;
    private RecyclerView rcView;
    @Override
    public int setLayout() {
        return R.layout.fragment_vrvideo;
    }
    @Override
    public void inject() {
        getFragmentComponent().inject(this);
    }
    public void initView() {
        super.initView();

        rcView = view.findViewById(R.id.vrvideo_rcView);
        rcView.setLayoutManager(new GridLayoutManager(context,2));
    }

    @Override
    public void initData() {
        super.initData();
        p.setVrVideo();
    }

    @Override
    public void showVrImg(List<VrImageItem> datas) {
    }
    @Override
    public void showVrVideo(List<VrVideoBean.ContentBean> datas) {
        rcView.setAdapter(new VrVideoAdapter(context,datas));
    }
}
