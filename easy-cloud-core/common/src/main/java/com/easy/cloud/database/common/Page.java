package com.easy.cloud.database.common;

import lombok.Data;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;

/**
 * User : LiuKe
 * Date : 2017/1/13
 * Time : 18:36
 */
@Data
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 34234234234985L;
    private int total;
    private int pageSize;
    private int pageCount;
    private int pageNum;
    private boolean isHasNextPage;
    private boolean isHasPreviousPage;
    private List<T> list;


    public Page() {
        super();
    }

    public Page(Integer pageNum, Integer pageSize, Integer total) {
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
        this.isHasPreviousPage = pageNum == 1 ? false : true;
        this.isHasNextPage = pageNum >= pageCount ? false : true;
    }

    public Page(PageQuery pageQuery, Integer total) {
        this(pageQuery.getPageNum(), pageQuery.getPageSize(), total);
    }

    public Page(PageQuery pageQuery, Integer total, List<T> list) {

        this(pageQuery.getPageNum(), pageQuery.getPageSize(), total);
        this.setList(list);
    }

    public Page(Page page, List<T> list) {
        this.total = page.getTotal();
        this.pageSize = page.getPageSize();
        this.pageNum = page.getPageNum();
        this.isHasPreviousPage = page.isHasPreviousPage();
        this.isHasNextPage = page.isHasPreviousPage();
        this.list = list;
    }

//    public Page(List<T> list) {
//        PageInfo pageInfo = new PageInfo(list);
//        this.total = pageInfo.getTotal();
//        this.pageSize = pageInfo.getPageSize();
//        this.pageNum = pageInfo.getPageNum();
//        this.pageCount = pageInfo.getPages();
//        this.isHasPreviousPage = pageInfo.isHasPreviousPage();
//        this.isHasNextPage = pageInfo.isHasPreviousPage();
//        this.list = pageInfo.getList();
//    }
}
