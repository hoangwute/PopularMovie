package com.wuochoang.popularmovie.ui.detail.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.olddog.common.ToastUtils;
import com.wuochoang.popularmovie.R;
import com.wuochoang.popularmovie.base.BaseActivity;
import com.wuochoang.popularmovie.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends BaseActivity {

    @Override
    public BaseFragment initFragment() {
        Intent intent = getIntent();
        Integer movieId = intent.getIntExtra("MovieId", 4);
        BaseFragment fragment = new MovieDetailsFragment();
        Bundle arguments = new Bundle();
        arguments.putInt("MovieId", movieId);
        fragment.setArguments(arguments);
        return fragment;
    }

}
