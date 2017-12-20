package com.happy.bwiesample.mvp.view.activity;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.mvp.presenter.FindPlayPresenter;

/**
 * 作者： 方冬冬
 * 时间： 2017/12/19 19:32
 * 功能：
 */

public class FindPlayActivity extends BaseMvpActivity<FindPlayPresenter> {

    private RelativeLayout rela;

    @Override
    public void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_find_play;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        rela = findViewById(R.id.rela);
        View inflate = LayoutInflater.from(this).inflate(R.layout.simple_player_view_player, rela);
        PlayerView playerVie=new PlayerView(this,inflate)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource("http://youkesupload.oss-cn-hangzhou.aliyuncs.com/0e9641acc8acebcf2cd8657f334d5cca6aba480b/c4e73cf9957556f24f74e95320ddcce692d84633.mp4");
        playerVie.startPlay();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //newConfig.orientation获得当前屏幕状态是横向或者竖向
        //Configuration.ORIENTATION_PORTRAIT 表示竖向
        //Configuration.ORIENTATION_LANDSCAPE 表示横屏
        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(FindPlayActivity.this, "现在是竖屏", Toast.LENGTH_SHORT).show();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
            rela.setLayoutParams(params);
        }
        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(FindPlayActivity.this, "现在是横屏", Toast.LENGTH_SHORT).show();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            rela.setLayoutParams(params);
        }
    }

}
