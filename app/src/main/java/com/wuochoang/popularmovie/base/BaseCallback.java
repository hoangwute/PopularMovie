package com.wuochoang.popularmovie.base;

import com.wuochoang.popularmovie.network.entities.BaseResult;

/**
 * Created by TuanJio on 8/14/2017.
 */

public interface BaseCallback {

    void onSuccess(BaseResult result);

    void onFail();
}
