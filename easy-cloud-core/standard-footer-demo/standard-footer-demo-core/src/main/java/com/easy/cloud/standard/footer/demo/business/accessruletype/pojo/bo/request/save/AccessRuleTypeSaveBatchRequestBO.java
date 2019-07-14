package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.save;

import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.AccessRuleTypeAlterRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.save.AccessRuleTypeSaveResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * <p>
 * 准入规则类型表 批量保存请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccessRuleTypeSaveBatchRequestBO extends AccessRuleTypeAlterRequestBO<AccessRuleTypeSaveResponseBO> {
    /**
    * 批量保存请求对象列表
    */
    private List<AccessRuleTypeSaveBatchRequest> accessRuleTypes;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected AccessRuleTypeSaveResponseBO doExecute() {
        // 转换
        List<AccessRuleTypeDTO> accessRuleTypesForSave = convert().convertToDTOFromSaveBatchRequests(this.accessRuleTypes);
        // 保存
        boolean success = service().saveForBatch(accessRuleTypesForSave);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<AccessRuleTypeSaveResponseBO> responseClass() {
        return AccessRuleTypeSaveResponseBO.class;
    }


    @Data
    @Accessors(chain = true)
    public static class AccessRuleTypeSaveBatchRequest extends AccessRuleTypeAlterRequest {

    }
}