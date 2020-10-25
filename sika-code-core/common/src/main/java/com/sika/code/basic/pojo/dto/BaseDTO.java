package com.sika.code.basic.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础数据传输对象
 *
 * @author daiqi 创建日期 2018年1月6日 下午3:36:13
 */
@Data
public class BaseDTO<BY> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 创建人的id
     */
    private BY createBy;
    /**
     * 最后更新人标识，记录用户的id
     */
    private BY updateBy;
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

}
