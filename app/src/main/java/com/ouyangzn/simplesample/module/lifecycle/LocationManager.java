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

package com.ouyangzn.simplesample.module.lifecycle;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.os.Handler;
import android.util.Log;

import java.util.Random;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions:
 */
public class LocationManager implements LifecycleObserver {

    private static final String TAG = "LocationManager";

    private Handler mHandler;
    private OnLocationChangedListener mListener;
    private Runnable mLocationRunnable;

    public LocationManager(OnLocationChangedListener listener) {
        mListener = listener;
        mHandler = new Handler();
        mLocationRunnable = new Runnable() {
            @Override
            public void run() {
                Random random = new Random();
                mListener.onLocationChanged(new Location(random.nextDouble(), random.nextDouble()));
                mHandler.postDelayed(this, 2000);
            }
        };
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onStartLocate() {
        Log.d(TAG, "----------onStartLocate-------------");
        mHandler.post(mLocationRunnable);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onStopLocate() {
        Log.d(TAG, "----------onStopLocate-------------");
        mHandler.removeCallbacks(mLocationRunnable);
    }

    public interface OnLocationChangedListener{
        void onLocationChanged(Location location);
    }

}
