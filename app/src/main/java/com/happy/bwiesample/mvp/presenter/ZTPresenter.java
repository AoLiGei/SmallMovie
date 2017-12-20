package com.happy.bwiesample.mvp.presenter;

import com.happy.bwiesample.base.BasePresenter;
import com.happy.bwiesample.entry.RecommendBean;
import com.happy.bwiesample.entry.VideoHttpResponse;
import com.happy.bwiesample.entry.VideoRes;
import com.happy.bwiesample.mvp.model.ZTModel;
import com.happy.bwiesample.mvp.view.ZTView;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/15
 * @Time 19:07
 */

public class ZTPresenter extends BasePresenter<ZTView,ZTModel>{
    @Inject
    ZTModel model;
    @Inject
    public ZTPresenter(){
        
    }

    public void getCommit(){

        model.getTypeData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RecommendBean>() {
                    @Override
                    public void onNext(RecommendBean recommendBean) {
                        getView().showZTList(recommendBean);
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
