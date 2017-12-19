package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.helper.GlideHelper;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by:
 * 樊健翔
 * 2017/12/19 16:44
 */

public class SpecialRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Inject
    GlideHelper helper;
    Context context;
    List<VideoType> typeList;

    public SpecialRecyclerAdapter(Context context, List<VideoType> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.special_rv_item, null);
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

        if (holder instanceof MyViewHodler) {
            VideoType videoType = typeList.get(position);
            if(typeList.get(position).childList.get(0).pic!=null&&!typeList.get(position).childList.get(0).pic.equals("")){
                Glide.with(context).load(typeList.get(position).childList.get(0).pic).into(((MyViewHodler) holder).imageView);
            }
            if(videoType.title!=null&&!videoType.title.equals("")&&videoType.title!="Banner"){
                ((MyViewHodler) holder).textView.setText(typeList.get(position).title);
            }
        }


    }

    @Override
    public int getItemCount() {
        return typeList.size();
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
