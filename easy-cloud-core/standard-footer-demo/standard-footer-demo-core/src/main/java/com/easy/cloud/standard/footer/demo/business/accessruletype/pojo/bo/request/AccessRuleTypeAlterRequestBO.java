package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request;

import java.io.Serializable;
import com.easy.cloud.standard.base.pojo.bo.request.BaseStandardAlterRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.domain.AccessRuleTypeDomain;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.AccessRuleTypeAlterResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 修改请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class AccessRuleTypeAlterRequestBO<ResponseBO extends AccessRuleTypeAlterResponseBO> extends BaseStandardAlterRequestBO<ResponseBO> implements AccessRuleTypeDomain {

    @Data
    @Accessors(chain = true)
    public static class AccessRuleTypeAlterRequest extends BaseStandardAlterRequest {
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
}
