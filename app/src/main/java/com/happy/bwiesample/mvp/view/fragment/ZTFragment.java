package com.happy.bwiesample.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.helper.RetrofitHelper;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.presenter.ZTPresenter;
import com.happy.bwiesample.mvp.view.ZTView;
import com.happy.bwiesample.mvp.view.adapter.SpecialRecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class ZTFragment extends BaseMvpFragment<ZTPresenter> implements ZTView {
    @Inject
    Context context;
    private RecyclerView special_rv;

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
        SpecialRecyclerAdapter adapter = new SpecialRecyclerAdapter(context,typeList);
        special_rv.setLayoutManager(new GridLayoutManager(context,2));
        special_rv.setAdapter(adapter);
    }

    @Override
    public void initView() {
        super.initView();
        special_rv = view.findViewById(R.id.special_rv);
    }


    @Override
    public void initData() {
        super.initData();
        p.getCommit();
    }
}
