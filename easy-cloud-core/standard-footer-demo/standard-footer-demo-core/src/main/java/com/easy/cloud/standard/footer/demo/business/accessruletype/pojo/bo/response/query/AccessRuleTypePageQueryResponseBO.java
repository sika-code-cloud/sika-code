package com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query;

import com.easy.cloud.database.common.Page;
import com.easy.cloud.standard.base.pojo.bo.response.BaseStandardResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.domain.AccessRuleTypeDomain;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.dto.AccessRuleTypeDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 准入规则类型表 分页查询响应类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
@Data
@Accessors(chain = true)
public class AccessRuleTypePageQueryResponseBO implements BaseStandardResponseBO<Page<AccessRuleTypeDTO>>, AccessRuleTypeDomain {

    /**
    * 分页响应对象
    */
    private Page<AccessRuleTypePageQueryResponse> page;

    @Override
    public void build(Page<AccessRuleTypeDTO> page) {
        this.page = new Page<>(page, convert().convertToPageQueryResponses(page.getList()));
    }

    /**
    * <p>
        * 响应类 封装响应数据
        * </p>
    *
    * @author daiqi
    * @date 2019-06-22 00:32:04
    */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class AccessRuleTypePageQueryResponse extends AccessRuleTypeDTO {

    }
}