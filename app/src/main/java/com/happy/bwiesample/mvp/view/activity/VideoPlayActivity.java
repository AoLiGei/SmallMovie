package com.happy.bwiesample.mvp.view.activity;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.helper.ScreenHelper;
import com.happy.bwiesample.mvp.presenter.VideoPlayPresenter;
import com.happy.bwiesample.mvp.view.VideoPlayView;

import javax.inject.Inject;

public class VideoPlayActivity extends BaseMvpActivity<VideoPlayPresenter> implements VideoPlayView {

    private RelativeLayout rela;

    @Inject
    ScreenHelper screenHelper;
    @Override
    public void inject() {

        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_video_play;
    }

    @Override
    public void initView() {
        super.initView();
        hideBottomUIMenu();
        rela = findViewById(R.id.videoPlay_rela);
        View inflate = LayoutInflater.from(this).inflate(R.layout.simple_player_view_player, rela);
        PlayerView playerVie=new PlayerView(this,inflate)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .hideCenterPlayer(false)
                .setPlaySource("http://youkesupload.oss-cn-hangzhou.aliyuncs.com/0e9641acc8acebcf2cd8657f334d5cca6aba480b/c4e73cf9957556f24f74e95320ddcce692d84633.mp4");
        playerVie.startPlay();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
            rela.setLayoutParams(params);
        }
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.MATCH_PARENT);
            Log.d("height", "onConfigurationChanged: "+screenHelper.getScreenHeight());
            rela.setBackgroundColor(Color.RED);
            rela.setLayoutParams(params);
        }
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
