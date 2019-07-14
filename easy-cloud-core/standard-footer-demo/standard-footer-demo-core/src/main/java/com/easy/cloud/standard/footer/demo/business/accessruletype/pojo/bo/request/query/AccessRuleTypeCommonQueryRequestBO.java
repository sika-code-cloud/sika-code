package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query;

import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.AccessRuleTypeQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeQueryResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 普通查询请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccessRuleTypeCommonQueryRequestBO extends AccessRuleTypeQueryRequestBO<AccessRuleTypeQueryResponseBO> {
    /**
     * 查询请求对象
     */
    private AccessRuleTypeCommonQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected AccessRuleTypeQueryResponseBO doExecute() {
        return newResponseBO(this, service().find(queryRequest));
    }

    @Override
    public Class<AccessRuleTypeQueryResponseBO> responseClass() {
        return AccessRuleTypeQueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class AccessRuleTypeCommonQueryRequest extends AccessRuleTypeQueryRequest {

    }
}