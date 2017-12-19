package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VideoListBean;

import java.lang.invoke.CallSite;
import java.util.List;

/**
 * Created by:
 * 樊健翔
 * 2017/12/19 19:36
 */

public class VideoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<VideoListBean.RetBean.ListBean> list;

    public VideoListAdapter(Context context, List<VideoListBean.RetBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.movielist_rv_item, null);
        MyViewHodler holder = new MyViewHodler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (setOnItemClick != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    setOnItemClick.ItemCliek(holder.itemView, position);
                }
            });
        }

        if(holder instanceof MyViewHodler){
            ((MyViewHodler) holder).textView.setText(list.get(position).getTitle());
            Glide.with(context).load(list.get(position).getPic()).into(((MyViewHodler) holder).imageView);

        }
    }

    @Override
    public int getItemCount() {
        return 100;
    }


    setOnItemClick setOnItemClick;

    public void setOnItemClick(VideoListAdapter.setOnItemClick setOnItemClick) {
        this.setOnItemClick = setOnItemClick;
    }

    public interface setOnItemClick {
        void ItemCliek(View view, int position);
    }


    class MyViewHodler extends RecyclerView.ViewHolder{
        View view;
        ImageView imageView;
        TextView textView;

        public MyViewHodler(View view) {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.movielist_item_iv);
            textView = view.findViewById(R.id.movielist_item_tv);

        }
    }
}
