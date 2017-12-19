package com.happy.bwiesample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happy.bwiesample.di.component.FragmentComponent;

import javax.inject.Inject;

/**
 * Created by 红玫瑰 on 2017/12/12.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends Fragment {
    public View view;
    @Inject
    public P p;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null){
            view=inflater.inflate(setLayout(),null);
            inject();
            p.attachView(this);
            initView();


        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public abstract int setLayout();
    public abstract void inject();
    public void initView(){

    }
    public void initData(){

    }

    protected FragmentComponent getFragmentComponent(){
        return FragmentComponent.getFragmentComponentInstance();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        p.dettachView();
    }
}
