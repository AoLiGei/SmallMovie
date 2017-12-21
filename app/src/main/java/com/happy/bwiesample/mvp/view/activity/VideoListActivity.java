package com.happy.bwiesample.mvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseActivity;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoInfo;
import com.happy.bwiesample.entry.VideoListBean;
import com.happy.bwiesample.helper.RetrofitHelper;
import com.happy.bwiesample.http.INews;
import com.happy.bwiesample.http.IVideo;
import com.happy.bwiesample.mvp.view.adapter.VideoListAdapter;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

/**
 * 专题
 * 影片分类列表Activity
 */
public class VideoListActivity extends BaseActivity {
    @Inject
    Context context;
    @Inject
    Gson gson;
    private RecyclerView videolist_rv;
    private String url;
    private List<VideoListBean.RetBean.ListBean> list;
    private ImageView iv_back;
    private TextView tv_head;
    private String name;
    private SpringView springView;

    @Override
    public int setLayout() {
        return R.layout.activity_video_list;
    }


    @Override
    public void initView() {
        super.initView();
        videolist_rv = (RecyclerView) findViewById(R.id.videolist_rv);
        iv_back = findViewById(R.id.video_list_head_back);
        tv_head = findViewById(R.id.video_list_head_tv);
        springView = findViewById(R.id.video_list_sp);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        name = intent.getStringExtra("name");
        tv_head.setText(name);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.base_slide_right_stay, R.anim.base_slide_right_out);
            }
        });


    }

    @Override
    public void initData() {
        super.initData();
        getByOkGo(url);

    }

    /**
     * 请求网路
     *
     * @param url
     */
    private void getByOkGo(final String url) {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();
        //创建一个Request
        final Request request = new Request.Builder()
                .url(url)
                .build();
        //new call
        Call call = mOkHttpClient.newCall(request);
        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String s = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson1 = new Gson();
                        VideoListBean videoListBean = gson1.fromJson(s, VideoListBean.class);
                        list = videoListBean.getRet().getList();
                        final VideoListAdapter adapter = new VideoListAdapter(VideoListActivity.this, list);
                        videolist_rv.setLayoutManager(new GridLayoutManager(VideoListActivity.this, 3));
                        videolist_rv.setAdapter(adapter);

                        //设置点击事件,
                        adapter.setOnItemClick(new VideoListAdapter.setOnItemClick() {
                            @Override
                            public void ItemCliek(View view, int position) {
                                Intent intent = new Intent(VideoListActivity.this,VideoPlay_Fan_Activity.class);
                                VideoListBean.RetBean.ListBean bean = list.get(position);
                                intent.putExtra("playId",list.get(position).getDataId());
                                intent.putExtra("loadURL",list.get(position).getLoadURL());
                                startActivity(intent);
                            }
                        });

                        springView.setListener(new SpringView.OnFreshListener() {
                            @Override
                            public void onRefresh() {
                                getByOkGo(url);
                                adapter.upDate(list);
                                springView.onFinishFreshAndLoad();
                            }

                            @Override
                            public void onLoadmore() {
                                adapter.addData(list);
                            }
                        });

                    }
                });
            }
        });

    }
}
