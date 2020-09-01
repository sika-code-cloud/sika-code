package com.sika.code.database.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author daiqi
 * @ClassName : BaseDO
 * @Description : 所有持久化实体的基础类 --阿里规范手册建议实用DO为后缀--但是若包已do后缀结尾将报错--因此使用entity作为后缀
 * @date 2017年12月4日 下午12:47:39
 */
@Data
public class BaseEntity<PRIMARY extends Serializable, BY> {
    @TableId(type = IdType.AUTO)
    private PRIMARY id;
    @TableField(fill = FieldFill.INSERT)
    private BY createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createDate;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private BY updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateDate;
    @Version
    private Integer version;
    /**
     * 删除标志
     */
    @TableLogic
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
