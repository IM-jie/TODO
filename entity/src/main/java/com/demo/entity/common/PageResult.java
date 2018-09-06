package com.demo.entity.common;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @program: parent
 * @description: 分页实体类
 * @author: zouweidong
 * @create: 2018-08-29 11:50
 **/
public class PageResult <T> implements Serializable {
    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6477868212171605239L;

    /**
     * 总记录数
     */
    private Long total;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 每页记录数
     */
    private Integer pageSize;

    /**
     * 总页数
     */
    private Integer pages;

    /**
     * 结果集
     */
    private List<T> data;

    public PageResult(List<T> data) {
        init(data);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    /**
     * 初始化
     *
<<<<<<< HEAD
     * @param：rows
=======
     * @param data
>>>>>>> a388e65fed368697a8b122495edae41f5d3a8756
     */
    private void init(List<T> data) {
        if (data instanceof Page) {
            Page<T> page = (Page<T>) data;
            this.total = page.getTotal();
            this.data = page.getResult();
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
        }
    }
}
