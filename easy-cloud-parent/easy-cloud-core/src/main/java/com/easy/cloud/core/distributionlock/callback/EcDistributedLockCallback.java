package com.easy.cloud.core.distributionlock.callback;

/**
 * 
 * <p>
 * 分布式锁回调接口类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
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
     * 得到分布式锁名称
     *
     * @return
     */
    public String getLockName();
}
