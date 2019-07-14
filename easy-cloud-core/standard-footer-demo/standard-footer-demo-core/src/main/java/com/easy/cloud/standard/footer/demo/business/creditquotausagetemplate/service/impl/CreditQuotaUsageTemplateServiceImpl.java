package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.service.impl;

import com.easy.cloud.standard.base.convert.BaseConvert;
import com.easy.cloud.standard.base.service.impl.BaseStandardServiceImpl;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.convert.CreditQuotaUsageTemplateConvert;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.entity.CreditQuotaUsageTemplateEntity;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.mapper.CreditQuotaUsageTemplateMapper;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.dto.CreditQuotaUsageTemplateDTO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.service.CreditQuotaUsageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 额度模板类型配置 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Service(value = "creditQuotaUsageTemplateService")
public class CreditQuotaUsageTemplateServiceImpl extends BaseStandardServiceImpl<CreditQuotaUsageTemplateMapper, CreditQuotaUsageTemplateEntity, CreditQuotaUsageTemplateDTO> implements CreditQuotaUsageTemplateService {
    @Autowired
    private CreditQuotaUsageTemplateMapper creditQuotaUsageTemplateMapper;


    @Override
    protected BaseConvert<CreditQuotaUsageTemplateEntity, CreditQuotaUsageTemplateDTO> convert() {
        return CreditQuotaUsageTemplateConvert.INSTANCE;
    }
}
