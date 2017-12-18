package com.happy.bwiesample.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by 红玫瑰 on 2017/12/12.
 */

public class BasePresenter<V,M> {

    //basepresenter控制view的连接和分离
    private WeakReference<V> referenceView;
    @Inject
    protected M model;

    public void attachView(V v){
        referenceView=new WeakReference<V>(v);
    }

    protected V getView(){
        if(referenceView!=null&&referenceView.get()!=null){
           return referenceView.get();
        }
        return null;
    }

    public void dettachView(){
        if(referenceView!=null&&referenceView.get()!=null){
           referenceView.clear();
           referenceView=null;

        }
    }

}
