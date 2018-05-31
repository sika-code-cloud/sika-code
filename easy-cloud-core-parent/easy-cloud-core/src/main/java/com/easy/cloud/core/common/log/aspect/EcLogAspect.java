package com.easy.cloud.core.common.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.constant.EcBaseConfigConstant;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.log.conditional.EcLogConditional;
import com.easy.cloud.core.common.log.pojo.bo.EcLogBO;

/**
 * 
 * <p>
 * 日志记录切面类
 * </p>
 *
 * <pre>
 *  说明：对添加日志记录注解的类做日志记录
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月7日 下午7:06:15
 */
@Aspect
@Order
@Component
@Conditional(value = EcLogConditional.class)
public class EcLogAspect {

	@Pointcut("@within(" + EcBaseConfigConstant.LOG_ANNOTATION_NAME + ")")
	public void logPointcut() {

	}

	/**
	 * dqLog日志记录
	 * 
	 * @param joinPoint
	 */
	@Around("logPointcut()")
	public Object dqLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTimeMillis = EcDateUtils.getCurrentTimeMillis();
		Object targetReturnValue = null;
		try {
			targetReturnValue = joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		} finally {
			doLogLogic(joinPoint, beginTimeMillis, targetReturnValue);
		}
		return targetReturnValue;
	}

	/**
	 * <p>
	 * 执行日志记录的业务处理
	 * </p>
	 *
	 * @param joinPoint
	 * @return
	 * @author daiqi 创建时间 2018年2月7日 下午7:21:56
	 * @throws Throwable
	 */
	protected void doLogLogic(final ProceedingJoinPoint joinPoint, final long beginTimeMillis, final Object targetReturnValue) {
		long endTimeMillis = EcDateUtils.getCurrentTimeMillis();
		// 构建日志逻辑对象--设置日志数据
		EcLogBO ecLogBO = new EcLogBO(beginTimeMillis, endTimeMillis);
		ecLogBO.buildLogData(joinPoint, targetReturnValue);
		ecLogBO.handle();
	}

}
