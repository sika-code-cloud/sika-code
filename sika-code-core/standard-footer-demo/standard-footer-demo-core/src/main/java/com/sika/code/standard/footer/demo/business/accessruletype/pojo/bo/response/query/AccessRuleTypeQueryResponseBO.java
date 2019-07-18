package com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query;

import com.sika.code.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.domain.AccessRuleTypeDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * 准入规则类型表 普通查询响应类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
 */
@Data
@Accessors(chain = true)
public class AccessRuleTypeQueryResponseBO implements BaseStandardResponseBO<AccessRuleTypeDTO>, AccessRuleTypeDomain {
    /**
     * 返回给页面的响应对象
     */
    private AccessRuleTypeQueryResponse accessRuleType;

    @Override
    public void build(AccessRuleTypeDTO accessRuleType) {
        this.accessRuleType = convert().convertToQueryResponse(accessRuleType);
    }

    /**
     * <p>
     * 响应类 : 封装响应数据
     * </p>
     *
     * @author daiqi
     * @date 2019-07-18 23:31:10
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class AccessRuleTypeQueryResponse extends AccessRuleTypeDTO {

    }
}