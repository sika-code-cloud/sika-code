package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query;

import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.CreditQuotaUsageTemplateQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplateQueryResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置 普通查询请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditQuotaUsageTemplateCommonQueryRequestBO extends CreditQuotaUsageTemplateQueryRequestBO<CreditQuotaUsageTemplateQueryResponseBO> {
    /**
     * 查询请求对象
     */
    private CreditQuotaUsageTemplateCommonQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected CreditQuotaUsageTemplateQueryResponseBO doExecute() {
        return newResponseBO(this, service().find(queryRequest));
    }

    @Override
    public Class<CreditQuotaUsageTemplateQueryResponseBO> responseClass() {
        return CreditQuotaUsageTemplateQueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class CreditQuotaUsageTemplateCommonQueryRequest extends CreditQuotaUsageTemplateQueryRequest {

    }
}