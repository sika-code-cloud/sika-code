package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.domain;

import com.easy.cloud.standard.base.pojo.domain.BaseStandardDomain;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.convert.CreditQuotaUsageTemplateConvert;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.logic.CreditQuotaUsageTemplateLogic;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.service.CreditQuotaUsageTemplateService;

/**
 * <p>
 * 额度模板类型配置 领域类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
public interface CreditQuotaUsageTemplateDomain extends BaseStandardDomain {

    /**
     * <p>
     * 额度模板类型配置service
     * </p>
     *
     * @return CreditQuotaUsageTemplateService
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    default CreditQuotaUsageTemplateService service() {
        return getBean(CreditQuotaUsageTemplateService.class);
    }

    /**
     * <p>
     * 额度模板类型配置logic
     * </p>
     *
     * @return CreditQuotaUsageTemplateLogic
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    default CreditQuotaUsageTemplateLogic logic() {
        return getBean(CreditQuotaUsageTemplateLogic.class);
    }

    /**
     * <p>
     * 额度模板类型配置convert
     * </p>
     *
     * @return CreditQuotaUsageTemplateConvert
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    default CreditQuotaUsageTemplateConvert convert() {
        return CreditQuotaUsageTemplateConvert.INSTANCE;
    }
}
