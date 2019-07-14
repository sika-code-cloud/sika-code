package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.logic;


import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplateCommonQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplateListQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.query.CreditQuotaUsageTemplatePageQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save.CreditQuotaUsageTemplateSaveBatchRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.save.CreditQuotaUsageTemplateSaveRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request.update.CreditQuotaUsageTemplateUpdateByIdRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplateListQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplatePageQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.query.CreditQuotaUsageTemplateQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.save.CreditQuotaUsageTemplateSaveResponseBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.update.CreditQuotaUsageTemplateUpdateResponseBO;

/**
 * <p>
 * 额度模板类型配置 逻辑类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
public interface CreditQuotaUsageTemplateLogic {
    /**
     * <p>
     * 保存普通字段的数据
     * </p>
     *
     * @param requestBO : 保存请求业务对象
     * @return CreditQuotaUsageTemplateSaveResponseBO
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    CreditQuotaUsageTemplateSaveResponseBO save(CreditQuotaUsageTemplateSaveRequestBO requestBO);

    /**
     * <p>
     * 批量保存数据
     * </p>
     *
     * @param requestBO : 批量保存请求业务对象
     * @return CreditQuotaUsageTemplateSaveResponseBO
     * @author daiqi
     * @date 2019/6/19 20:59
     */
    CreditQuotaUsageTemplateSaveResponseBO saveBatch(CreditQuotaUsageTemplateSaveBatchRequestBO requestBO);

    /**
     * <p>
     * 更新普通字段的数据
     * </p>
     *
     * @param requestBO : 更新请求业务对象
     * @return CreditQuotaUsageTemplateUpdateResponseBO
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    CreditQuotaUsageTemplateUpdateResponseBO updateById(CreditQuotaUsageTemplateUpdateByIdRequestBO requestBO);

    /**
     * <p>
     * 根据分页查询条件查询分页数据
     * </p>
     *
     * @param requestBO : 分页查询请求对象
     * @return CreditQuotaUsageTemplatePageQueryResponseBO
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    CreditQuotaUsageTemplatePageQueryResponseBO page(CreditQuotaUsageTemplatePageQueryRequestBO requestBO);

    /**
     * <p>
     * 根据查询条件获取详情数据
     * </p>
     *
     * @param requestBO : 查询请求业务对象
     * @return CreditQuotaUsageTemplateQueryResponseBO
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    CreditQuotaUsageTemplateQueryResponseBO find(CreditQuotaUsageTemplateCommonQueryRequestBO requestBO);

    /**
     * <p>
     * 根据查询条件获取列表数据
     * </p>
     *
     * @param requestBO : 查询请求业务对象
     * @return CreditQuotaUsageTemplateListQueryResponseBO
     * @author daiqi
     * @date 2019-06-07 10:12:51
     */
    CreditQuotaUsageTemplateListQueryResponseBO list(CreditQuotaUsageTemplateListQueryRequestBO requestBO);
}
