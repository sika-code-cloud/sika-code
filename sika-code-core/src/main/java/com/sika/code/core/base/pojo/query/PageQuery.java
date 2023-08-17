package com.sika.code.core.base.pojo.query;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User : sikadai
 * Date : 2017/1/23
 * Time : 13:17
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class PageQuery extends BaseQuery {
    protected Integer pageNum;
    protected Integer pageSize;
    protected Integer start;
    protected String sortColumn;
    protected String sortType;
    protected Long startIndex;

    public PageQuery() {
        super();
        buildParam();
    }

    public PageQuery(Integer pageSize) {
        this.pageSize = pageSize;
        buildParam();
    }
    public PageQuery(Long startIndex) {
        this.startIndex = startIndex;
        buildParam();
    }

    protected void buildParam() {
        this.pageNum = (this.pageNum == null || this.pageNum <= 0) ? 1 : this.pageNum;
        this.pageSize = (this.pageSize == null || this.pageSize <= 0) ? 10 : this.pageSize;
        this.startIndex = (this.startIndex == null || this.startIndex <= 0) ? 0 : this.startIndex;
    }

    public Integer getStart() {
        if (pageNum == 1) {
            return 0;
        } else {
            return (pageNum - 1) * pageSize;
        }
    }
}
