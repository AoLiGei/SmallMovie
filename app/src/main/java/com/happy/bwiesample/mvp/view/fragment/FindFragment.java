package com.happy.bwiesample.mvp.view.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpFragment;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.mvp.presenter.FindPresenter;
import com.happy.bwiesample.mvp.presenter.JXPresenter;
import com.happy.bwiesample.mvp.view.FindView;
import com.happy.bwiesample.mvp.view.JXView;
import com.happy.bwiesample.wigdet.OverLayCardLayoutManager;
import com.happy.bwiesample.wigdet.RenRenCallback;

import java.util.List;
import java.util.ListIterator;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:11
 */

public class FindFragment extends BaseMvpFragment<FindPresenter> implements FindView {

    private RecyclerView recyclerView;
    private Button fab;
    private List<VideoType> mDatas;

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

    }

    @Override
    public void initData() {
        super.initData();
        p.showData();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setVisibility(View.GONE);
                p.showData();
                recyclerView.getAdapter().notifyDataSetChanged();

            }
        });
        final RenRenCallback callback = new RenRenCallback();

        recyclerView.setLayoutManager(new OverLayCardLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter());

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
        recyclerView.setVisibility(View.VISIBLE);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImageView;
        TextView mNameView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        public int dp(int dp) {
            final float density = getResources().getDisplayMetrics().density;
            return (int) (dp * density);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            final View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_renren_layout, parent, false);
            final MyViewHolder myViewHolder = new MyViewHolder(view);

            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            Glide.with(getActivity()).load(mDatas.get(position).pic).fitCenter().into((ImageView) holder.itemView.findViewById(R.id.image_view));
            ((TextView) holder.itemView.findViewById(R.id.text_view)).setText(mDatas.get(position).description);
            ((TextView) holder.itemView.findViewById(R.id.name_view)).setText(mDatas.get(position).title);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }
    }

}
