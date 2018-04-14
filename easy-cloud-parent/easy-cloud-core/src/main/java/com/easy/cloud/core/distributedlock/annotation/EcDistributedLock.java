package com.easy.cloud.core.distributedlock.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.distributedlock.callback.result.processor.EcBaseDistributedLockResultProcessor;
import com.easy.cloud.core.distributedlock.callback.result.processor.EcDefaultDistributedLockResultProcessor;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockNameDesc;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockTemplateTypeEnum;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockTime;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockTypeEnum;

/**
 * 
 * <p>
 * 分布式锁注解
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年4月12日 上午11:30:55
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EcDistributedLock {
	/**
	 * 锁的名称。 如果lockName可以确定，直接设置该属性。
	 */
	String lockName() default EcSymbol.EMPTY;

	/**
	 * lockName前缀
	 */
	String lockNamePre() default EcDistributedLockNameDesc.LOCK_NAME_PRE_DEFAULT;

	/**
	 * lockName后缀
	 */
	String lockNameSuffix() default EcSymbol.EMPTY;

	/**
	 * 获得锁名时拼接前后缀用到的分隔符
	 * 
	 * @return
	 */
	String separator() default EcSymbol.SPLIT_COLON;

	/**
	 * <pre>
	 *     获取注解的方法参数列表的某个参数对象的某个属性值来作为lockName。因为有时候lockName是不固定的。
	 *     当param不为空时，可以通过argNum参数来设置具体是参数列表的第几个参数，不设置则默认取第一个。
	 * </pre>
	 */
	String param() default EcSymbol.EMPTY;

	/**
	 * 将方法第argNum个参数作为锁
	 */
	int argNum() default 0;

	/**
	 * 
	 * <p>
	 * 分布式锁的类型---参考EcDistributedLockTypeEnum
	 * </p>
	 *
	 * @return
	 * @author daiqi 
	 * @创建时间 2018年4月12日 下午2:32:25
	 */
	EcDistributedLockTypeEnum lockType() default EcDistributedLockTypeEnum.FAIR;

	/**
	 * 
	 * <p>
	 * 分布式锁模板类型枚举
	 * </p>
	 *
	 * @return
	 * @author daiqi 
	 * @创建时间 2018年4月13日 上午9:42:04
	 */
	EcDistributedLockTemplateTypeEnum templateType() default EcDistributedLockTemplateTypeEnum.REDISSION;

	/**
	 * 是否使用尝试锁。
	 */
	boolean tryLock() default false;

	/**
	 * 最长等待时间。 该字段只有当tryLock()返回true才有效。
	 */
	long waitTime() default EcDistributedLockTime.WAIT_TIME_DEFAULT;

	/**
	 * 锁超时时间。 超时时间过后，锁自动释放。 建议： 尽量缩简需要加锁的逻辑。
	 */
	long leaseTime() default EcDistributedLockTime.LEASE_TIME_DEFAULT;

	/**
	 * 时间单位。默认为秒。
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;
	
	/**
	 * 
	 * <p>
	 * 结果处理器类的class
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月14日 上午10:48:19
	 */
	Class<? extends EcBaseDistributedLockResultProcessor> resultProcessorClass() default EcDefaultDistributedLockResultProcessor.class;

}
