package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VideoType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡华铎 on 2017/12/20.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<VideoType> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private OnRecyclerListener listener;

    public SearchAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setListener(OnRecyclerListener listener){
        this.listener = listener;
    }

    /**
     * 添加集合到Adapter
     * @param data
     */
    public void addData(List<VideoType> data){
        this.list.clear();
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.movielist_rv_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnItemListener(view,holder.getLayoutPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoType videoType = list.get(position);
        holder.movielist_item_tv.setText(videoType.title);
        Glide.with(context).load(videoType.pic).into(holder.movielist_item_iv);
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView movielist_item_iv;
        public TextView movielist_item_tv;

        public ViewHolder(View itemView) {
            super(itemView);
            movielist_item_iv = itemView.findViewById(R.id.movielist_item_iv);
            movielist_item_tv = itemView.findViewById(R.id.movielist_item_tv);
        }
    }



}
