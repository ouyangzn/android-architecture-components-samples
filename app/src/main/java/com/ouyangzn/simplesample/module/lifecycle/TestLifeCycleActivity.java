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

import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import com.ouyangzn.simplesample.R;
import com.ouyangzn.simplesample.base.BaseLifecycleActivity;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions: 定位,获取当前位置,为了降低功耗,需要在onPause的时候暂停定位,onResume才开启定位
 */
public class TestLifeCycleActivity extends BaseLifecycleActivity {

  @BindView(R.id.tv_test_life_cycle_lat) TextView mTvLat;
  @BindView(R.id.tv_test_life_cycle_lon) TextView mTvLon;
  private LocationManager mLocationManager;

  @Override protected int getContentView() {
    return R.layout.activity_test_life_cycle;
  }

  @Override protected void initData() {
    mLocationManager = new LocationManager(new LocationManager.OnLocationChangedListener() {
      @Override public void onLocationChanged(final Location location) {
        Log.d(TAG, "----------onLocationChanged-----------");
        runOnUiThread(new Runnable() {
          @Override public void run() {
            mTvLat.setText(String.valueOf(location.getLat()));
            mTvLon.setText(String.valueOf(location.getLon()));
          }
        });
      }
    });
    mRegistry.addObserver(mLocationManager);
  }

  @Override protected void initView() {

  }
}
