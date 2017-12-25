package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VideoType;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/24
 * @Time 20:15
 */

public class CommentAdapter extends RecyclerView.Adapter {
    private List<VideoType> datas;
    private Context context;

    public CommentAdapter(List<VideoType> datas,Context context) {
        this.datas = datas;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context,R.layout.comment_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            ((MyViewHolder) holder).tv_user.setText(datas.get(position).phoneNumber);
            ((MyViewHolder) holder).tv_time.setText(datas.get(position).time);
            ((MyViewHolder) holder).tv_comment.setText(datas.get(position).msg);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_user,tv_time,tv_comment;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_user=itemView.findViewById(R.id.comment_user);
            tv_time=itemView.findViewById(R.id.comment_time);
            tv_comment=itemView.findViewById(R.id.comment_text);
        }
    }
}
