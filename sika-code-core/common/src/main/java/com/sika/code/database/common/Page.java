package com.sika.code.database.common;

import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * User : sikadai
 * Date : 2017/1/13
 * Time : 18:36
 */
@Data
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 34234234234985L;
    /**
     * 数据总数
     */
    private int total;
    /**
     * 每页的数量
     */
    private int pageSize;
    /**
     * 页面的总数
     */
    private int pageCount;
    /**
     * 当前页面编号
     */
    private int pageNum;
    /**
     * 当前数据
     */
    private int currentSize;
    private boolean isHasNextPage;
    private boolean isHasPreviousPage;
    private List<T> list;


    public Page() {
        super();
    }

    public Page(Integer pageNum, Integer pageSize, Integer total, List<T> list) {
        pageSize = 0 == pageSize ? null : pageSize;
        Assert.notNull(pageNum, "pageNum");
        Assert.notNull(pageSize, "pageSize");
        Assert.notNull(total, "total");
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        if (this.total == 0) {
            this.pageCount = 0;
        } else {
            int i = total % pageSize;
            int count = total / pageSize;
            this.pageCount = i == 0 ? count : count + 1;
        }
        if (list == null) {
            this.currentSize = 0;
        } else {
            this.currentSize = list.size();
        }
        this.list = list;
        this.isHasPreviousPage = pageNum != 1;
        this.isHasNextPage = pageNum < pageCount;
    }

    public Page(PageQuery pageQuery, Integer total, List<T> list) {
        this(pageQuery.getPageNum(), pageQuery.getPageSize(), total, list);
    }

    public Page(Page page, List<T> list) {
        this(page.getPageNum(), page.getPageSize(), page.getTotal(), list);
    }
}
