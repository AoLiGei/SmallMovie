package com.happy.bwiesample.base;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.happy.bwiesample.di.component.ActivityComponent;
import com.happy.bwiesample.helper.NetWorkHelper;

import javax.inject.Inject;

public abstract class BaseMvpActivity<P extends BasePresenter> extends AppCompatActivity {

    @Inject
    public P p;

    @Inject
    NetWorkHelper netWorkHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        setSystemBarTransparent(true);
        inject();
        p.attachView(this);
        initView();
        initData();

    }

    public abstract void inject();
    public abstract int setLayout();
    private void setSystemBarTransparent(boolean on) {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // KITKAT解决方案
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }

    }

    public void initView(){

    }
    public void initData(){

    }

    protected ActivityComponent getActivityComponent(){
        return ActivityComponent.getActivityComponentInstance();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        p.dettachView();
    }
}
