package com.byron.library.bean.db;

import com.byron.library.db.annotation.Column;
import com.byron.library.db.annotation.PrimaryKey;
import com.byron.library.db.annotation.TableName;

import java.io.Serializable;

@SuppressWarnings("serial")
@TableName(name = "table_type")
public class TypeVo implements Serializable {

    /**
     * 主键ID
     */
    @PrimaryKey(name = "_type_id", autoIncrement = true)
    private int type_id;

    @Column(name = "_type_title")
    private String title;
    @Column(name = "_createtime")
    private long creatTime;

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

}