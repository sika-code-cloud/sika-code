package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save;

import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.CreditQuotaUsageTemplateAlterRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.save.CreditQuotaUsageTemplateSaveResponseBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.dto.CreditQuotaUsageTemplateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置 保存请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditQuotaUsageTemplateSaveRequestBO extends CreditQuotaUsageTemplateAlterRequestBO<CreditQuotaUsageTemplateSaveResponseBO> {
    /**
     * 保存请求对象
     */
    private CreditQuotaUsageTemplateSaveRequest creditQuotaUsageTemplate;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected CreditQuotaUsageTemplateSaveResponseBO doExecute() {
        // 转换
        CreditQuotaUsageTemplateDTO creditQuotaUsageTemplateForSave = convert().convertToDTO(this.creditQuotaUsageTemplate);
        // 保存
        boolean success = service().save(creditQuotaUsageTemplateForSave);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<CreditQuotaUsageTemplateSaveResponseBO> responseClass() {
        return CreditQuotaUsageTemplateSaveResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class CreditQuotaUsageTemplateSaveRequest extends CreditQuotaUsageTemplateAlterRequest {

    }
}