package com.happy.bwiesample.mvp.view.activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseActivity;
import com.happy.bwiesample.mvp.view.fragment.VRImgFragment;
import com.happy.bwiesample.mvp.view.fragment.VRVideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/18
 * @Time 20:30
 */

public class VRActivity extends BaseActivity {

    private TabLayout vr_tab;
    private ViewPager vr_vp;
    private List<Fragment> fragments;

    @Override
    public int setLayout() {
        return R.layout.activity_vr;
    }

    @Override
    public void initView() {
        super.initView();
        vr_tab = findViewById(R.id.vr_tab);
        vr_vp = findViewById(R.id.vr_vp);
        vr_tab.setTabMode(TabLayout.MODE_FIXED);
        vr_tab.setupWithViewPager(vr_vp);
        ImageView iv_return=findViewById(R.id.vr_iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        fragments = new ArrayList<>();
        fragments.add(new VRImgFragment());
        fragments.add(new VRVideoFragment());

        vr_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                if(position==0){
                    return "VR全景图";
                }else {
                    return "VR视频";
                }

            }
        });
    }
}
