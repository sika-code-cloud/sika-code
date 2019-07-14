package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query;

import com.easy.cloud.standard.base.pojo.bo.response.BaseStandardResponseBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.domain.CreditQuotaUsageTemplateDomain;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.dto.CreditQuotaUsageTemplateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 额度模板类型配置 普通查询响应类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@Accessors(chain = true)
public class CreditQuotaUsageTemplateListQueryResponseBO implements BaseStandardResponseBO<List<CreditQuotaUsageTemplateDTO>>, CreditQuotaUsageTemplateDomain {
    /**
     * 返回给页面的响应对象
     */
    private List<CreditQuotaUsageTemplateListQueryResponse> creditQuotaUsageTemplates;

    @Override
    public void build(List<CreditQuotaUsageTemplateDTO> creditQuotaUsageTemplates) {
        this.creditQuotaUsageTemplates = convert().convertToListQueryResponses(creditQuotaUsageTemplates);
    }

    /**
     * <p>
     * 响应类 : 封装响应数据
     * </p>
     *
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class CreditQuotaUsageTemplateListQueryResponse extends CreditQuotaUsageTemplateDTO {

    }
}