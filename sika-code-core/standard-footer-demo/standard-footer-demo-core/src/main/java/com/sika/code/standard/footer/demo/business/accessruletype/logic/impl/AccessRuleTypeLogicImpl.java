package com.sika.code.standard.footer.demo.business.accessruletype.logic.impl;


import com.baomidou.mybatisplus.extension.api.R;
import com.sika.code.lock.distribution.DistributionLock;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeCommonQueryRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypeListQueryRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveBatchRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.update.AccessRuleTypeUpdateByIdRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeListQueryResponseBO;
import com.sika.code.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;
import com.sika.code.standard.footer.demo.business.accessruletype.logic.AccessRuleTypeLogic;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.query.AccessRuleTypePageQueryRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.request.save.AccessRuleTypeSaveRequestBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypePageQueryResponseBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.query.AccessRuleTypeQueryResponseBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.save.AccessRuleTypeSaveResponseBO;
import com.sika.code.standard.footer.demo.business.accessruletype.pojo.bo.response.update.AccessRuleTypeUpdateResponseBO;
import com.sika.code.standard.base.logic.BaseStandardLogic;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.locks.Lock;

/**
 * <p>
 * 准入规则类型表 逻辑实现类
 * </p>
 *
 * @author daiqi
 * @since 2019-07-18 23:31:10
 */
@Component(value = "accessRuleTypeLogic")
public class AccessRuleTypeLogicImpl extends BaseStandardLogic implements AccessRuleTypeLogic {
    @Autowired
    private AccessRuleTypeService accessRuleTypeService;
    @Autowired
    private DistributionLock distributionLock;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessRuleTypeSaveResponseBO save(AccessRuleTypeSaveRequestBO request) {
        return request.execute();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessRuleTypeSaveResponseBO saveBatch(AccessRuleTypeSaveBatchRequestBO requestBO) {
        return requestBO.execute();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AccessRuleTypeUpdateResponseBO updateById(AccessRuleTypeUpdateByIdRequestBO request) {
        return request.execute();
    }

    @Override
    public AccessRuleTypePageQueryResponseBO page(AccessRuleTypePageQueryRequestBO request) {
        return request.execute();
    }

    @Override
    public AccessRuleTypeQueryResponseBO find(AccessRuleTypeCommonQueryRequestBO request) {
        return request.execute();
    }

    @Override
    public AccessRuleTypeListQueryResponseBO list(AccessRuleTypeListQueryRequestBO request) {
        Lock lock = null;
        try {
            lock = distributionLock.lock("hello");
        } finally {
            distributionLock.unlock(lock);
        }
        return request.execute();
    }
}