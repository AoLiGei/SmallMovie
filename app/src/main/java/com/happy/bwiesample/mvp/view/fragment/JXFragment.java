package com.happy.bwiesample.mvp.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.RecommendBean;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.view.JXView;
import com.happy.bwiesample.mvp.view.activity.CaiPlayerActivity;
import com.happy.bwiesample.mvp.view.adapter.RecommendAdapter;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class JXFragment extends BaseMvpFragment<JXPresenter> implements JXView {

    private RecyclerView recyclerView;
    private Toolbar recommend_toolbar;

    private int height = 150;
    private int overallXScroll = 0;
    private RecommendAdapter adapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_jx;
    }

    @Override
    public void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void setVideoData(final RecommendBean bean) {
        //设置适配器
        adapter = new RecommendAdapter(getActivity(),bean);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new RecommendAdapter.OnRecyclerItemListener() {
            @Override
            public void setItemListener(View view, int position) {
                RecommendBean.RetBean.ListBean.ChildListBean listBean = bean.ret.list.get(4).childList.get(position - 2);
                Intent intent = new Intent(getActivity(), CaiPlayerActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public void initView() {
        super.initView();
        recommend_toolbar = view.findViewById(R.id.recommend_toolbar);
        recyclerView = view.findViewById(R.id.recommend_recycler);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
    }

    @Override
    public void initData() {
        super.initData();
        p.showVideoData();
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
                    recommend_toolbar.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));
                } else if (overallXScroll > 0 && overallXScroll <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    recommend_toolbar.setVisibility(View.VISIBLE);
                    float scale = (float) overallXScroll / height;
                    float alpha = (255 * scale);
                    recommend_toolbar.setBackgroundColor(Color.argb((int) alpha, 200, 29, 26));
                } else {
                    recommend_toolbar.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
            }

        });
    }
}
