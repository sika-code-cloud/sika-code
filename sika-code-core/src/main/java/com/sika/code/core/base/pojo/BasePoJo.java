package com.sika.code.core.base.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author daiqi
 * @create 2021-10-13 23:29
 */
@Data
public abstract class BasePoJo<PRIMARY extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 更新日期
     */
    private Date updateDate;
    /**
     * 版本管理标志
     */
    private Integer version;
    /**
     * 数据版本号-若逻辑删除-则该数据与ID保持一致
     */
//    private PRIMARY dataVersion;
    /**
     * 可用标志", remark = "1:可用,0:不可用
     */
    private Integer available;
    /**
     * 逻辑删除标志", remark = "1:删除,0:未删
     */
    private Integer isDeleted;
    /**
     * 备注
     */
    private String remark;

    public abstract PRIMARY getId() ;

    public abstract void setId(PRIMARY id);
}
