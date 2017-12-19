package com.happy.bwiesample.mvp.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/19
 * @Time 11:42
 */

public class VrPlayActivity extends BaseActivity {
    private VrEventBean mBean;
    private VrPanoramaView mVrPanoramaView;
//    private VrVideoView mVrVideoView;
    private TextView tv_title;
//    private SeekBar mSeekBar;
    private ImageView iv_return;
    private ImageLoadTask mImageLoadTask;
//    private VideoLoadTask mVideoLoadTask;

    @Override
    public int setLayout() {
        return R.layout.activity_vr_play;
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);

        iv_return = findViewById(R.id.vr_iv_return);
        mVrPanoramaView=findViewById(R.id.vr_play_img);
        tv_title = findViewById(R.id.vrplay_title);
//        mVrVideoView=findViewById(R.id.vr_play_video);
//        mSeekBar = findViewById(R.id.vr_play_video_bar);



        //隐藏掉VR效果左下角的信息按钮显示
        mVrPanoramaView.setInfoButtonEnabled(false);
        //隐藏掉VR效果右下角全屏显示按钮
        mVrPanoramaView.setFullscreenButtonEnabled(false);
        //切换VR的模式   参数: VrWidgetView.DisplayMode.FULLSCREEN_STEREO设备模式(手机横着放试试)   VrWidgetView.DisplayMode.FULLSCREEN_MONO手机模式
        mVrPanoramaView.setDisplayMode(VrWidgetView.DisplayMode.FULLSCREEN_STEREO);
//
//        //隐藏VR效果左下角的信息按钮显示
//        mVrVideoView.setInfoButtonEnabled(false);
//        //切换VR的模式   参数:VrVideoView.DisplayMode.FULLSCREEN_STEREO:设备模式(手机横着放试试)      VrVideoView.DisplayMode..FULLSCREEN_MONO手机模式
//        mVrVideoView.setDisplayMode(VrVideoView.DisplayMode.FULLSCREEN_MONO );


        mVrPanoramaView.setEventListener(new MyVREventListener());
        iv_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //根据bean来判断到底是哪一种类型
        if(mBean!=null){
            tv_title.setText(mBean.getResName());
            if(mBean.getType()==0){
                mVrPanoramaView.setVisibility(View.VISIBLE);
                mImageLoadTask=new ImageLoadTask();
                mImageLoadTask.execute();
            }
//            else if(mBean.getType()==1){
//                mVrVideoView.setVisibility(View.VISIBLE);
//                mSeekBar.setVisibility(View.VISIBLE);
//                mVideoLoadTask=new VideoLoadTask();
//                mVideoLoadTask.execute();
//            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(mBean!=null){
            if(mBean.getType()==0){
                mVrPanoramaView.resumeRendering();

            }
//            else if(mBean.getType()==1){
//                mVrVideoView.resumeRendering();
//
//            }
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        if(mBean!=null){
            if(mBean.getType()==0){
                mVrPanoramaView.pauseRendering();
            }
//            else if(mBean.getType()==1){
//                mVrVideoView.pauseRendering();
//            }
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
            if(mBean.getType()==0){
                mVrPanoramaView.shutdown();
                if (mImageLoadTask != null) {
                    if (!mImageLoadTask.isCancelled()) {
                        mImageLoadTask.cancel(true);
                    }
                }

            }
//            else if(mBean.getType()==1){
//                mVrVideoView.shutdown();
//                if (mVideoLoadTask != null) {
//                    if (!mVideoLoadTask.isCancelled()) {
//                        mVideoLoadTask.cancel(true);
//                    }
//                }
//            }
            mBean=null;
        }

    }

    private class ImageLoadTask extends AsyncTask<Void,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(Void... voids) {

            if(mBean!=null){
                try {
                    URL url = new URL(mBean.getResUrl());
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmap = BitmapFactory.decodeStream(input);
                    return myBitmap;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
//            try {
//                //从资产目录拿到资源,返回结果是字节流
//                InputStream inputStream = getAssets().open("andes.jpg");
//                //把字节流转换成Bitmap对象
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                return bitmap;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if(bitmap!=null){
                Toast.makeText(VrPlayActivity.this, "有图片!", Toast.LENGTH_SHORT).show();

                //创建bVrPanoramaView.Options,去决定显示VR是普通效果,还是立体效果
                VrPanoramaView.Options options = new VrPanoramaView.Options();
                //TYPE_STEREO_OVER_UNDER立体效果:图片的上半部分放在左眼显示,下半部分放在右眼显示     TYPE_MONO:普通效果
                options.inputType=VrPanoramaView.Options.TYPE_MONO;
                //使用VR控件对象,显示效果  参数:1.Bitmap对象      2.VrPanoramaView.Options对象,决定显示的效果
                mVrPanoramaView.loadImageFromBitmap(bitmap, options);
            }

        }
    }

    //B.自定义一个类继承AsyncTask,只使用我们需要的方法.完成在子线程加载图片资源,在主线程显示
//    private class VideoLoadTask extends AsyncTask<Void, Void, Void> {
//        //B.该方法在子线程运行,从本地文件中把资源加载到内存中
//        @Override
//        protected Void doInBackground(Void... Void) {
//            //创建VrVideoView.Options对象,决定VR是普通的效果,还是立体效果
//            VrVideoView.Options options = new VrVideoView.Options();
//            //立体模式
//            options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
//            //处理加载的视频格式
//            //FORMAT_DEFAULT:默认格式(SD卡或assets)
//            //FORMAT_HLS:流媒体数据格式(直播)
//            options.inputFormat = VrVideoView.Options.FORMAT_DEFAULT;
//            try {
//                //提示:视频加载的方法还做了把视频读取到内存中的操作,所以它有一个矛盾,调用该方法是放在主线程还是子线程(一般我们放在子线程)
//                //使用VR控件对象,从资产目录加载视频数据,显示效果 参数: 1.String对象 2.VrVideoView.Options对象,决定显示效果
//                mVrVideoView.loadVideoFromAsset(mBean.getResUrl(), options);//不要管他在爆红,就在子线程里执行
//                //使用VR控件对象,从网络加载视频数据,显示效果(要加网络权限)   参数:   1.视频网址,String对象
////              vr_video.loadVideo(Uri.parse("http://youkesvideo.oss-cn-hangzhou.aliyuncs.com/movie2/2016/10/11/%E6%B9%84%E5%85%AC%E6%B2%B3%E8%A1%8C%E5%8A%A8.Operation.Mekong.2016.TC720P.X264.AAC.Mandarin.CHS.Mp4Ba.mp4"), options);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//    }

    //VR运行状态监听类,自定义一个类继承VrPanoramaEventListener,复写里面的两个方法
    private class MyVREventListener extends VrPanoramaEventListener {
        //当VR视图加载成功的时候回调
        @Override
        public void onLoadSuccess() {
            super.onLoadSuccess();
            Toast.makeText(VrPlayActivity.this, "加载成功!", Toast.LENGTH_SHORT).show();

        }

        //当VR视图加载失败的时候回调
        @Override
        public void onLoadError(String errorMessage) {
            super.onLoadError(errorMessage);
            Toast.makeText(VrPlayActivity.this, "加载失败,,,", Toast.LENGTH_SHORT).show();
        }
    }
}
