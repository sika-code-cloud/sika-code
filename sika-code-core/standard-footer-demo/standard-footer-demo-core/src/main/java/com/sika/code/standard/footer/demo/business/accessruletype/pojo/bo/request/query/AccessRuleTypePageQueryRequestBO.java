package com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.query;

import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.AccessRuleTypeQueryRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypePageQueryResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * 准入规则类型表 分页查询请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccessRuleTypePageQueryRequestBO extends AccessRuleTypeQueryRequestBO<AccessRuleTypePageQueryResponseBO> {
    /**
     * 查询请求对象
     */
    private AccessRuleTypePageQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected AccessRuleTypePageQueryResponseBO doExecute() {
        return newResponseBO(this, service().page(queryRequest));
    }

    @Override
    public Class<AccessRuleTypePageQueryResponseBO> responseClass() {
        return AccessRuleTypePageQueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class AccessRuleTypePageQueryRequest extends AccessRuleTypeQueryRequest {

    }
}