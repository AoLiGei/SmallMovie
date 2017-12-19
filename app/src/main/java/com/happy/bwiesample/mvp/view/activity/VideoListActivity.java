package com.happy.bwiesample.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseActivity;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoInfo;
import com.happy.bwiesample.entry.VideoListBean;
import com.happy.bwiesample.mvp.view.adapter.VideoListAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import java.util.List;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Response;

public class VideoListActivity extends BaseActivity {
    @Inject
    Context context;
    @Inject
    Gson gson;
    private RecyclerView videolist_rv;
    private String url;
    private List<VideoListBean.RetBean.ListBean> list;

    @Override
    public int setLayout() {
        return R.layout.activity_video_list;
    }


    @Override
    public void initView() {
        super.initView();
        videolist_rv = (RecyclerView) findViewById(R.id.videolist_rv);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");

    }

    @Override
    public void initData() {
        super.initData();
        getByOkGo(url);
    }



    private void getByOkGo(String url){
        OkGo.get(url)                            // 请求方式和请求url
                .tag(this)                       // 请求的 tag, 主要用于取消对应的请求
                .cacheKey("cacheKey")            // 设置当前请求的缓存key,建议每个不同功能的请求设置一个
                .cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        VideoListBean videoListBean = gson.fromJson(s, VideoListBean.class);
                        list = videoListBean.getRet().getList();
                        VideoListAdapter adapter = new VideoListAdapter(VideoListActivity.this,list);
                        videolist_rv.setLayoutManager(new GridLayoutManager(VideoListActivity.this,3));
                        videolist_rv.setAdapter(adapter);
                    }
                });
    }
}
