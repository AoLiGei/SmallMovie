package com.happy.bwiesample.mvp.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoInfo;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.entry.VideoType;
import com.happy.bwiesample.helper.SearchDBHelper;
import com.happy.bwiesample.mvp.presenter.SearchPresenter;
import com.happy.bwiesample.mvp.view.SearchView;
import com.happy.bwiesample.mvp.view.adapter.HotAdapter;
import com.happy.bwiesample.mvp.view.adapter.OnRecyclerListener;
import com.happy.bwiesample.mvp.view.adapter.SearchAdapter;
import com.happy.bwiesample.wigdet.SearchHistory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class SearchActivity extends BaseMvpActivity<SearchPresenter> implements View.OnClickListener,SearchView{

    @Inject
    SearchDBHelper dbHelper;
    private EditText edt_search;
    private ImageView img_clear;
    private TextView tv_operate;
    private RecyclerView search_result_recycler,hot_recycler;
    private RelativeLayout rl_his_rec;
    private SearchAdapter adapter;
    private SearchHistory history;
    private ImageView img_search_clear;

    //用来存放保存搜索记录的Key值
    private List<String> list = new ArrayList<>();

    @Override
    public void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        super.initView();
        tv_operate = findViewById(R.id.tv_operate);
        tv_operate.setOnClickListener(this);
        img_clear = findViewById(R.id.img_clear);
        img_clear.setOnClickListener(this);
        edt_search = findViewById(R.id.edt_search);
        edt_search.addTextChangedListener(textWatcher);

        search_result_recycler = findViewById(R.id.search_result_recycler);
        GridLayoutManager manager1 = new GridLayoutManager(this,3);
        search_result_recycler.setLayoutManager(manager1);

        hot_recycler = findViewById(R.id.hot_recycler);
        GridLayoutManager manager2 = new GridLayoutManager(this,2);
        hot_recycler.setLayoutManager(manager2);
        p.getHotData();

        img_search_clear = findViewById(R.id.img_search_clear);
        img_search_clear.setOnClickListener(this);
        rl_his_rec = findViewById(R.id.rl_his_rec);
        //从数据库中个搜索数据出来显示出搜索记录
        history = findViewById(R.id.search_history);

        if (dbHelper.dbIsEntry()){
            List<String> list = dbHelper.queryData();
            for (int i=0;i<list.size();i++){
                String search_text = list.get(i);
                TextView textView = new TextView(SearchActivity.this);
                textView.setTextColor(Color.parseColor("#ffffff"));
                textView.setText(search_text);
                history.addView(textView);
            }
        }


    }

    @Override
    public void initData() {
        super.initData();
    }

    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0){
                img_clear.setVisibility(View.VISIBLE);
                tv_operate.setText("搜索");
            }else {
                img_clear.setVisibility(View.GONE);
                tv_operate.setText("取消");
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_clear:
                edt_search.getText().clear();
                break;
            case R.id.tv_operate:
                String text = tv_operate.getText().toString().trim();//获取时取消还是搜索的状态
                if (text.equals("取消")){
                    finish();
                }else if (text.equals("搜索")){
                    rl_his_rec.setVisibility(View.GONE);
                    dbHelper.addData(getEditText());//添加搜索内容到数据库
                    search_result_recycler.setVisibility(View.VISIBLE);
                    p.getData();
                }
                break;
            case R.id.img_search_clear:
                //删除数据库的数据
                dbHelper.deleteData();
                //删除控件中的所有TextView
                history.removeAllViews();
                break;
        }
    }

    @Override
    public String getEditText() {
        return edt_search.getText().toString();
    }

    @Override
    public void showSearchData(VideoHttpResponse<VideoRes> videobean) {
        final List<VideoType> list = videobean.getRet().list;
        adapter = new SearchAdapter(this);
        adapter.addData(list);
        search_result_recycler.setAdapter(adapter);
        adapter.setListener(new OnRecyclerListener() {
            @Override
            public void setOnItemListener(View view, int position) {
                VideoType videoType = list.get(position);
                Intent intent = new Intent(SearchActivity.this,VideoPlayActivity.class);
                intent.putExtra("playId",videoType.dataId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showHotData(VideoHttpResponse<VideoRes> videobean) {
        final List<VideoType> list = videobean.getRet().list;
        HotAdapter hotAdapter = new HotAdapter(this);
        hotAdapter.addData(list);
        hot_recycler.setAdapter(hotAdapter);
        hotAdapter.setListener(new OnRecyclerListener() {
            @Override
            public void setOnItemListener(View view, int position) {
                VideoInfo videoInfo = list.get(0).childList.get(position);
                Intent intent = new Intent(SearchActivity.this,VideoPlayActivity.class);
                intent.putExtra("playId",videoInfo.dataId);
                startActivity(intent);
            }
        });
    }
}
