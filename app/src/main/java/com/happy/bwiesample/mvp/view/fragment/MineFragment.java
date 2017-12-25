package com.happy.bwiesample.mvp.view.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.view.MineView;
import com.happy.bwiesample.mvp.view.adapter.PopGvAdapter;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class MineFragment extends BaseMvpFragment<JXPresenter> implements MineView {
    @Inject
    Context context;
    private TextView tv_them;
    private RelativeLayout rl_them;
    private RelativeLayout relativeLayout;

    @Override
    public int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void inject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void initView() {
        super.initView();
        relativeLayout = view.findViewById(R.id.rl_them);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupWindow popupWindow = new PopupWindow(getContext());
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                View layout = LayoutInflater.from(getContext()).inflate(R.layout.layout_popupwindow,null);
                GridView gridView= layout.findViewById(R.id.popwindow_gv);
                gridView.setAdapter(new PopGvAdapter(getContext()));
                popupWindow.setContentView(layout);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
                popupWindow.setOutsideTouchable(false);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER,0,0);



            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }

}
