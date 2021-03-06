package com.happy.bwiesample.mvp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.helper.NetWorkHelper;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.view.JXView;
import com.happy.bwiesample.mvp.view.activity.VideoPlayActivity;
import com.happy.bwiesample.mvp.view.adapter.OnRecyclerListener;
import com.happy.bwiesample.mvp.view.adapter.RecommendAdapter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.List;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class JXFragment extends BaseMvpFragment<JXPresenter> implements JXView {
    @Inject
    NetWorkHelper netWorkHelper;
    private RecyclerView recyclerView;
    private Toolbar recommend_toolbar;
    private SpringView springView;
    private TextView jx_Prompt;

    private int height = 200;
    private int overallXScroll = 0;
    private RecommendAdapter adapter;
    private ProgressBar progressBar;

    @Override
    public int setLayout() {
        return R.layout.fragment_jx;
    }

    @Override
    public void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        jx_Prompt = view.findViewById(R.id.jx_Prompt);
        springView = view.findViewById(R.id.jx_spring);
        progressBar = view.findViewById(R.id.special_pb);
        recommend_toolbar = view.findViewById(R.id.recommend_toolbar);
        recyclerView = view.findViewById(R.id.recommend_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);

        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                p.showVideoData();

            }

            @Override
            public void onLoadmore() {
                Toast.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                springView.onFinishFreshAndLoad();
            }
        });
    }

    @Override
    public void initData() {
        super.initData();
        //判断网络
        if (netWorkHelper.isConnectedByState()) {
            p.showVideoData();
            setLisenter();
            recyclerView.setVisibility(View.VISIBLE);
            jx_Prompt.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            jx_Prompt.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }

    }

    private void setLisenter() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                overallXScroll = overallXScroll + dy;// 累加y值 解决滑动一半y值为0
                if (overallXScroll <= 0) {   //设置标题的背景颜色
                    recommend_toolbar.setVisibility(View.GONE);
                    recommend_toolbar.setBackgroundColor(Color.argb((int) 0, 33, 150, 243));
                } else if (overallXScroll > 0 && overallXScroll <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    recommend_toolbar.setVisibility(View.VISIBLE);
                    float scale = (float) overallXScroll / height;
                    float alpha = (255 * scale);
                    recommend_toolbar.setBackgroundColor(Color.argb((int) alpha, 33, 150, 198));
                } else {
                    recommend_toolbar.setBackgroundColor(Color.argb((int) 255, 33, 150, 243));
                }
            }

        });
    }

    @Override
    public void setVideoData(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
        if (videoResVideoHttpResponse != null) {
            progressBar.setVisibility(View.GONE);
            final List<VideoType> list = videoResVideoHttpResponse.getRet().list;
            //设置适配器
            adapter = new RecommendAdapter(getActivity());
            adapter.addData(list);
            recyclerView.setAdapter(adapter);
            springView.onFinishFreshAndLoad();
            //设置监听
            adapter.setListener(new OnRecyclerListener() {
                @Override
                public void setOnItemListener(View view, int position) {
                    Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                    intent.putExtra("playId", list.get(4).childList.get(position - 2).dataId);
                    getActivity().startActivity(intent);

                }
            });
        }
    }

}
