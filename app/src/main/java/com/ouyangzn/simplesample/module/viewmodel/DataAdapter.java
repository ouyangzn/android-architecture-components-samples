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

import com.ouyangzn.recyclerview.BaseRecyclerViewAdapter;
import com.ouyangzn.recyclerview.BaseViewHolder;
import com.ouyangzn.simplesample.R;

import java.util.List;

/**
 * Created by ouyangzn on 2017/8/28.
 * Descriptions:
 */
public class DataAdapter extends BaseRecyclerViewAdapter<ItemData> {

    public DataAdapter(List<ItemData> data) {
        super(R.layout.item_list_main, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, ItemData item) {
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_content, item.getContent());
    }
}
