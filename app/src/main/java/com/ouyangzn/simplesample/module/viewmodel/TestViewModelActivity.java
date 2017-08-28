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

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ouyangzn.recyclerview.BaseRecyclerViewAdapter;
import com.ouyangzn.simplesample.R;
import com.ouyangzn.simplesample.base.BaseActivity;
import com.ouyangzn.simplesample.base.BaseLifecycleActivity;
import com.ouyangzn.simplesample.utils.UiUtils;

import java.util.List;

import butterknife.BindView;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions:
 */
public class TestViewModelActivity extends BaseLifecycleActivity implements BaseRecyclerViewAdapter.OnLoadingMoreListener {
    private static final int LIMIT = 10;
    private static final int PAGE_FIRST = 0;

    @BindView(R.id.recycler_test_view_model) RecyclerView mRecyclerView;
    @BindView(R.id.refresh_test_view_model) SwipeRefreshLayout mRefreshLayout;
    private DataAdapter mAdapter;
    private ItemDataListViewModel mListViewModel;

    private int mPage = PAGE_FIRST;

    @Override
    protected @LayoutRes
    int getContentView() {
        return R.layout.activity_test_view_model;
    }

    @Override
    protected void initData() {
        mAdapter = new DataAdapter(null);
        mAdapter.setOnLoadingMoreListener(this);
        mListViewModel = ViewModelProviders.of(this).get(ItemDataListViewModel.class);
        mListViewModel.getItemLiveData().observe(this, new Observer<List<ItemData>>() {
            @Override
            public void onChanged(@Nullable List<ItemData> items) {
                UiUtils.stopRefresh(mRefreshLayout);
                Log.d(TAG, "----------ViewModel.onChanged---------");
                boolean hasMore = items != null && items.size() >= LIMIT;
                if (mAdapter.isLoadingMore()) {
                    mAdapter.loadMoreFinish(hasMore, items);
                } else {
                    mAdapter.setHasMore(hasMore);
                    mAdapter.resetData(items);
                }
            }
        });
        queryItemData(true);
    }

    @Override
    protected void initView() {
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                queryItemData(true);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
        UiUtils.setRecyclerViewLoadMore(mAdapter, mRecyclerView);
    }

    @Override
    public void requestMoreData() {
        queryItemData(false);
    }

    private void queryItemData(boolean refresh) {
        if (refresh) {
            mPage = PAGE_FIRST;
        } else {
            mPage++;
        }
        mListViewModel.getData(mPage, LIMIT);
    }
}
