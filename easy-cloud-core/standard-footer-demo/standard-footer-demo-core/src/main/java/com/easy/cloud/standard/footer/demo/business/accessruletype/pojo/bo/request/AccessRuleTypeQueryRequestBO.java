package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request;

import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.query.AccessRuleTypeQuery;
import com.easy.cloud.standard.base.pojo.bo.request.BaseStandardQueryRequestBO;
import com.easy.cloud.standard.base.pojo.bo.response.BaseStandardResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.domain.AccessRuleTypeDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 抽象查询请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class AccessRuleTypeQueryRequestBO<ResponseBO extends BaseStandardResponseBO> extends BaseStandardQueryRequestBO<ResponseBO> implements AccessRuleTypeDomain {

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class AccessRuleTypeQueryRequest extends AccessRuleTypeQuery {

    }
}