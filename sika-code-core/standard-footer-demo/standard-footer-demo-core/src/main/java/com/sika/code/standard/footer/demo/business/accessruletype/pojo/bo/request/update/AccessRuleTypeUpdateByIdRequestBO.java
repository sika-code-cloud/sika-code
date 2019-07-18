package com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.update;

import com.sika.code.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.AccessRuleTypeAlterRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.update.AccessRuleTypeUpdateResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 根据id更新请求逻辑类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class AccessRuleTypeUpdateByIdRequestBO extends AccessRuleTypeAlterRequestBO<AccessRuleTypeUpdateResponseBO> {
    /**
    * 根据id更新数据的请求对象
    */
    private AccessRuleTypeUpdateByIdRequest accessRuleType;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {
        // 校验数据是否存在，不存在抛出异常
        service().verifyExist(this.accessRuleType.getAccessRuleTypeId());
    }

    @Override
    protected AccessRuleTypeUpdateResponseBO doExecute() {
        // 转换
        AccessRuleTypeDTO accessRuleTypeForUpdate = convert().convertToDTO(accessRuleType);
        // 执行
        Boolean success = service().update(accessRuleTypeForUpdate);
        // 响应
        return newResponseBO(this, success);
    }

    @Override
    public Class<AccessRuleTypeUpdateResponseBO> responseClass() {
        return AccessRuleTypeUpdateResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class AccessRuleTypeUpdateByIdRequest extends AccessRuleTypeAlterRequest {
        /**
         * 表数据id
         */
        private Long accessRuleTypeId;
    }
}