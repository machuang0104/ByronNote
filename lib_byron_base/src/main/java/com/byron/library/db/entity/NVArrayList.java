/*
 * Copyright (C) 2013  ethan.qiu@sosino.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.byron.library.db.entity;

import com.byron.library.db.StringUtils;
import com.byron.library.db.apache.BasicNameValuePair;
import com.byron.library.db.apache.NameValuePair;

import java.util.ArrayList;

/**
 * @author: ethan.qiu@sosino.com
 * @date: 2013-6-21
 */
public class NVArrayList extends ArrayList<NameValuePair> {

    private static final long serialVersionUID = 1L;

    @Override
    public boolean add(NameValuePair nameValuePair) {
        if (!StringUtils.isEmpty(nameValuePair.getValue())) {
            return super.add(nameValuePair);
        } else {
            return false;
        }
    }

    /**
     * 添加数据
     *
     * @param key
     * @param value
     * @return
     */
    public boolean add(String key, String value) {
        return add(new BasicNameValuePair(key, value));
    }

}