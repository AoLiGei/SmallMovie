package com.happy.bwiesample.mvp.view.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.helper.ScreenHelper;
import com.happy.bwiesample.mvp.presenter.VideoPlayPresenter;
import com.happy.bwiesample.mvp.view.VideoPlayView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class VideoPlayActivity extends BaseMvpActivity<VideoPlayPresenter> implements VideoPlayView {

    private RelativeLayout rela;

    private String playId;

    @Inject
    Context mContext;
    private Toolbar videoPlayToolBar;
    private View videoView;
    private PlayerView playerVie;
    private VideoRes videoRes;

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
        playId = getIntent().getStringExtra("playId");
        hideBottomUIMenu();
        rela = findViewById(R.id.videoPlay_rela);
        videoPlayToolBar = findViewById(R.id.videoPlay_toolBar);
        videoView = LayoutInflater.from(this).inflate(R.layout.simple_player_view_player, rela);
        playerVie = new PlayerView(this, videoView);


    }

    @Override
    public void initData() {
        super.initData();
        if(playId.isEmpty()){

            //播放默认资源

            playLocalMovie();
        }else {
            //加载网络的资源

            p.getVideoRes(playId);
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            videoPlayToolBar.setVisibility(View.VISIBLE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
            rela.setLayoutParams(params);
        }
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            videoPlayToolBar.setVisibility(View.GONE);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  ViewGroup.LayoutParams.MATCH_PARENT);
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

    @Subscribe(sticky = true,threadMode = ThreadMode.MAIN)
    public void onEventPlay(){

    }

    @Override
    public void setMovieRes(VideoHttpResponse<VideoRes> videoInfo) {
        //判断网络请求的url是都为空，为空加载本地默认资源
        videoRes = videoInfo.getRet();
        if(videoRes.getVideoUrl().isEmpty()){
            playLocalMovie();
        }else {
            playNetMovie(videoRes.getVideoUrl(), videoRes.pic, videoRes.title);
        }
    }

    //播放本地资源
    protected void  playLocalMovie(){
        playerVie.setPlaySource("http://youkesupload.oss-cn-hangzhou.aliyuncs.com/0e9641acc8acebcf2cd8657f334d5cca6aba480b/c4e73cf9957556f24f74e95320ddcce692d84633.mp4 ")
                .setTitle("默认资源")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .hideCenterPlayer(false);
        playerVie.startPlay();
    }
    //播放网络资源
    protected void playNetMovie(String videoUrl, String title, final String picUrl){
        playerVie.setPlaySource(videoUrl)
                .setTitle(title)
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        /**加载前显示的缩略图*/
                        Glide.with(mContext)
                                .load(picUrl)
                                .placeholder(R.color.black)
                                .error(R.color.black)
                                .into(ivThumbnail);
                    }
                })
                .hideCenterPlayer(false);
        playerVie.startPlay();
    }
    @Override
    protected void onPause() {
        super.onPause();
        if (playerVie != null) {
            playerVie.onPause();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (playerVie != null) {
            playerVie.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (playerVie != null) {
            playerVie.onDestroy();
        }
    }
}
