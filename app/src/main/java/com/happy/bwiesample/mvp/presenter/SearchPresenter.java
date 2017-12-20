package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.mvp.model.SearchModel;
import com.happy.bwiesample.mvp.view.SearchView;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * Created by 蔡华铎 on 2017/12/19.
 */

public class SearchPresenter extends BasePresenter<SearchView,SearchModel> {

    @Inject
    public SearchPresenter(){
    }
    @Inject
    SearchModel model;

    public void getData(){
        //获取搜索出来的结果
        Flowable<VideoHttpResponse<VideoRes>> videoList = model.getSearchVideoList(getView().getEditText());
        videoList.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoHttpResponse<VideoRes>>() {
                    @Override
                    public void onNext(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                        //把获取到的结果通过View层接口回调
                        getView().showSearchData(videoResVideoHttpResponse);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getHotData(){
        model.getHotVideoList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<VideoHttpResponse<VideoRes>>() {
                    @Override
                    public void onNext(VideoHttpResponse<VideoRes> videoResVideoHttpResponse) {
                        getView().showHotData(videoResVideoHttpResponse);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
