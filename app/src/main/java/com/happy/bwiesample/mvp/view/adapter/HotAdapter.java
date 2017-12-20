package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VideoInfo;
import com.happy.bwiesample.entry.VideoType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡华铎 on 2017/12/20.
 */

public class HotAdapter extends RecyclerView.Adapter<HotAdapter.ViewHolder> {

    private List<VideoType> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private OnRecyclerListener listener;

    public HotAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    /**
     * 添加数据过来
     *
     * @param data
     */
    public void addData(List<VideoType> data) {
        this.list.addAll(data);
        notifyDataSetChanged();
    }

    /**
     * 添加监听
     *
     * @param listener
     */
    public void setListener(OnRecyclerListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = inflater.inflate(R.layout.hot_video_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.setOnItemListener(view, holder.getLayoutPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VideoInfo videoInfo = list.get(0).childList.get(position);
        holder.textView.setText(videoInfo.title);
        Glide.with(context).load(videoInfo.pic).into(holder.imageView);
        holder.cardView.setCardElevation(8);//设置阴影部分大小
        holder.cardView.setContentPadding(5,5,5,5);//设置图片距离阴影大小
        holder.cardView.setCardBackgroundColor(Color.parseColor("#40ffffff"));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.get(0).childList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public ImageView imageView;
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.hot_card);
            imageView = itemView.findViewById(R.id.hot_img);
            textView = itemView.findViewById(R.id.hot_text);
        }
    }
}
