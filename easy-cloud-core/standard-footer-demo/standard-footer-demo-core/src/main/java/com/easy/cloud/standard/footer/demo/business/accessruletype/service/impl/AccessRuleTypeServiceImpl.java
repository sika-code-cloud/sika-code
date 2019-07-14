package com.easy.cloud.standard.footer.demo.business.accessruletype.service.impl;

import com.easy.cloud.standard.footer.demo.business.accessruletype.entity.AccessRuleTypeEntity;
import com.easy.cloud.standard.footer.demo.business.accessruletype.mapper.AccessRuleTypeMapper;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.query.AccessRuleTypeQuery;
import com.easy.cloud.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;
import com.easy.cloud.standard.footer.demo.business.accessruletype.convert.AccessRuleTypeConvert;

import com.easy.cloud.standard.base.convert.BaseConvert;
import com.easy.cloud.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 准入规则类型表 服务实现类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
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

