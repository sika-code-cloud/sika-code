package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response;

import com.easy.cloud.standard.base.pojo.bo.response.BaseStandardResponseBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.domain.CreditQuotaUsageTemplateDomain;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置 修改响应抽象类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@Accessors(chain = true)
public abstract class CreditQuotaUsageTemplateAlterResponseBO implements BaseStandardResponseBO<Boolean>, CreditQuotaUsageTemplateDomain {
    /**
     * 返回给页面的视图对象
     */
    protected Boolean success;

    @Override
    public void build(Boolean success) {
        this.success = success;
    }
}