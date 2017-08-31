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

package com.ouyangzn.simplesample.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions:
 */
public abstract class BaseActivity extends AppCompatActivity {

  protected String TAG = "BaseActivity";

  private Unbinder mBind;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getContentView());
    setTitle(getClass().getSimpleName());
    TAG = getClass().getSimpleName();
    mBind = ButterKnife.bind(this);
    initData();
    initView();
  }

  protected abstract @LayoutRes int getContentView();

  protected abstract void initData();

  protected abstract void initView();

  @Override protected void onDestroy() {
    mBind.unbind();
    super.onDestroy();
  }
}
