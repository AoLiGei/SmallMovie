package com.happy.bwiesample.mvp.view.fragment;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.mvp.presenter.FindPresenter;
import com.happy.bwiesample.mvp.view.FindView;
import com.happy.bwiesample.mvp.view.activity.VideoPlayActivity;
import com.happy.bwiesample.mvp.view.adapter.FindAdapter;
import com.happy.bwiesample.wigdet.OverLayCardLayoutManager;
import com.happy.bwiesample.wigdet.RenRenCallback;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 *  发现页面
 */

public class FindFragment extends BaseMvpFragment<FindPresenter> implements FindView {

    private RecyclerView recyclerView;
    private Button fab;
    private List<VideoType> mDatas;
    private FindAdapter adapter;
    private TextView tv;
    private ImageView imageView;
    private AnimationDrawable anim;

    @Override
    public int setLayout() {
        return R.layout.fragment_find;
    }

    @Override
    public void inject() {

        getFragmentComponent().inject(this);
    }

    @Override
    public void initView() {
        super.initView();
        recyclerView = view.findViewById(R.id.recycler_view);
        fab = view.findViewById(R.id.fab);
        tv = view.findViewById(R.id.find_no);
        imageView = view.findViewById(R.id.find_jia);

    }

    @Override
    public void initData() {
        super.initData();

        anim = (AnimationDrawable) getResources().getDrawable(R.drawable.img);
        imageView.setBackground(anim);
        anim.setOneShot(false);
        anim.start();
        recyclerView.setLayoutManager(new OverLayCardLayoutManager(getActivity()));

        p.showData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                p.showData();
//                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });
        final RenRenCallback callback = new RenRenCallback();


        callback.setSwipeListener(new RenRenCallback.OnSwipeListener() {
            @Override
            public void onSwiped(int adapterPosition, int direction) {
                if (direction == ItemTouchHelper.DOWN || direction == ItemTouchHelper.UP) {
                    mDatas.add(0, mDatas.remove(adapterPosition));
                    recyclerView.getAdapter().notifyDataSetChanged();
                } else {
                    mDatas.remove(adapterPosition);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onSwipeTo(RecyclerView.ViewHolder viewHolder, float offset) {
//                if (offset < -50) {
//                    viewHolder.itemView.findViewById(R.id.like).setVisibility(View.VISIBLE);
//                    viewHolder.itemView.findViewById(R.id.not_like).setVisibility(View.INVISIBLE);
//                } else if (offset > 50) {
//                    viewHolder.itemView.findViewById(R.id.like).setVisibility(View.INVISIBLE);
//                    viewHolder.itemView.findViewById(R.id.not_like).setVisibility(View.VISIBLE);
//                } else {
//                    viewHolder.itemView.findViewById(R.id.like).setVisibility(View.INVISIBLE);
//                    viewHolder.itemView.findViewById(R.id.not_like).setVisibility(View.INVISIBLE);
//                }
            }
        });
        new ItemTouchHelper(callback).attachToRecyclerView(recyclerView);




    }

    @Override
    public void success(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {

        VideoRes ret = videoResVideoHttpResponse.getRet();
        mDatas = ret.list;
        tv.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        adapter = new FindAdapter(getActivity(), mDatas);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FindAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Toast.makeText(getActivity(), "" + postion, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), VideoPlayActivity.class);
                String dataId = mDatas.get(postion).dataId;
                intent.putExtra("playId", dataId);
                getActivity().startActivity(intent);
            }
        });

    }
}
