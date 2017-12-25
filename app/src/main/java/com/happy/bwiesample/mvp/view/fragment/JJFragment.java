package com.happy.bwiesample.mvp.view.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseFragment;
import com.happy.bwiesample.entry.VideoRes;

import java.io.Serializable;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/23
 * @Time 9:25
 */

public class JJFragment extends BaseFragment {

    private TextView desc_view;
    private ImageView iv_expan;
    private int defultLine=2;
    private TextView tv_expan;
    private RecyclerView rcView;
    private boolean isExpan=false;

    @Override
    public int setLayout() {
        return R.layout.fragment_jj;
    }
    @Override
    public void initView() {
        super.initView();
        desc_view = view.findViewById(R.id.description_view);
        iv_expan = view.findViewById(R.id.expand_view);
        tv_expan = view.findViewById(R.id.expand_tv);
        rcView = view.findViewById(R.id.jjFragment_rcview);

        iv_expan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(desc_view.getText().toString().equals("暂无信息")){

                    return;
                }
                if(isExpan){
                    tv_expan.setText("展开");
                    ObjectAnimator animator = ObjectAnimator.ofFloat(iv_expan, "rotation", 180f, 360f);
                    animator.setDuration(500);
                    animator.setRepeatCount(0);
                    animator.start();
                    desc_view.setHeight(defultLine*desc_view.getLineHeight());
                    isExpan=false;
                }else {
                    tv_expan.setText("收起");
                    ObjectAnimator animator = ObjectAnimator.ofFloat(iv_expan, "rotation", 0f, 180f);
                    animator.setDuration(500);
                    animator.setRepeatCount(0);
                    animator.start();
                    desc_view.setHeight(desc_view.getLineCount()*desc_view.getLineHeight());
                    isExpan=true;
                }

            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        VideoRes videoRes = (VideoRes) bundle.getSerializable("videoRes");
        if(videoRes!=null){
            desc_view.setHeight(desc_view.getLineHeight() * defultLine);
            desc_view.setText("  简介："+videoRes.description);
        }

    }

    public static JJFragment getJJFragmentInstance(VideoRes videoRes){

        Bundle bundle =new Bundle();
        bundle.putSerializable("videoRes",videoRes);
        JJFragment fragment = new JJFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
