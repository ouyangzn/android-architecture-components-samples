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
package com.ouyangzn.simplesample;

import android.support.annotation.LayoutRes;
import android.view.View;

import com.ouyangzn.simplesample.base.BaseActivity;
import com.ouyangzn.simplesample.utils.Actions;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected @LayoutRes int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

    }

    @OnClick({ R.id.btn_test_live_data, R.id.btn_test_view_model, R.id.btn_test_life_cycle }) public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_test_life_cycle:
                Actions.goTestLifeCycle(this);
                break;
            case R.id.btn_test_live_data:
                Actions.goTestLiveData(this);
                break;
            case R.id.btn_test_view_model:
                Actions.goTestViewModel(this);
                break;
        }
    }

}
