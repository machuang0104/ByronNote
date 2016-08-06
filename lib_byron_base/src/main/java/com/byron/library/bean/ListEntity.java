package com.byron.library.bean;

import java.io.Serializable;
import java.util.ArrayList;


public class ListEntity<T> implements Serializable {

    private static final long serialVersionUID = 1090686338072092877L;

    /**
     */
    private int total;
    /**
     */
    private ArrayList<T> list;

    /**
     */
    private int pageNo;
    /**
     */
    private int pageCount;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    @Override
    public String toString() {
        return "ListEntity [total=" + total + ", list=" + list + ", pageNo="
                + pageNo + ", pageCount=" + pageCount + "]";
    }

}