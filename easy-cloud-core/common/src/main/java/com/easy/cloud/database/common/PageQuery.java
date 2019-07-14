package com.easy.cloud.database.common;

import com.easy.cloud.basic.pojo.query.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * User : LiuKe
 * Date : 2017/1/23
 * Time : 13:17
 */
@Data
public class PageQuery<PRIMARY, BY> extends BaseQuery<PRIMARY, BY>{
    private Integer pageNum;
    private Integer pageSize;
    private Integer start;
    private String sortColumn;
    private String sortType;

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
