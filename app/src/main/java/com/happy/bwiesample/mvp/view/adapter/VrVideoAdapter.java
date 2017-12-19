package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.vr.sdk.widgets.video.VrVideoView;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VrEventBean;
import com.happy.bwiesample.entry.VrVideoBean;
import com.happy.bwiesample.mvp.view.activity.VrPlayActivity;
import com.happy.bwiesample.mvp.view.activity.VrPlayActivity2;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/19
 * @Time 9:43
 */

public class VrVideoAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<VrVideoBean.ContentBean> datas;
    public VrVideoAdapter(Context context, List<VrVideoBean.ContentBean> datas) {
        this.context = context;
        this.datas = datas;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(context,R.layout.vrvideo_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        if(holder instanceof MyViewHolder){
            Glide.with(context).load(datas.get(position).getImg()).placeholder(R.mipmap.ic_launcher).into(((MyViewHolder) holder).iv);
            ((MyViewHolder) holder).tv.setText(datas.get(position).getTitle());
            ((MyViewHolder) holder).iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().postSticky(new VrEventBean(1,datas.get(position).getTitle(),datas.get(position).getPlay(),null));
                    Intent intent = new Intent(context, VrPlayActivity2.class);
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
            tv=itemView.findViewById(R.id.vrvideo_item_tv);
            iv=itemView.findViewById(R.id.vrvideo_item_iv);
        }
    }
}
