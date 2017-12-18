package com.happy.bwiesample.di.component;

import com.happy.bwiesample.app.MyApp;
import com.happy.bwiesample.di.moudle.ActivityMoudle;
import com.happy.bwiesample.di.scope.ActivityScope;
import com.happy.bwiesample.mvp.view.activity.MainActivity;

import dagger.Component;

/**
 * Created by 红玫瑰 on 2017/12/13.
 */

@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityMoudle.class)
public abstract class ActivityComponent {
    public abstract void inject(MainActivity activity);
    private static ActivityComponent mainComponent;
    public static ActivityComponent getActivityComponentInstance(){
        if(mainComponent==null){

            mainComponent=DaggerActivityComponent.builder()
                    .appComponent(MyApp.getInstance().getAppComponent())
                    .build();
        }
        return mainComponent;
    }

}
