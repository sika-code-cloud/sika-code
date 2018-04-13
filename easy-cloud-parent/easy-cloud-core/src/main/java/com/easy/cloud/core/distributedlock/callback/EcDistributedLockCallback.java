package com.easy.cloud.core.distributedlock.callback;

import com.easy.cloud.core.distributedlock.pojo.dto.EcDistributedLockDTO;

/**
 * 
 * <p>
 * 分布式锁回调接口类
 * </p>
 *
 * @param <T>
 * @author daiqi
 * 创建时间    2018年4月12日 上午11:26:39
 */
public interface EcDistributedLockCallback<T> {
	/**
     * 调用者必须在此方法中实现需要加分布式锁的业务逻辑
     *
     * @return
     */
    public T process();

    /**
     * 获取分布式锁数据传输对象
     *
     * @return
     */
    public EcDistributedLockDTO getDistributedLockDTO();
}
