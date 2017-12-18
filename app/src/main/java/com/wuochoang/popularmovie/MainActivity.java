package com.wuochoang.popularmovie;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.olddog.common.ToastUtils;
import com.wuochoang.popularmovie.base.BaseActivity;
import com.wuochoang.popularmovie.base.BaseFragment;
import com.wuochoang.popularmovie.common.Constant;
import com.wuochoang.popularmovie.model.Movie;
import com.wuochoang.popularmovie.model.base.ApiResult;
import com.wuochoang.popularmovie.network.ApiService;
import com.wuochoang.popularmovie.ui.devices.DevicesFragment;
import com.wuochoang.popularmovie.ui.list.view.ListFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar_title)
    TextView txtFragmentTitle;
    @BindView(R.id.my_toolbar)
    Toolbar toolBar;

    @Override
    public BaseFragment initFragment() {
        return new ListFragment();
    }

    public void initView() {
        setSupportActionBar(toolBar);
        txtFragmentTitle.setText(App.getStringResource(R.string.Popular));
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_menu); // change tool bar icon
        toolBar.setOverflowIcon(drawable);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menuPopular:
                txtFragmentTitle.setText(App.getStringResource(R.string.Popular));
                replaceFragment(new ListFragment());
                break;
            case R.id.menuFavourite:
                txtFragmentTitle.setText(App.getStringResource(R.string.Favourites));
                replaceFragment(new DevicesFragment());
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
