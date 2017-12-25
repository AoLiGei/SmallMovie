package com.happy.bwiesample.mvp.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseFragment;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.mvp.presenter.CommentPresenter;
import com.happy.bwiesample.mvp.view.CommentView;
import com.happy.bwiesample.mvp.view.adapter.CommentAdapter;

import java.util.List;

import javax.inject.Inject;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/23
 * @Time 9:25
 */

public class CommentFragment extends BaseMvpFragment<CommentPresenter> implements CommentView{

    private String mediaId;
    private RecyclerView comment_rcview;
    private TextView comment_hint;
    @Inject
    Context context;
    @Override
    public int setLayout() {
        return R.layout.fragment_comment;
    }

    @Override
    public void inject() {

        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        comment_rcview=view.findViewById(R.id.comment_rcview);
        comment_hint=view.findViewById(R.id.comment_hint);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle bundle = getArguments();
        mediaId = bundle.getString("mediaId");
        if(mediaId!=null){
            Log.d("mediaId", "onActivityCreated: "+mediaId);
            p.getVideoRes(mediaId);
        }else {
            return;
        }

    }

    public static CommentFragment getCommentFragmentInstance(String mediaId){

        Bundle bundle =new Bundle();
        bundle.putString("mediaId",mediaId);
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public void showComment(VideoHttpResponse<VideoRes> videoRes) {
        List<VideoType> list = videoRes.getRet().list;
        if(list.size()==0){
            return;
        }else {
            comment_rcview.setLayoutManager(new LinearLayoutManager(context));
            comment_rcview.setAdapter(new CommentAdapter(list,context));
            comment_hint.setVisibility(View.GONE);
            comment_rcview.setVisibility(View.VISIBLE);
        }
    }
}
