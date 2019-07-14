package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response;

import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.domain.AccessRuleTypeDomain;
import com.easy.cloud.standard.base.pojo.bo.response.BaseStandardResponseBO;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 修改响应抽象类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
@Data
@Accessors(chain = true)
public abstract class AccessRuleTypeAlterResponseBO implements BaseStandardResponseBO<Boolean>, AccessRuleTypeDomain {
    /**
     * 操作成功的标志
     */
    protected Boolean success;

    @Override
    public void build(Boolean success) {
        this.success = success;
    }
}