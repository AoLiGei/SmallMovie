package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.mvp.model.SearchModel;
import com.happy.bwiesample.mvp.view.SearchView;

import javax.inject.Inject;

/**
 * Created by 蔡华铎 on 2017/12/19.
 */

public class SearchPresenter extends BasePresenter<SearchView,SearchModel> {

    @Inject
    public SearchPresenter(){

    }
}
