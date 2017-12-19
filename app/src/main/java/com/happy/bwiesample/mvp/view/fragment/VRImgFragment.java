package com.happy.bwiesample.mvp.view.fragment;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.VrImageItem;
import com.happy.bwiesample.entry.VrVideoBean;
import com.happy.bwiesample.mvp.presenter.VRPresenter;
import com.happy.bwiesample.mvp.view.VRView;
import com.happy.bwiesample.mvp.view.adapter.VrImgAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 20:49
 */

public class VRImgFragment extends BaseMvpFragment<VRPresenter> implements VRView{

    private RecyclerView rcView;

    @Inject
    Context context;
    @Override
    public int setLayout() {
        return R.layout.fragment_vrimg;
    }
    @Override
    public void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        super.initView();

        rcView = view.findViewById(R.id.vrimg_rcView);
        rcView.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void initData() {
        super.initData();
        p.setVrImg();
    }

    @Override
    public void showVrImg(List<VrImageItem> datas) {
        rcView.setAdapter(new VrImgAdapter(context,datas));

    }

    @Override
    public void showVrVideo(List<VrVideoBean.ContentBean> datas) {

    }
}
