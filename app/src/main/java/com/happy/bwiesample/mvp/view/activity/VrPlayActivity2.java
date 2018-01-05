package com.happy.bwiesample.mvp.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.vr.sdk.widgets.common.VrWidgetView;
import com.google.vr.sdk.widgets.pano.VrPanoramaEventListener;
import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseActivity;
import com.happy.bwiesample.entry.VrEventBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/19
 * @Time 11:42
 */

public class VrPlayActivity2 extends BaseActivity {
    private VrEventBean mBean;
    private VrVideoView mVrVideoView;
    private TextView tv_title;
    private SeekBar mSeekBar;
    private ImageView iv_return;
    private VideoLoadTask mVideoLoadTask;

    @Override
    public int setLayout() {
        return R.layout.activity_vr_play2;
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);

        iv_return = findViewById(R.id.vr_iv_return);
        tv_title = findViewById(R.id.vrplay_title);
        mVrVideoView=findViewById(R.id.vr_play_video);
        mSeekBar = findViewById(R.id.vr_play_video_bar);
        //隐藏VR效果左下角的信息按钮显示
        mVrVideoView.setInfoButtonEnabled(false);
        //切换VR的模式   参数:VrVideoView.DisplayMode.FULLSCREEN_STEREO:设备模式(手机横着放试试)      VrVideoView.DisplayMode..FULLSCREEN_MONO手机模式
        mVrVideoView.setDisplayMode(VrVideoView.DisplayMode.FULLSCREEN_STEREO );
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //根据bean来判断到底是哪一种类型
        if(mBean!=null){
            tv_title.setText(mBean.getResName());
             if(mBean.getType()==1){
                mVrVideoView.setVisibility(View.VISIBLE);
                mSeekBar.setVisibility(View.VISIBLE);
                mVideoLoadTask=new VideoLoadTask();
                mVideoLoadTask.execute();
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(mBean!=null){
        if(mBean.getType()==1){
                mVrVideoView.resumeRendering();
            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(mBean!=null){
             if(mBean.getType()==1){
                mVrVideoView.pauseRendering();
            }
        }
    }
    //eventbus来接收对象
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(VrEventBean event) {
       mBean=event;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(mBean!=null){
        if(mBean.getType()==1){
                mVrVideoView.shutdown();
                if (mVideoLoadTask != null) {
                    if (!mVideoLoadTask.isCancelled()) {
                        mVideoLoadTask.cancel(true);
                    }
                }
            }
            mBean=null;
        }

    }


    //B.自定义一个类继承AsyncTask,只使用我们需要的方法.完成在子线程加载图片资源,在主线程显示
    private class VideoLoadTask extends AsyncTask<Void, Void, Void> {
        //B.该方法在子线程运行,从本地文件中把资源加载到内存中
        @Override
        protected Void doInBackground(Void... Void) {
            //创建VrVideoView.Options对象,决定VR是普通的效果,还是立体效果
            VrVideoView.Options options = new VrVideoView.Options();
            //立体模式
            options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
            //处理加载的视频格式
            //FORMAT_DEFAULT:默认格式(SD卡或assets)
            //FORMAT_HLS:流媒体数据格式(直播)
            options.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;
            try {
                //提示:视频加载的方法还做了把视频读取到内存中的操作,所以它有一个矛盾,调用该方法是放在主线程还是子线程(一般我们放在子线程)
                //使用VR控件对象,从资产目录加载视频数据,显示效果 参数: 1.String对象 2.VrVideoView.Options对象,决定显示效果
                mVrVideoView.loadVideo(Uri.parse("http://dl.mojing.cn/xianchang/160705/1467684547_37_3840HD.mp4"), options);//不要管他在爆红,就在子线程里执行
                //使用VR控件对象,从网络加载视频数据,显示效果(要加网络权限)   参数:   1.视频网址,String对象
//              vr_video.loadVideo(Uri.parse("http://youkesvideo.oss-cn-hangzhou.aliyuncs.com/movie2/2016/10/11/%E6%B9%84%E5%85%AC%E6%B2%B3%E8%A1%8C%E5%8A%A8.Operation.Mekong.2016.TC720P.X264.AAC.Mandarin.CHS.Mp4Ba.mp4"), options);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}
