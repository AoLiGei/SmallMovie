package com.happy.bwiesample.mvp.view.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseMvpActivity;
import com.happy.bwiesample.mvp.presenter.SearchPresenter;
import com.happy.bwiesample.mvp.view.SearchView;

public class SearchActivity extends BaseMvpActivity<SearchPresenter> implements View.OnClickListener,SearchView{

    private EditText edt_search;
    private ImageView img_clear;
    private TextView tv_operate;

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
        img_clear = findViewById(R.id.img_clear);
        img_clear.setOnClickListener(this);
        edt_search = findViewById(R.id.edt_search);
        edt_search.addTextChangedListener(textWatcher);


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
        }
    }

    @Override
    public String getEditText() {
        return edt_search.getText().toString();
    }
}
