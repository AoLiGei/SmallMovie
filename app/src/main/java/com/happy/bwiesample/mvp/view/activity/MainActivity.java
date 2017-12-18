package com.happy.bwiesample.mvp.view.activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
    private TextView main_title;

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
        main_title = findViewById(R.id.main_title);
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
                        main_title.setText("精选");
                        showFragment(0);
                        break;
                    case R.id.main_rb_2:
                        main_title.setText("专题");
                        showFragment(1);
                        break;
                    case R.id.main_rb_3:
                        main_title.setText("发现");
                        showFragment(2);
                        break;
                    case R.id.main_rb_4:
                        main_title.setText("我的");
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
