package com.sika.code.standard.footer.demo.business.accessruletype.entity;

import java.io.Serializable;
import com.sika.code.standard.base.pojo.entity.BaseStandardEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:29:17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("risk_system_access_rule_type")
public class AccessRuleTypeEntity extends BaseStandardEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 规则类型 [详情规则类型文档]
     */
    private Integer ruleType;

    /**
     * 是否展示 [0:不展示 1:展示]
     */
    private Integer showFlag;

    /**
     * 排序编号 序号越小，排序越靠前
     */
    private Integer orderNum;

    /**
     * 准入优先级，序号越小优先级越高
     */
    private Integer priority;


}
