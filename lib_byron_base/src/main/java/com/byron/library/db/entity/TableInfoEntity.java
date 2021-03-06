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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: ethan.qiu@sosino.com
 * @date: 2013-6-25
 */
public class TableInfoEntity implements Serializable {
    private static final long serialVersionUID = 488168612576359150L;
    /**
     */
    private String tableName = "";
    /**
     */
    private String className = "";
    /**
     */
    private PKPropertyEntity pkProperyEntity = null;
    /**
     */
    ArrayList<PropertyEntity> propertieArrayList = new ArrayList<PropertyEntity>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<PropertyEntity> getPropertieArrayList() {
        return propertieArrayList;
    }

    public void setPropertieArrayList(List<PropertyEntity> propertyList) {
        this.propertieArrayList = (ArrayList<PropertyEntity>) propertyList;
    }

    public PKPropertyEntity getPkProperyEntity() {
        return pkProperyEntity;
    }

    public void setPkProperyEntity(PKPropertyEntity pkProperyEntity) {
        this.pkProperyEntity = pkProperyEntity;
    }

}
