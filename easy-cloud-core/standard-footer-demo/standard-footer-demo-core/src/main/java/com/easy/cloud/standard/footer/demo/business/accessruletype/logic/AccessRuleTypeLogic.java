package com.easy.cloud.standard.footer.demo.business.accessruletype.logic;

import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeCommonQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeListQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypePageQueryRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveBatchRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.request.update.AccessRuleTypeUpdateByIdRequestBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeListQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypePageQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeQueryResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.save.AccessRuleTypeSaveResponseBO;
import com.easy.cloud.standard.footer.demo.business.accessruletype.pojo.bo.response.update.AccessRuleTypeUpdateResponseBO;

/**
 * <p>
 * 准入规则类型表 逻辑类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-22 00:32:04
 */
public interface AccessRuleTypeLogic {
    /**
     * <p>
     * 保存普通字段的数据
     * </p>
     *
     * @param requestBO : 保存请求业务对象
     * @return AccessRuleTypeSaveResponseBO
     * @author daiqi
     * @date 2019-06-22 00:32:04
     */
    AccessRuleTypeSaveResponseBO save(AccessRuleTypeSaveRequestBO requestBO);

    /**
     * <p>
     * 批量保存数据
     * </p>
     *
     * @param requestBO : 批量保存请求业务对象
     * @return AccessRuleTypeSaveResponseBO
     * @author daiqi
     * @date 2019-06-22 00:32:04
     */
    AccessRuleTypeSaveResponseBO saveBatch(AccessRuleTypeSaveBatchRequestBO requestBO);

    /**
     * <p>
     * 更新普通字段的数据
     * </p>
     *
     * @param requestBO : 更新请求业务对象
     * @return AccessRuleTypeUpdateResponseBO
     * @author daiqi
     * @date 2019-06-22 00:32:04
     */
    AccessRuleTypeUpdateResponseBO updateById(AccessRuleTypeUpdateByIdRequestBO requestBO);

    /**
     * <p>
     * 根据分页查询条件查询分页数据
     * </p>
     *
     * @param requestBO : 分页查询请求对象
     * @return AccessRuleTypePageQueryResponseBO
     * @author daiqi
     * @date 2019-06-22 00:32:04
     */
    AccessRuleTypePageQueryResponseBO page(AccessRuleTypePageQueryRequestBO requestBO);

    /**
     * <p>
     * 根据查询条件获取详情数据
     * </p>
     *
     * @param requestBO : 查询请求业务对象
     * @return AccessRuleTypeQueryResponseBO
     * @author daiqi
     * @date 2019-06-22 00:32:04
     */
    AccessRuleTypeQueryResponseBO find(AccessRuleTypeCommonQueryRequestBO requestBO);

    /**
     * <p>
     * 根据查询条件获取列表数据
     * </p>
     *
     * @param requestBO : 查询请求业务对象
     * @return AccessRuleTypeListQueryResponseBO
     * @author daiqi
     * @date 2019-06-22 00:32:04
     */
    AccessRuleTypeListQueryResponseBO list(AccessRuleTypeListQueryRequestBO requestBO);
}
