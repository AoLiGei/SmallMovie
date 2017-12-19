package com.happy.bwiesample.mvp.view.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;

import com.happy.bwiesample.R;
import com.happy.bwiesample.base.BaseActivity;

public class SearchActivity extends BaseActivity {

    private EditText edt_search;
    private ImageView img_clear;
    private String text;

    @Override
    public int setLayout() {
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        super.initView();
        img_clear = findViewById(R.id.img_clear);
        edt_search = findViewById(R.id.edt_search);
        edt_search.addTextChangedListener(textWatcher);
        text = edt_search.getText().toString();


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

        }
    };
}
