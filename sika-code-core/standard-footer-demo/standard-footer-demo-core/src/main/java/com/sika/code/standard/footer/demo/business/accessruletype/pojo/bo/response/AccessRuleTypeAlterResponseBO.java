package com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response;

import com.sika.code.standard.footer.demo.business.accessruletype.pojo.domain.AccessRuleTypeDomain;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 修改响应抽象类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
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