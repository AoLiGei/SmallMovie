package com.happy.bwiesample.di.component;

import com.happy.bwiesample.app.MyApp;
import com.happy.bwiesample.di.moudle.FragmentMoudle;
import com.happy.bwiesample.di.scope.FragmentScope;
import com.happy.bwiesample.mvp.view.fragment.FindFragment;
import com.happy.bwiesample.mvp.view.fragment.JXFragment;
import com.happy.bwiesample.mvp.view.fragment.MineFragment;
import com.happy.bwiesample.mvp.view.fragment.ZTFragment;

import dagger.Component;

/**
 * @Describtion
 * @Author LiAng
 * @Date 2017/12/14
 * @Time 11:48
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentMoudle.class)
public abstract class FragmentComponent {

    public abstract void inject(JXFragment jxFragment);
    public abstract void inject(ZTFragment ztFragment);
    public abstract void inject(FindFragment findFragment);
    public abstract void inject(MineFragment mineFragment);
    private static FragmentComponent fragmentComponent;
    public static FragmentComponent getFragmentComponentInstance(){
        if(fragmentComponent==null) {
            return fragmentComponent = DaggerFragmentComponent
                    .builder()
                    .appComponent(MyApp.getInstance().getAppComponent())
                    .build();
        }
        return fragmentComponent;
    }
}
