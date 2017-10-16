package com.wuochoang.popularmovie;

import android.os.Bundle;

import com.wuochoang.popularmovie.base.BaseActivity;
import com.wuochoang.popularmovie.base.BaseFragment;

public class MainActivity extends BaseActivity {

    @Override
    public BaseFragment initFragment() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
