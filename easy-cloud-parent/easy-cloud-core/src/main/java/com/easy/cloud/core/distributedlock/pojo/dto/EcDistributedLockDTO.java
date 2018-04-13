package com.easy.cloud.core.distributedlock.pojo.dto;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.distributedlock.annotation.EcDistributedLock;
import com.easy.cloud.core.distributedlock.template.EcDistributedLockTemplate;

/**
 * 
 * <p>
 * 分布式锁数据传输类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月13日 上午10:29:32
 */
public class EcDistributedLockDTO {
	/** 锁的名称 */
	private String lockName;
	/** 完整的锁名 */
	private String lockNameFull;
	/** 锁的模板对象 */
	private EcDistributedLockTemplate lockTemplate;
	/** 锁的注解对象 */
	private EcDistributedLock distributedLock;

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public EcDistributedLockTemplate getLockTemplate() {
		return lockTemplate;
	}

	public void setLockTemplate(EcDistributedLockTemplate lockTemplate) {
		this.lockTemplate = lockTemplate;
	}

	public EcDistributedLock getDistributedLock() {
		return distributedLock;
	}

	public void setDistributedLock(EcDistributedLock distributedLock) {
		this.distributedLock = distributedLock;
	}
	
	/** 获取完整的锁名 */
	public String getLockNameFull() {
		if (EcStringUtils.isNotEmpty(lockNameFull)) {
			return lockNameFull;
		}
		
		this.lockNameFull = this.lockName;
		if (EcBaseUtils.isNotNull(this.distributedLock)) {
			StringBuilder sb = EcStringUtils.newStringBuilderDefault();
			if (EcStringUtils.isNotEmpty(this.distributedLock.lockNamePre())) {
				sb.append(this.distributedLock.lockNamePre());
				sb.append(this.distributedLock.separator());
			}
			sb.append(this.lockName);
			
			if (EcStringUtils.isNotEmpty(this.distributedLock.lockNameSuffix())) {
				sb.append(this.distributedLock.separator());
				sb.append(this.distributedLock.lockNameSuffix());
			}
			this.lockNameFull = sb.toString();
		}
		return this.lockNameFull;
	}

}
