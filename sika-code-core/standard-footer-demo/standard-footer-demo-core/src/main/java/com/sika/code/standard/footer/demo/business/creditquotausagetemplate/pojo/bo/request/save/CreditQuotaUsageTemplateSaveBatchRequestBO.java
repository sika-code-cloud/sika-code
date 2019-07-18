package com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save;

import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.CreditQuotaUsageTemplateAlterRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.save.CreditQuotaUsageTemplateSaveResponseBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.dto.CreditQuotaUsageTemplateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

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
public class CreditQuotaUsageTemplateSaveBatchRequestBO extends CreditQuotaUsageTemplateAlterRequestBO<CreditQuotaUsageTemplateSaveResponseBO> {
    /**
     * 批量保存请求对象列表
     */
    private List<CreditQuotaUsageTemplateSaveBatchRequest> creditQuotaUsageTemplates;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected CreditQuotaUsageTemplateSaveResponseBO doExecute() {
        // 转换
        List<CreditQuotaUsageTemplateDTO> creditQuotaUsageTemplatesForSave = convert().convertToDTOFromSaveBatchRequests(this.creditQuotaUsageTemplates);
        // 保存
        boolean success = service().saveForBatch(creditQuotaUsageTemplatesForSave);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<CreditQuotaUsageTemplateSaveResponseBO> responseClass() {
        return CreditQuotaUsageTemplateSaveResponseBO.class;
    }


    @Data
    @Accessors(chain = true)
    public static class CreditQuotaUsageTemplateSaveBatchRequest extends CreditQuotaUsageTemplateAlterRequest {

    }
}