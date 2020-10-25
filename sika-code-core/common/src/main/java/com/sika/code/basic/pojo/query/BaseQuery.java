package com.sika.code.basic.pojo.query;

import lombok.Data;

import java.util.Date;

/**
 * 基础查询对象
 *
 * @author daiqi
 * 创建日期 2018年1月6日 下午3:43:09
 */
@Data
public class BaseQuery<PRIMARY, BY> {
    /**
     * 表id
     */
    private PRIMARY id;
    private BY createBy;
    private BY updateBy;
    private Date createDate;
    private Date updateDate;
    private Integer version;
    /**
     * 删除标志
     */
    private Integer isDeleted;
    /**
     * 可用标志
     */
    private Integer available;
    /**
     * 备注
     */
    private String remark;

}
