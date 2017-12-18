package com.wuochoang.popularmovie.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wuochoang.popularmovie.App;

import com.wuochoang.popularmovie.R;
import com.wuochoang.popularmovie.common.utils.Utils;
import com.wuochoang.popularmovie.di.component.ActivityComponent;
import com.wuochoang.popularmovie.di.module.ActivityModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by quyenlx on 8/8/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public abstract BaseFragment initFragment();
    private ProgressDialog mProgressDialog;
    protected ActivityComponent component;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        component = App.get().getComponent().plus(new ActivityModule(this));
        replaceFragment(initFragment());
    }


    public void replaceFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        App.get().setCurrentActivity(this);
    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = Utils.showLoadingDialog(this);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
}
