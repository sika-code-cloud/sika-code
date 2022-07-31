package com.sika.code.core.base.pojo.query;

import lombok.Data;

import java.io.Serializable;

/**
 * User : sikadai
 * Date : 2017/1/23
 * Time : 13:17
 */
@Data
public class PageQuery<PRIMARY extends Serializable> extends BaseQuery<PRIMARY> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer start;
    private String sortColumn;
    private String sortType;
    private PRIMARY startIndex;

    public PageQuery() {
        super();
        this.pageNum = (this.pageNum == null || this.pageNum <= 0) ? 1 : this.pageNum;
        this.pageSize = (this.pageSize == null || this.pageSize <= 0) ? 10 : this.pageSize;
    }

    public Integer getStart() {
        if (pageNum == 1) {
            return 0;
        } else {
            return (pageNum - 1) * pageSize;
        }
    }
}
