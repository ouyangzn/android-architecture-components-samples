/*
 * Copyright (c) 2017.  ouyangzn   <ouyangzn@163.com>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ouyangzn.simplesample.module.livedata;

import android.arch.lifecycle.LiveData;
import android.os.Handler;
import android.util.Log;

import java.util.Random;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions:
 */
public class DataLiveData extends LiveData<TestData> {

    private static final String TAG = "DataLiveData";

    private Handler mHandler;
    private Runnable mDataRunnable;

    public DataLiveData() {
        mHandler = new Handler();
        mDataRunnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                postValue(new TestData("随机数:" + random.nextInt()));
                mHandler.postDelayed(this, 2000);
            }
        };
    }

    @Override
    protected void onActive() {
        Log.d(TAG, "---------onActive--------");
        // 有观察者,开启数据操作
        mHandler.post(mDataRunnable);
    }

    @Override
    protected void onInactive() {
        Log.d(TAG, "---------onInactive--------");
        // 没人观察变化时,取消对数据的操作
        mHandler.removeCallbacks(mDataRunnable);
    }

}
