package com.sika.code.standard.footer.demo.business.accessruletype.enums;

import com.sika.code.common.spring.SpringUtil;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import com.sika.code.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;

/**
 * @author daiqi
 * @create 2020-03-19 23:22
 */
public class Demo {
    public void testGetBean() {
        AccessRuleTypeService ruleTypeService = SpringUtil.getBean(RuleBeanMappingEnum.ACCESSRULE_TYPESERVICE_IMPL);
        ruleTypeService.save(new AccessRuleTypeDTO());
    }
}
