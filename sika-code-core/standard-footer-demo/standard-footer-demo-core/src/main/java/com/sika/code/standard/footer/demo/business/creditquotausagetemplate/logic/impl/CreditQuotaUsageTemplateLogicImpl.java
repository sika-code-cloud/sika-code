package com.sika.code.standard.footer.demo.business.creditquotausagetemplate.logic.impl;

import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplateCommonQueryRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplateListQueryRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save.CreditQuotaUsageTemplateSaveBatchRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.update.CreditQuotaUsageTemplateUpdateByIdRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplateListQueryResponseBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.service.CreditQuotaUsageTemplateService;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.logic.CreditQuotaUsageTemplateLogic;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplatePageQueryRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save.CreditQuotaUsageTemplateSaveRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplatePageQueryResponseBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplateQueryResponseBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.save.CreditQuotaUsageTemplateSaveResponseBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.update.CreditQuotaUsageTemplateUpdateResponseBO;
import com.sika.code.standard.base.logic.BaseStandardLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 额度模板类型配置 逻辑实现类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Component(value = "creditQuotaUsageTemplateLogic")
public class CreditQuotaUsageTemplateLogicImpl extends BaseStandardLogic implements CreditQuotaUsageTemplateLogic {
    @Autowired
    private CreditQuotaUsageTemplateService creditQuotaUsageTemplateService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreditQuotaUsageTemplateSaveResponseBO save(CreditQuotaUsageTemplateSaveRequestBO request) {
        return request.execute();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreditQuotaUsageTemplateSaveResponseBO saveBatch(CreditQuotaUsageTemplateSaveBatchRequestBO requestBO) {
        return requestBO.execute();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CreditQuotaUsageTemplateUpdateResponseBO updateById(CreditQuotaUsageTemplateUpdateByIdRequestBO request) {
        return request.execute();
    }

    @Override
    public CreditQuotaUsageTemplatePageQueryResponseBO page(CreditQuotaUsageTemplatePageQueryRequestBO request) {
        return request.execute();
    }

    @Override
    public CreditQuotaUsageTemplateQueryResponseBO find(CreditQuotaUsageTemplateCommonQueryRequestBO request) {
        return request.execute();
    }

    @Override
    public CreditQuotaUsageTemplateListQueryResponseBO list(CreditQuotaUsageTemplateListQueryRequestBO request) {
        return request.execute();
    }
}