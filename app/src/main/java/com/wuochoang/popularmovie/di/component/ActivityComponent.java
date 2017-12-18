package com.wuochoang.popularmovie.di.component;

import com.wuochoang.popularmovie.MainActivity;
import com.wuochoang.popularmovie.di.module.ActivityModule;
import com.wuochoang.popularmovie.ui.detail.view.MovieDetailsFragment;
import com.wuochoang.popularmovie.ui.list.view.ListFragment;

import dagger.Subcomponent;

/**
 * Created by quyenlx on 8/9/2017.
 */

@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(ListFragment listFragment);

    void inject(MovieDetailsFragment movieDetailsFragment);
}
