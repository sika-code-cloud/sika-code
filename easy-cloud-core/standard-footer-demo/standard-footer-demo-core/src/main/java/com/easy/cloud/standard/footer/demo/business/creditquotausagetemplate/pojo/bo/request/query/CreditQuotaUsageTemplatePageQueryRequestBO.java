package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query;

import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.CreditQuotaUsageTemplateQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplatePageQueryResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置 分页查询请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditQuotaUsageTemplatePageQueryRequestBO extends CreditQuotaUsageTemplateQueryRequestBO<CreditQuotaUsageTemplatePageQueryResponseBO> {
    /**
     * 查询请求对象
     */
    private CreditQuotaUsageTemplatePageQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected CreditQuotaUsageTemplatePageQueryResponseBO doExecute() {
        return newResponseBO(this, service().page(queryRequest));
    }

    @Override
    public Class<CreditQuotaUsageTemplatePageQueryResponseBO> responseClass() {
        return CreditQuotaUsageTemplatePageQueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class CreditQuotaUsageTemplatePageQueryRequest extends CreditQuotaUsageTemplateQueryRequest {

    }
}