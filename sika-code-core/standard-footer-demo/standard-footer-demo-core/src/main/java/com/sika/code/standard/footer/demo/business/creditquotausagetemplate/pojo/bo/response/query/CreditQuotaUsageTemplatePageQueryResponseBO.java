package com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query;

import com.sika.code.database.common.Page;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.domain.CreditQuotaUsageTemplateDomain;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.dto.CreditQuotaUsageTemplateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置 分页查询响应类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@Accessors(chain = true)
public class CreditQuotaUsageTemplatePageQueryResponseBO implements BaseStandardResponseBO<Page<CreditQuotaUsageTemplateDTO>>, CreditQuotaUsageTemplateDomain {

    /**
     * 分页响应对象
     */
    private Page<CreditQuotaUsageTemplatePageQueryResponse> page;

    @Override
    public void build(Page<CreditQuotaUsageTemplateDTO> page) {
        this.page = new Page<>(page, convert().convertToPageQueryResponses(page.getList()));
    }

    /**
     * <p>
     * 响应类 封装响应数据
     * </p>
     *
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class CreditQuotaUsageTemplatePageQueryResponse extends CreditQuotaUsageTemplateDTO {

    }
}