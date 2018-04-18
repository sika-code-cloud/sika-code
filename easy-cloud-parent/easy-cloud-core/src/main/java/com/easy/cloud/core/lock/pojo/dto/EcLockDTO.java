package com.easy.cloud.core.lock.pojo.dto;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.lock.annotation.EcLockAnnotation;
import com.easy.cloud.core.lock.template.EcLockTemplate;

/**
 * 
 * <p>
 * 分布式锁数据传输类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月13日 上午10:29:32
 */
public class EcLockDTO {
	/** 锁的名称 */
	private String lockName;
	/** 完整的锁名 */
	private String lockNameFull;
	/** 锁的模板对象 */
	private EcLockTemplate lockTemplate;
	/** 锁的注解对象 */
	private EcLockAnnotation lockAnnotation;

	public String getLockName() {
		return lockName;
	}

	public void setLockName(String lockName) {
		this.lockName = lockName;
	}

	public EcLockTemplate getLockTemplate() {
		return lockTemplate;
	}

	public void setLockTemplate(EcLockTemplate lockTemplate) {
		this.lockTemplate = lockTemplate;
	}

	public EcLockAnnotation getLockAnnotation() {
		return this.lockAnnotation;
	}

	public void setLock(EcLockAnnotation lockAnnotation) {
		this.lockAnnotation = lockAnnotation;
	}
	
	/** 获取完整的锁名 */
	public String getLockNameFull() {
		if (EcStringUtils.isNotEmpty(lockNameFull)) {
			return lockNameFull;
		}
		this.lockNameFull = this.lockName;
		if (EcBaseUtils.isNotNull(this.lockAnnotation)) {
			String[] names = {lockAnnotation.namePre(), this.lockName, this.lockAnnotation.nameSuffix()};
			this.lockNameFull = EcStringUtils.generateKey(EcSymbol.COLON, names);
		}
		return this.lockNameFull;
	}

}
