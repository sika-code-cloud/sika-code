package com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request;

import com.sika.code.standard.footer.demo.business.accessruletype.pojo.query.AccessRuleTypeQuery;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardQueryRequestBO;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.domain.AccessRuleTypeDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 抽象查询请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
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