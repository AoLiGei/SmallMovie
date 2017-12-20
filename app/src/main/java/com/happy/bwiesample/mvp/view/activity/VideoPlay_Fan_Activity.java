package com.happy.bwiesample.mvp.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.entry.VideoListBean;
import com.happy.bwiesample.helper.ScreenHelper;
import com.happy.bwiesample.mvp.presenter.VideoPlayPresenter;
import com.happy.bwiesample.mvp.view.VideoPlayView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

/**
 * 樊健翔
 * 视频播放详情页
 */
public class VideoPlay_Fan_Activity extends BaseMvpActivity<VideoPlayPresenter> implements VideoPlayView {

    private ImageView video_play_head_back;
    private TextView video_play_head_tv;
    private ImageView video_play_head_collection;
    private TabLayout video_play_tablayout;
    private ViewPager video_play_viewpager;

    @Inject
    ScreenHelper screenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void inject() {
        getActivityComponent().inject(VideoPlay_Fan_Activity.this);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_video_play_fan;
    }

    @Subscribe
    public void onEventMainThread(VideoListBean.RetBean.ListBean bean) {
        String msg = "onEventMainThread收到了消息：" + bean.getTitle();
        Log.d("harvic", msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        hideBottomUIMenu();
        video_play_head_back = (ImageView) findViewById(R.id.video_play_head_back);
        video_play_head_tv = (TextView) findViewById(R.id.video_play_head_tv);
        video_play_head_collection = (ImageView) findViewById(R.id.video_play_head_collection);
        video_play_tablayout = (TabLayout) findViewById(R.id.video_play_tablayout);
        video_play_viewpager = (ViewPager) findViewById(R.id.video_play_viewpager);
    }

    @Override
    public void initData() {
        super.initData();
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }
}
