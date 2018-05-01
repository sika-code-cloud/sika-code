package com.easy.cloud.core.lock.callback;

import com.easy.cloud.core.lock.callback.result.EcLockResult;
import com.easy.cloud.core.lock.pojo.dto.EcLockDTO;

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
public interface EcLockCallback<T> {
	/**
	 * 
	 * <p>
	 * 调用者必须在此方法中实现执行分布式锁后的业务逻辑
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param distributedLockResult : EcDistributedLockResult : 分布式锁的处理结果
	 * @return 执行的结果
	 * @author daiqi
	 * @创建时间 2018年4月14日 上午9:59:39
	 */
    public T process(EcLockResult distributedLockResult);

    /**
     * 获取分布式锁数据传输对象
     *
     * @return
     */
    public EcLockDTO getDistributedLockDTO();
}
