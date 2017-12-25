package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happy.bwiesample.R;
import com.happy.bwiesample.entry.VideoInfo;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.mvp.view.activity.SearchActivity;
import com.happy.bwiesample.mvp.view.activity.VideoPlayActivity;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 蔡华铎 on 2017/12/16.
 */

public class RecommendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private LayoutInflater inflater;
    private Context context;
    private OnRecyclerListener listener;
    private List<VideoType> list = new ArrayList<>();

    public RecommendAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void addData(List<VideoType> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加到监听的方法
     * @param listener
     */
    public void setListener(OnRecyclerListener listener){
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1){
            View inflate = inflater.inflate(R.layout.banner_item, parent, false);
            BannerViewHolder holder1 = new BannerViewHolder(inflate);
            return holder1;
        }

        if (viewType == 2){
            View inflate = inflater.inflate(R.layout.text_item, parent, false);
            TextViewHolder holder2 = new TextViewHolder(inflate);
            return holder2;
        }
        if (viewType == 3){
            final View inflate = inflater.inflate(R.layout.video_data_list_item, parent, false);
            final VideoDataViewHolder holder3 = new VideoDataViewHolder(inflate);
            inflate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.setOnItemListener(inflate,holder3.getLayoutPosition());
                }
            });
            return holder3;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof BannerViewHolder){

            ((BannerViewHolder) holder).banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(((VideoInfo)path).pic).into(imageView);
                }
            });
            ((BannerViewHolder) holder).banner.setImages(list.get(0).childList);
            ((BannerViewHolder) holder).banner.start();

            ((BannerViewHolder) holder).search_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(context,SearchActivity.class));
                }
            });
            //点击每个Banner界面的图片获取信息
            ((BannerViewHolder) holder).banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    //跳转到播放界面
                    VideoInfo videoInfo = list.get(0).childList.get(position);
                    Intent intent = new Intent(context, VideoPlayActivity.class);
                    intent.putExtra("playId",videoInfo.dataId);
                    context.startActivity(intent);
                }
            });
        }

        if (holder instanceof TextViewHolder){

        }
        if (holder instanceof VideoDataViewHolder){
            List<VideoInfo> beans = list.get(4).childList;
            ((VideoDataViewHolder) holder).textView.setText(beans.get(position-2).title);
            Glide.with(context).load(beans.get(position-2).pic).into(((VideoDataViewHolder) holder).imageView);
            ((VideoDataViewHolder) holder).cardView.setCardElevation(8);//设置阴影部分大小
            ((VideoDataViewHolder) holder).cardView.setContentPadding(5,5,5,5);//设置图片距离阴影大小
            ((VideoDataViewHolder) holder).cardView.setCardBackgroundColor(Color.parseColor("#40ffffff"));

        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.get(0).childList.size()+2;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 1;
        }
        if (position == 1){
            return 2;
        }
        return 3;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder{

        public Banner banner;
        public LinearLayout search_btn;
        public BannerViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.recommend_banner);
            search_btn = itemView.findViewById(R.id.search_btn);
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder{

        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class VideoDataViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView textView;
        public CardView cardView;

        public VideoDataViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.video_img);
            textView = itemView.findViewById(R.id.video_text);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
