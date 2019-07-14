package com.easy.cloud.standard.footer.demo.business.accessruletype.controller;

import com.easy.cloud.result.Result;
import com.easy.cloud.standard.base.controller.BaseStandardController;
import com.easy.cloud.standard.footer.demo.business.accessruletype.logic.AccessRuleTypeLogic;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeCommonQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeListQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypePageQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveBatchRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.update.AccessRuleTypeUpdateByIdRequestBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * 准入规则类型表 前端控制器
 * </p>
 *
 * @author daiqi
 * @since 2019-06-21 23:12:36
 */
@RestController(value = "accessRuleTypeController")
@RequestMapping("access_rule_type")
public class AccessRuleTypeController extends BaseStandardController {
    @Autowired
    private AccessRuleTypeLogic accessRuleTypeLogic;

    @RequestMapping(value = "save")
    public Result save(@RequestBody AccessRuleTypeSaveRequestBO request) {
        return super.generateResult(accessRuleTypeLogic.save(request));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody AccessRuleTypeSaveBatchRequestBO request) {
        return super.generateResult(accessRuleTypeLogic.saveBatch(request));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody AccessRuleTypeUpdateByIdRequestBO request) {
        return super.generateResult(accessRuleTypeLogic.updateById(request));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody AccessRuleTypePageQueryRequestBO request) {
        return super.generateResult(accessRuleTypeLogic.page(request));
    }

    @RequestMapping(value = "find")
    public Result find(AccessRuleTypeCommonQueryRequestBO request) {
        System.out.println("请求头为:" + super.request.getHeader("appSecret"));
        return super.generateResult(accessRuleTypeLogic.find(request));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody AccessRuleTypeListQueryRequestBO request) {
        return super.generateResult(accessRuleTypeLogic.list(request));
    }
}
