package com.easy.cloud.core.distributionlock.type;

import com.easy.cloud.core.distributionlock.constant.EcDistributedLockConstant.EcDistributedLockTypeEnum;

/**
 * 
 * <p>
 * 分布式锁类型选择器
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年4月12日 上午11:29:04
 */
public interface EcDistributedLockTypeSelector {
	/**
	 * 
	 * <p>
	 * 根据类型枚举获取分布式锁类型
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param distributedLockTypeEnum : EcDistributedLockConstant.EcDistributedLockTypeEnum : 分布式锁类型枚举对象
	 * @return EcBaseDistributedLockType
	 * @author daiqi
	 * 创建时间    2018年4月12日 上午11:49:42
	 */
	EcBaseDistributedLockType getDistributedLockTypeByType(EcDistributedLockTypeEnum distributedLockTypeEnum);
	
}
