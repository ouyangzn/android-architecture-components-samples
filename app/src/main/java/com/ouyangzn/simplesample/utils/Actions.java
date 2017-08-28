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

package com.ouyangzn.simplesample.utils;

import android.content.Intent;

import com.ouyangzn.simplesample.base.BaseActivity;
import com.ouyangzn.simplesample.module.lifecycle.TestLifeCycleActivity;
import com.ouyangzn.simplesample.module.livedata.TestLiveDataActivity;
import com.ouyangzn.simplesample.module.viewmodel.TestViewModelActivity;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions:
 */
public class Actions {

    public static void goTestLifeCycle(BaseActivity activity) {
        activity.startActivity(new Intent(activity, TestLifeCycleActivity.class));
    }

    public static void goTestLiveData(BaseActivity activity) {
        activity.startActivity(new Intent(activity, TestLiveDataActivity.class));
    }

    public static void goTestViewModel(BaseActivity activity) {
        activity.startActivity(new Intent(activity, TestViewModelActivity.class));
    }
}
