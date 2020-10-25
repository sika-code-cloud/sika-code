package com.sika.code.standard.footer.demo.business.accessruletype.enums;

import com.sika.code.basic.constant.BeanMappingEnum;
import com.sika.code.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daiqi
 * @create 2020-03-19 23:08
 */
@Getter
@AllArgsConstructor
public enum RuleBeanMappingEnum implements BeanMappingEnum<AccessRuleTypeService> {
    /**
     * bean映射枚举
     */
    ACCESSRULE_TYPESERVICE_IMPL(1, "accessRuleTypeService","测试");
    private Integer type;
    private String beanName;
    private String desc;

    @Override
    public Class<AccessRuleTypeService> getBeanClass() {
        return AccessRuleTypeService.class;
    }
}
