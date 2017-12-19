package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VrEventBean;
import com.happy.bwiesample.entry.VrImageItem;
import com.happy.bwiesample.mvp.view.activity.VrPlayActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/19
 * @Time 9:43
 */

public class VrImgAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<VrImageItem> datas;
    public VrImgAdapter(Context context, List<VrImageItem> datas) {
        this.context = context;
        this.datas = datas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context,R.layout.vrimg_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {

        if(holder instanceof MyViewHolder){
            Glide.with(context).load(datas.get(position).getImgUrl()).placeholder(R.mipmap.ic_launcher).into(((MyViewHolder) holder).iv);
            ((MyViewHolder) holder).tv.setText(datas.get(position).getmName());
            ((MyViewHolder) holder).iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(new VrEventBean(0,datas.get(position).getmName(),datas.get(position).getImgUrl(),datas.get(position).getMusicUrl()));
                    Intent intent = new Intent(context, VrPlayActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv;
        public ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.vrimg_item_tv);
            iv=itemView.findViewById(R.id.vrimg_item_iv);
        }
    }
}
