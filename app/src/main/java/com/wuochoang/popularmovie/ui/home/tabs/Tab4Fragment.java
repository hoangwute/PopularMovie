package com.wuochoang.popularmovie.ui.home.tabs;

import com.wuochoang.popularmovie.base.BaseFragment;
import com.wuochoang.popularmovie.ui.devices.DevicesFragment;

/**
 * Created by quyenlx on 8/9/2017.
 */

public class Tab4Fragment extends BaseTabFragment {
    @Override
    public BaseFragment initFragment() {
        return new DevicesFragment();
    }

//
//    @Override
//    public void onBackStackChanged() {
//        Timber.i("Size Tab4 : " + frm.getBackStackEntryCount());
//        Fragment fragment = frm.findFragmentById(R.id.tab_container);
//        if (fragment != null)
//            Timber.i("Fragment Tab4 : " + fragment.getClass().getSimpleName());
//    }
}
