package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VideoType;

import java.util.List;

/**
 * 作者： 方冬冬
 * 时间： 2017/12/20 21:00
 * 功能：发现页面的适配
 */

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.MyViewHolder> {

    private Context context;
    private List<VideoType> mDatas;

    public FindAdapter(Context context, List<VideoType> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    public int dp(int dp) {
        final float density = context.getResources().getDisplayMetrics().density;
        return (int) (dp * density);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_renren_layout, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(view, myViewHolder.getAdapterPosition());

            }
        });
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        if (mDatas != null){
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(context).load(mDatas.get(position).pic).fitCenter().into((ImageView) holder.itemView.findViewById(R.id.image_view));
        ((TextView) holder.itemView.findViewById(R.id.text_view)).setText("     "+mDatas.get(position).description);
        ((TextView) holder.itemView.findViewById(R.id.name_view)).setText(mDatas.get(position).title);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTextView;
        ImageView mImageView;
        TextView mNameView;

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    private MyItemClickListener mItemClickListener;

    public interface MyItemClickListener {
        void onItemClick(View view,int postion);
    }

    /**
     * 设置Item点击监听
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener){
        this.mItemClickListener = listener;
    }

}
