package com.sika.code.standard.footer.demo.business.accessruletype.logic;

import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeCommonQueryRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeListQueryRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypePageQueryRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveBatchRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.update.AccessRuleTypeUpdateByIdRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeListQueryResponseBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypePageQueryResponseBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeQueryResponseBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.save.AccessRuleTypeSaveResponseBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.update.AccessRuleTypeUpdateResponseBO;

/**
 * <p>
 * 准入规则类型表 逻辑类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
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
     * @date 2019-07-18 23:31:10
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
     * @date 2019-07-18 23:31:10
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
     * @date 2019-07-18 23:31:10
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
     * @date 2019-07-18 23:31:10
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
     * @date 2019-07-18 23:31:10
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
     * @date 2019-07-18 23:31:10
     */
    AccessRuleTypeListQueryResponseBO list(AccessRuleTypeListQueryRequestBO requestBO);
}
