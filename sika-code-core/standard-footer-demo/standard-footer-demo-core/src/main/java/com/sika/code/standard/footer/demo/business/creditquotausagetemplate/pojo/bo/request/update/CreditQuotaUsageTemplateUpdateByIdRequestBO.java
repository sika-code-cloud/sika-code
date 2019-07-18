package com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.update;

import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.CreditQuotaUsageTemplateAlterRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.update.CreditQuotaUsageTemplateUpdateResponseBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.dto.CreditQuotaUsageTemplateDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置 普通更新请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditQuotaUsageTemplateUpdateByIdRequestBO extends CreditQuotaUsageTemplateAlterRequestBO<CreditQuotaUsageTemplateUpdateResponseBO> {
    /**
     * 根据id更新数据的请求对象
     */
    private CreditQuotaUsageTemplateUpdateByIdRequest creditQuotaUsageTemplate;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {
        // 校验数据是否存在，不存在抛出异常
        service().verifyExist(this.creditQuotaUsageTemplate.getCreditQuotaUsageTemplateId());
    }

    @Override
    protected CreditQuotaUsageTemplateUpdateResponseBO doExecute() {
        // 转换
        CreditQuotaUsageTemplateDTO creditQuotaUsageTemplateForUpdate = convert().convertToDTO(creditQuotaUsageTemplate);
        // 执行
        Boolean success = service().update(creditQuotaUsageTemplateForUpdate);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<CreditQuotaUsageTemplateUpdateResponseBO> responseClass() {
        return CreditQuotaUsageTemplateUpdateResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class CreditQuotaUsageTemplateUpdateByIdRequest extends CreditQuotaUsageTemplateAlterRequest {
        /**
         * 表数据id
         */
        private Long creditQuotaUsageTemplateId;
    }
}