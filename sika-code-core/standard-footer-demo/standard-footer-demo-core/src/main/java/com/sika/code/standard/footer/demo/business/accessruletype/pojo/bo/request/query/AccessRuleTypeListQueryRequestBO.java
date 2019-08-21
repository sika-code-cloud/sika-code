package com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.query;

import com.sika.code.basic.util.Assert;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.AccessRuleTypeQueryRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeListQueryResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 准入规则类型表 普通查询请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccessRuleTypeListQueryRequestBO extends AccessRuleTypeQueryRequestBO<AccessRuleTypeListQueryResponseBO> {
    /**
    * 查询请求对象
    */
    private AccessRuleTypeListQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected AccessRuleTypeListQueryResponseBO doExecute() {
    return newResponseBO(this, service().list(queryRequest));
    }

    @Override
    public Class<AccessRuleTypeListQueryResponseBO> responseClass() {
        return AccessRuleTypeListQueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class AccessRuleTypeListQueryRequest extends AccessRuleTypeQueryRequest {
        private List<String> accessRuleTypeIds;
    }
}