package com.sika.code.standard.footer.demo.business.accessruletype.pojo.domain;

import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;
import com.sika.code.common.spring.SpringUtil;
import com.sika.code.standard.footer.demo.business.accessruletype.convert.AccessRuleTypeConvert;
import com.sika.code.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;
import com.sika.code.standard.footer.demo.business.accessruletype.logic.AccessRuleTypeLogic;

/**
 * <p>
 * 准入规则类型表 领域类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
 */
public interface AccessRuleTypeDomain extends BaseStandardDomain {
    /**
     * <p>
     * 返回当前模块的service
     * </p>
     *
     * @return AccessRuleTypeService
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    default AccessRuleTypeService service() {
        return getBean(AccessRuleTypeService.class);
    }

    /**
     * <p>
     * 返回当前模块的Logic
     * </p>
     *
     * @return AccessRuleTypeLogic
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    default AccessRuleTypeLogic logic() {
        return getBean(AccessRuleTypeLogic.class);
    }

    /**
     * <p>
     * 获取当前模块的转化器对象
     * </p>
     *
     * @return AccessRuleTypeConvert
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    default AccessRuleTypeConvert convert() {
        return AccessRuleTypeConvert.INSTANCE;
    }
}
