package com.happy.bwiesample.mvp.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.happy.bwiesample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by:
 * 樊健翔
 * 2017/12/23 10:31
 */

public class PopGvAdapter extends BaseAdapter {
    Context context;
    List<String>list = new ArrayList<>();

    public PopGvAdapter(Context context) {
        this.context = context;

        for (int i=0;i<16;i++){
            list.add("哈哈"+i);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //加载布局为一个视图
        View view=View.inflate(context,R.layout.layout_popgv_item,null);

        //在view视图中查找id为image_photo的控件
//        TextView tv_name= (TextView) view.findViewById(R.id.tv);
//        tv_name.setText(list.get(position));
        return view;
    }
}
