package com.wuochoang.popularmovie.di.component;

import com.wuochoang.popularmovie.di.module.ActivityModule;
import com.wuochoang.popularmovie.di.module.ApplicationModule;
import com.wuochoang.popularmovie.di.module.NetworkModule;
import com.wuochoang.popularmovie.di.module.StorageModule;
import com.wuochoang.popularmovie.network.MyServiceInterceptor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by quyenlx on 8/9/2017.
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        StorageModule.class
})
public interface ApplicationComponent {
    ActivityComponent plus(ActivityModule module);

    void inject(MyServiceInterceptor myServiceInterceptor);

}
