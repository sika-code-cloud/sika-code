package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.save;

import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;

import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.AccessRuleTypeAlterRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.save.AccessRuleTypeSaveResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 保存请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccessRuleTypeSaveRequestBO extends AccessRuleTypeAlterRequestBO<AccessRuleTypeSaveResponseBO> {
    /**
    * 保存请求对象
    */
    private AccessRuleTypeSaveRequest accessRuleType;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected AccessRuleTypeSaveResponseBO doExecute() {
        // 转换
        AccessRuleTypeDTO accessRuleTypeForSave = convert().convertToDTO(this.accessRuleType);
        // 保存
        boolean success = service().save(accessRuleTypeForSave);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<AccessRuleTypeSaveResponseBO> responseClass() {
        return AccessRuleTypeSaveResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class AccessRuleTypeSaveRequest extends AccessRuleTypeAlterRequest {

    }
}