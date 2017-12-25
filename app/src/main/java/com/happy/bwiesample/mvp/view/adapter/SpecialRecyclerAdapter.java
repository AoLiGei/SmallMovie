package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VideoInfo;
import com.happy.bwiesample.helper.GlideHelper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by:
 * 樊健翔
 * 2017/12/19 16:44
 * 专题适配器
 */

public class SpecialRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Inject
    GlideHelper helper;
    Context context;
    List<VideoInfo> videoInfos = new ArrayList<>();
    ;

    public SpecialRecyclerAdapter(Context context, List<VideoInfo> videoInfos) {
        this.context = context;
        this.videoInfos = videoInfos;
    }

    public void addData(List<VideoInfo> videoInfos) {
        this.videoInfos.addAll(videoInfos);
        notifyDataSetChanged();
    }

    public void upDate(List<VideoInfo> videoInfos) {
        this.videoInfos.clear();
        addData(videoInfos);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.special_rv_item, null);
        MyViewHodler holder = new MyViewHodler(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHodler) {
            Glide.with(context).load(videoInfos.get(position).pic).into(((MyViewHodler) holder).imageView);
            ((MyViewHodler) holder).textView.setText(videoInfos.get(position).title);

            //点击事件
            if (setOnItemClick != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        setOnItemClick.ItemCliek(holder.itemView, position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return videoInfos.size();
    }


    setOnItemClick setOnItemClick;

    public void setOnItemClick(SpecialRecyclerAdapter.setOnItemClick setOnItemClick) {
        this.setOnItemClick = setOnItemClick;
    }

    public interface setOnItemClick {
        void ItemCliek(View view, int position);

    }

    class MyViewHodler extends RecyclerView.ViewHolder {
        View view;
        ImageView imageView;
        TextView textView;

        public MyViewHodler(View view) {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.special_item_iv);
            textView = view.findViewById(R.id.special_item_tv);

        }
    }
}
