package com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query;

import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.CreditQuotaUsageTemplateQueryRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplateListQueryResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置 列表请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditQuotaUsageTemplateListQueryRequestBO extends CreditQuotaUsageTemplateQueryRequestBO<CreditQuotaUsageTemplateListQueryResponseBO> {
    /**
     * 查询请求对象
     */
    private CreditQuotaUsageTemplateListQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected CreditQuotaUsageTemplateListQueryResponseBO doExecute() {
        return newResponseBO(this, service().list(queryRequest));
    }

    @Override
    public Class<CreditQuotaUsageTemplateListQueryResponseBO> responseClass() {
        return CreditQuotaUsageTemplateListQueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class CreditQuotaUsageTemplateListQueryRequest extends CreditQuotaUsageTemplateQueryRequest {

    }
}