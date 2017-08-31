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

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import butterknife.BindView;
import com.ouyangzn.simplesample.R;
import com.ouyangzn.simplesample.base.BaseLifecycleActivity;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions:
 */
public class TestLiveDataActivity extends BaseLifecycleActivity implements Observer<TestData> {

  @BindView(R.id.tv_test_live_data) TextView mTvData;
  private DataLiveData mDataLiveData;

  @Override protected int getContentView() {
    return R.layout.activity_test_live_data;
  }

  @Override protected void initData() {
    mDataLiveData = new DataLiveData();
    mDataLiveData.observe(this, this);
  }

  @Override protected void initView() {

  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override public void onChanged(@Nullable TestData data) {
    Log.d(TAG, "-----------onChanged---------");
    mTvData.setText(data != null ? data.getContent() : "无数据");
  }
}
