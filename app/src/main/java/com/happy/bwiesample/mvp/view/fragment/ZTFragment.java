package com.happy.bwiesample.mvp.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.RecommendBean;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoInfo;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.helper.NetWorkHelper;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.presenter.ZTPresenter;
import com.happy.bwiesample.mvp.view.ZTView;
import com.happy.bwiesample.mvp.view.activity.VideoListActivity;
import com.happy.bwiesample.mvp.view.adapter.SpecialRecyclerAdapter;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class ZTFragment extends BaseMvpFragment<ZTPresenter> implements ZTView {
    @Inject
    NetWorkHelper netWorkHelper;
    @Inject
    Context context;
    private RecyclerView recyclerView;
    private SpringView sv;
    private List<VideoInfo> videoInfos;
    private ProgressBar progressBar;
    private SpecialRecyclerAdapter adapter;
    private TextView textView;

    @Override
    public int setLayout() {
        return R.layout.fragment_zt;
    }

    @Override
    public void inject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void initView() {
        super.initView();
        recyclerView = view.findViewById(R.id.special_rv);
        sv = view.findViewById(R.id.special_sp);
        progressBar = view.findViewById(R.id.special_pb);
        textView = view.findViewById(R.id.jx_Prompt);

    }

    @Override
    public void initData() {
        super.initData();
        //判断网络
        if (netWorkHelper.isConnectedByState()) {
            p.getCommit();
            recyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showZTList(final VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
        if (videoResVideoHttpResponse != null) {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            VideoRes videoRes = videoResVideoHttpResponse.getRet();
            videoInfos = new ArrayList<>();
            for (int i = 1; i < videoRes.list.size(); i++) {
                if (!TextUtils.isEmpty(videoRes.list.get(i).moreURL) && !TextUtils.isEmpty(videoRes.list.get(i).title)) {
                    VideoInfo videoInfo = videoRes.list.get(i).childList.get(0);
                    videoInfo.title = videoRes.list.get(i).title;
                    videoInfo.moreURL = videoRes.list.get(i).moreURL;
                    videoInfos.add(videoInfo);
                }
            }
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            adapter = new SpecialRecyclerAdapter(context, videoInfos);
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClick(new SpecialRecyclerAdapter.setOnItemClick() {
                @Override
                public void ItemCliek(View view, int position) {
                    Intent intent = new Intent(context, VideoListActivity.class);
                    intent.putExtra("url", videoInfos.get(position).moreURL);
                    intent.putExtra("name", videoInfos.get(position).title);
                    startActivity(intent);
                }
            });

            sv.setListener(new SpringView.OnFreshListener() {
                @Override
                public void onRefresh() {
                    p.getCommit();
                    adapter.upDate(videoInfos);
                    sv.onFinishFreshAndLoad();
                }

                @Override
                public void onLoadmore() {
                }
            });

        }
    }

}
