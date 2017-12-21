package com.happy.bwiesample.mvp.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
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
    private RelativeLayout rela;
    private VideoListBean.RetBean.ListBean listBean;
    private View inflate;

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

    @Override
    public void initView() {
        super.initView();

        hideBottomUIMenu();

        Intent intent = getIntent();
//        intent.
        video_play_tablayout = (TabLayout) findViewById(R.id.video_play_tablayout);
        video_play_viewpager = (ViewPager) findViewById(R.id.video_play_viewpager);


        rela = findViewById(R.id.videoPlay_rela);
        View inflate = LayoutInflater.from(this).inflate(R.layout.simple_player_view_player, rela);
        PlayerView playerVie=new PlayerView(this,inflate)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .hideCenterPlayer(false)
                .setPlaySource("http://youkesupload.oss-cn-hangzhou.aliyuncs.com/0e9641acc8acebcf2cd8657f334d5cca6aba480b/c4e73cf9957556f24f74e95320ddcce692d84633.mp4 ")
                .startPlay();
//        playerVie.startPlay();
//        playerVie.hideCenterPlayer(true);
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
            rela.setLayoutParams(params);
        }
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            Log.d("height", "onConfigurationChanged: " + screenHelper.getScreenHeight());
            rela.setBackgroundColor(Color.RED);
            rela.setLayoutParams(params);
        }
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


}
