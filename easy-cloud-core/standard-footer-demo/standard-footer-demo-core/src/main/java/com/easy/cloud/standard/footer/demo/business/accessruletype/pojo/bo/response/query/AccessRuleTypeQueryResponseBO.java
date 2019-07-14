package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query;

import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import com.easy.cloud.standard.base.pojo.bo.response.BaseStandardResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.domain.AccessRuleTypeDomain;
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
 * @since 2019-06-22 00:32:04
 */
@Data
@Accessors(chain = true)
public class AccessRuleTypeQueryResponseBO implements BaseStandardResponseBO<AccessRuleTypeDTO>, AccessRuleTypeDomain {
    /**
     * 返回给页面的响应对象
     */
    private AccessRuleTypeQueryResponse accessRuleType;
    private Long startTime;
    private Long endTime;

    public String getSendTime() {
        if (endTime == null || startTime == null) {
            return null;
        }
        return (endTime - startTime) + "ms";
    }
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
     * @date 2019-06-22 00:32:04
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class AccessRuleTypeQueryResponse extends AccessRuleTypeDTO {

    }
}