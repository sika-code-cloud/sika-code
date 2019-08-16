package com.sika.code.standard.footer.demo.business.accessruletype.logic.impl;


import com.google.common.collect.Lists;
import com.sika.code.cache.redis.util.RedisUtil;
import com.sika.code.lock.anotation.DistributionLock;
import com.sika.code.lock.distribution.DistributionLockHandler;
import com.sika.code.lock.pojo.result.LockResult;
import com.sika.code.lock.properties.DistributionLockProperties;
import com.sika.code.standard.base.logic.BaseStandardLogic;
import com.sika.code.standard.footer.demo.business.accessruletype.logic.AccessRuleTypeLogic;
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
import com.sika.code.standard.footer.demo.business.accessruletype.service.AccessRuleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private DistributionLockProperties distributionLockProperties;

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
    @DistributionLock(modules = {"user", "order"}, fieldName = "ueryRequest.accessRuleTypeId")
    public AccessRuleTypeListQueryResponseBO list(AccessRuleTypeListQueryRequestBO request) {
        return request.execute();
    }

    @Autowired
    private DistributionLockHandler distributionLockHandler;

    /**
     * <p>
     * 锁示例 --- 具体使用选择一种锁即可
     * </p>
     */
    public AccessRuleTypeListQueryResponseBO lock(AccessRuleTypeListQueryRequestBO request) {
        String typeIdStr = String.valueOf(request.getQueryRequest().getAccessRuleTypeId());
        String lockKey = RedisUtil.generateRedisKey("user", "order", typeIdStr);

        // 级联key的列表
        List<String> lockKeys = Lists.newArrayList();
        for (String typeId : request.getQueryRequest().getAccessRuleTypeIds()) {
            lockKeys.add(RedisUtil.generateRedisKey("user", "order", typeId));
        }
        LockResult lockResult = null;
        try {
            // 1:非公平锁
            // 1.1:无过期时间的锁
            lockResult = distributionLockHandler.lock(lockKey);
            // 1.2:有过期时间的锁 key的过期时间为5秒
            lockResult = distributionLockHandler.lock(lockKey, 5);
            // 1.3:带时间单位的锁 key的过期时间为5000毫秒
            lockResult = distributionLockHandler.lock(lockKey, 5000, TimeUnit.MILLISECONDS);
            // --- 级联锁 ---
            // 1.4:无过期时间的锁
            lockResult = distributionLockHandler.lock(lockKeys);
            // 1.5:有过期时间的锁 key的过期时间为5秒
            lockResult = distributionLockHandler.lock(lockKeys, 5);
            // 1.6:带时间单位的锁 key的过期时间为5000毫秒
            lockResult = distributionLockHandler.lock(lockKeys, 5000, TimeUnit.MILLISECONDS);

            // 2:公平锁
            // 2.1:无过期时间的锁
            lockResult = distributionLockHandler.fairLock(lockKey);
            // 2.2:有过期时间的锁 key的过期时间为5秒
            lockResult = distributionLockHandler.fairLock(lockKey,5 );
            // 2.3:带时间单位的锁 key的过期时间为5000毫秒
            lockResult = distributionLockHandler.fairLock(lockKey, 5000, TimeUnit.MILLISECONDS);

            // 3:尝试非公平锁锁
            // 3.1:有过期时间的锁 锁的等待时间3秒，过期时间5秒
            lockResult = distributionLockHandler.tryLock(lockKey, 3, 5);
            // 3.2:带时间单位的锁 锁的等待时间3000毫秒，过期时间5000毫秒
            lockResult = distributionLockHandler.tryLock(lockKey, 3000, 5000, TimeUnit.MILLISECONDS);
            // --- 级联锁 ---
            // 3.3:尝试非公平级联锁 有过期时间的锁 锁的等待时间3秒，过期时间5秒
            lockResult = distributionLockHandler.tryLock(lockKeys, 3, 5);
            // 3.2:带时间单位的锁 锁的等待时间3000毫秒，过期时间5000毫秒
            lockResult = distributionLockHandler.tryLock(lockKeys, 3000, 5000, TimeUnit.MILLISECONDS);

            // 4:尝试公平锁
            // 3.1:有过期时间的锁 锁的等待时间3秒，过期时间5秒
            lockResult = distributionLockHandler.tryFairLock(lockKey, 3, 5);
            // 3.2:带时间单位的锁 锁的等待时间3000毫秒，过期时间5000毫秒
            lockResult = distributionLockHandler.tryFairLock(lockKey, 3000, 5000, TimeUnit.MILLISECONDS);

            return request.execute();
        } finally {
            distributionLockHandler.unlock(lockResult.getLock());
        }
    }


}