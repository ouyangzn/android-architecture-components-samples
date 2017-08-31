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

package com.ouyangzn.simplesample.module.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.ouyangzn.lib.utils.ThreadUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions:
 */
public class ItemDataListViewModel extends ViewModel {

  private MutableLiveData<List<ItemData>> mData;

  public ItemDataListViewModel() {
    mData = new MutableLiveData<>();
  }

  @Override protected void onCleared() {
    super.onCleared();
  }

  public LiveData<List<ItemData>> getItemLiveData() {
    if (mData == null) mData = new MutableLiveData<>();
    return mData;
  }

  // LiveData中有数据的情况下,会马上触发onChanged通知
  //    public LiveData<List<ItemData>> getData(final int page, final int limit) {
  //        ThreadUtil.execute(new Runnable() {
  //            @Override
  //            public void run() {
  //                if (page != 0) {
  //                    ThreadUtil.sleep(2000);
  //                }
  //                mData.postValue(getTestData(page, limit));
  //            }
  //        });
  //        return mData;
  //    }

  public void getData(final int page, final int limit) {
    ThreadUtil.execute(new Runnable() {
      @Override public void run() {
        ThreadUtil.sleep(2000);
        // 如果还有观察此数据的对象则处理
        if (mData.hasObservers()) {
          mData.postValue(getTestData(page, limit));
        }
      }
    });
  }

  private List<ItemData> getTestData(int page, int limit) {
    Log.d(this.getClass().getSimpleName(), "-----------create test data------------");
    List<ItemData> list = new ArrayList<>(limit);
    ItemData data;
    for (int i = 0; i < limit; i++) {
      data = new ItemData();
      data.setTitle("第" + page + "页的title" + i);
      data.setContent("第" + page + "页的content" + i);
      list.add(data);
    }
    return list;
  }
}
