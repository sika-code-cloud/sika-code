package com.sika.code.standard.footer.demo.business.accessruletype.service.impl;

import com.sika.code.standard.footer.demo.business.accessruletype.entity.AccessRuleTypeEntity;
import com.sika.code.standard.footer.demo.business.accessruletype.mapper.AccessRuleTypeMapper;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.query.AccessRuleTypeQuery;
import com.sika.code.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;
import com.sika.code.standard.footer.demo.business.accessruletype.convert.AccessRuleTypeConvert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 准入规则类型表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
 */
@Service(value = "accessRuleTypeService")
public class AccessRuleTypeServiceImpl extends BaseStandardServiceImpl<AccessRuleTypeMapper, AccessRuleTypeEntity, AccessRuleTypeDTO> implements AccessRuleTypeService {
    @Autowired
    private AccessRuleTypeMapper creditQuotaUsageTemplateMapper;


    @Override
    protected BaseConvert<AccessRuleTypeEntity, AccessRuleTypeDTO> convert() {
        return AccessRuleTypeConvert.INSTANCE;
    }
}

