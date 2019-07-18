package com.sika.code.standard.footer.demo.business.creditquotausagetemplate.controller;


import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.logic.CreditQuotaUsageTemplateLogic;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplateCommonQueryRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplateListQueryRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplatePageQueryRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save.CreditQuotaUsageTemplateSaveBatchRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save.CreditQuotaUsageTemplateSaveRequestBO;
import com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.update.CreditQuotaUsageTemplateUpdateByIdRequestBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 额度模板类型配置 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:03:29
 */
@RestController(value = "creditQuotaUsageTemplateController")
@RequestMapping("credit_quota_usage_template")
public class CreditQuotaUsageTemplateController extends BaseStandardController {
    @Autowired
    private CreditQuotaUsageTemplateLogic creditQuotaUsageTemplateLogic;

    @RequestMapping(value = "save")
    public Result save(@RequestBody CreditQuotaUsageTemplateSaveRequestBO request) {
        return super.generateResult(creditQuotaUsageTemplateLogic.save(request));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody CreditQuotaUsageTemplateSaveBatchRequestBO request) {
        return super.generateResult(creditQuotaUsageTemplateLogic.saveBatch(request));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody CreditQuotaUsageTemplateUpdateByIdRequestBO request) {
        return super.generateResult(creditQuotaUsageTemplateLogic.updateById(request));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody CreditQuotaUsageTemplatePageQueryRequestBO request) {
        return super.generateResult(creditQuotaUsageTemplateLogic.page(request));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody CreditQuotaUsageTemplateCommonQueryRequestBO request) {
        return super.generateResult(creditQuotaUsageTemplateLogic.find(request));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody CreditQuotaUsageTemplateListQueryRequestBO request) {
        return super.generateResult(creditQuotaUsageTemplateLogic.list(request));
    }
}
