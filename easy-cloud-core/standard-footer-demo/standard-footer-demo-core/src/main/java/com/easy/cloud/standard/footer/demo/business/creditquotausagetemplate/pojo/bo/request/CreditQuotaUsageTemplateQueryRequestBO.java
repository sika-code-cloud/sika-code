package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request;

import com.easy.cloud.standard.base.pojo.bo.request.BaseStandardQueryRequestBO;
import com.easy.cloud.standard.base.pojo.bo.response.BaseStandardResponseBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.domain.CreditQuotaUsageTemplateDomain;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.query.CreditQuotaUsageTemplateQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置 抽象查询请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class CreditQuotaUsageTemplateQueryRequestBO<ResponseBO extends BaseStandardResponseBO> extends BaseStandardQueryRequestBO<ResponseBO> implements CreditQuotaUsageTemplateDomain {

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class CreditQuotaUsageTemplateQueryRequest extends CreditQuotaUsageTemplateQuery {

    }
}
