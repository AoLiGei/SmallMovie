package com.happy.bwiesample.mvp.view.activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseActivity;
import com.happy.bwiesample.mvp.view.fragment.FindFragment;
import com.happy.bwiesample.mvp.view.fragment.JXFragment;
import com.happy.bwiesample.mvp.view.fragment.MineFragment;
import com.happy.bwiesample.mvp.view.fragment.ZTFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity{


    private RadioGroup main_rg;
    private FragmentManager fm;
    private List<Fragment> fragments;
    private TextView main_tv_vr;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initData() {
        super.initData();
    }
    @Override
    public void initView() {
        super.initView();

        main_rg = findViewById(R.id.main_rg);
        main_tv_vr = findViewById(R.id.main_tv_vr);

        main_tv_vr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO


                startActivity(new Intent(MainActivity.this,VRActivity.class));
            }
        });
        fm = getSupportFragmentManager();
        fragments=new ArrayList<>();
        fragments.add(new JXFragment());
        fragments.add(new ZTFragment());
        fragments.add(new FindFragment());
        fragments.add(new MineFragment());

        FragmentTransaction t = fm.beginTransaction();
        t.add(R.id.main_frameLayout,fragments.get(0));
        t.add(R.id.main_frameLayout,fragments.get(1));
        t.add(R.id.main_frameLayout,fragments.get(2));
        t.add(R.id.main_frameLayout,fragments.get(3));
        t.hide(fragments.get(1));
        t.hide(fragments.get(2));
        t.hide(fragments.get(3));
        t.show(fragments.get(0));
        t.commit();

        main_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                switch (checkedId){
                    case R.id.main_rb_1:
                        showFragment(0);
                        break;
                    case R.id.main_rb_2:
                        showFragment(1);
                        break;
                    case R.id.main_rb_3:
                        showFragment(2);
                        break;
                    case R.id.main_rb_4:
                        showFragment(3);
                        break;
                }

            }
        });
    }

    public void showFragment(int position){

        FragmentTransaction t = fm.beginTransaction();
        for (int i = 0; i < fragments.size(); i++) {
            if(i==position){
                t.show(fragments.get(i));
            }else {
                t.hide(fragments.get(i));
            }
        }
        t.commit();
    }



}
