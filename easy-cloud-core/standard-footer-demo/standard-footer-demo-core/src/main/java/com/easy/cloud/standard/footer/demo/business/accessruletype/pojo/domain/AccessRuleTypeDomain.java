package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.domain;

import com.easy.cloud.standard.base.pojo.domain.BaseStandardDomain;
import com.easy.cloud.common.spring.SpringUtil;
import com.easy.cloud.standard.footer.demo.business.accessruletype.convert.AccessRuleTypeConvert;
import com.easy.cloud.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;
import com.easy.cloud.standard.footer.demo.business.accessruletype.logic.AccessRuleTypeLogic;

/**
 * <p>
 * 准入规则类型表 领域类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
public interface AccessRuleTypeDomain extends BaseStandardDomain {
    /**
     * <p>
     * 返回当前模块的service
     * </p>
     *
     * @return AccessRuleTypeService
     * @author daiqi
     * @date 2019-06-22 00:32:04
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
     * @date 2019-06-22 00:32:04
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
     * @date 2019-06-22 00:32:04
     */
    default AccessRuleTypeConvert convert() {
        return AccessRuleTypeConvert.INSTANCE;
    }
}
