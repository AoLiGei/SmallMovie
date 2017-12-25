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
import android.widget.AdapterView;
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
import com.happy.bwiesample.wigdet.TheamCircleView;

import org.w3c.dom.Text;

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
    private PopupWindow popupWindow;

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
                popupWindow = new PopupWindow(getContext());
                popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                View layout = LayoutInflater.from(getContext()).inflate(R.layout.layout_popupwindow, null);
                GridView gridView = layout.findViewById(R.id.popwindow_gv);
                TextView tv_dismiss = layout.findViewById(R.id.tv_dismiss);
                TextView tv_click = layout.findViewById(R.id.tv_click);
                tv_dismiss.setOnClickListener(this);
                tv_click.setOnClickListener(this);
                PopGvAdapter popGvAdapter = new PopGvAdapter(getContext());
                gridView.setAdapter(popGvAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getContext(), "应用了主题" + position, Toast.LENGTH_SHORT).show();
                        popupWindow.dismiss();
                    }
                });

                popupWindow.setContentView(layout);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
                popupWindow.setOutsideTouchable(false);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(relativeLayout, Gravity.CENTER, 0, 0);

            }
        });
    }

    @Override
    public void initData() {
        super.initData();
    }


}
