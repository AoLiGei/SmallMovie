package com.happy.bwiesample.mvp.view.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.mvp.presenter.VideoPlayPresenter;
import com.happy.bwiesample.mvp.view.VideoPlayView;
import com.happy.bwiesample.mvp.view.fragment.CommentFragment;
import com.happy.bwiesample.mvp.view.fragment.JJFragment;
import com.jude.swipbackhelper.SwipeBackHelper;

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
    private TextView tv_title;
    private ViewPager play_vp;
    private TabLayout play_tab;
    private ImageView iv_return;

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
        SwipeBackHelper.onCreate(this);
        SwipeBackHelper.getCurrentPage(this)
                .setSwipeBackEnable(true)
                .setSwipeSensitivity(0.5f)
                .setSwipeRelateEnable(true)
                .setSwipeRelateOffset(300);
        playId = getIntent().getStringExtra("playId");
        hideBottomUIMenu();
        rela = findViewById(R.id.videoPlay_rela);
        videoPlayToolBar = findViewById(R.id.videoPlay_toolBar);
        tv_title = findViewById(R.id.vrplay_title);
        videoView = LayoutInflater.from(this).inflate(R.layout.simple_player_view_player, rela);
        playerVie = new PlayerView(this, videoView);
        play_tab = findViewById(R.id.videoPlay_tab);
        play_vp = findViewById(R.id.videoPlay_vp);
        iv_return = findViewById(R.id.videoPlay_iv_return);
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        play_tab.setTabMode(TabLayout.MODE_FIXED);
        play_tab.addTab(play_tab.newTab().setText("简介"));
        play_tab.addTab(play_tab.newTab().setText("评论"));
        play_tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if(tab.getText().equals("简介")){
                    play_vp.setCurrentItem(0);
                }else if(tab.getText().equals("评论")){
                    play_vp.setCurrentItem(1);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



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
    @Override
    public void setMovieRes(VideoHttpResponse<VideoRes> videoInfo) {
        //判断网络请求的url是都为空，为空加载本地默认资源
        videoRes = videoInfo.getRet();
        if(videoRes!=null){
            play_vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    if(position==0){
                        return JJFragment.getJJFragmentInstance(videoRes);
                    }else {
                        return CommentFragment.getCommentFragmentInstance(playId);
                    }

                }
                @Override
                public int getCount() {
                    return 2;
                }
            });
            if(videoRes.getVideoUrl().isEmpty()){
                playLocalMovie();
            }else {
                tv_title.setText(videoRes.title);
                playNetMovie(videoRes.getVideoUrl(), videoRes.title, videoRes.pic);
            }
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
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.VISIBLE);
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
        SwipeBackHelper.onDestroy(this);
        if (playerVie != null) {
            playerVie.onDestroy();
        }
    }
}
