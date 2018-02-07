package com.dq.easy.cloud.model.common.log.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.common.log.annotation.DqLogOpen;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;

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
@Component
@Order(99)
public class DqLogAspect {
	@Pointcut("@annotation(com.dq.easy.cloud.model.common.log.annotation.DqLogOpen)")
	private void dqLogOpenPointcut() {
	}

	/**
	 * dqLog日志记录
	 * 
	 * @param joinPoint
	 */
	@Around("dqLogOpenPointcut()")
	public Object dqLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
		return doLogLogic(joinPoint);
	}

	/**
	 * <p>
	 * 执行日志记录的业务处理
	 * </p>
	 *
	 * @param joinPoint
	 * @return
	 * @author daiqi 创建时间 2018年2月7日 下午7:21:56
	 */
	protected Object doLogLogic(ProceedingJoinPoint joinPoint) {
		try {

			Object obj = joinPoint.proceed();
			MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
			Method method = joinPointObject.getMethod();
			Class<?> clazz = method.getClass();
			boolean flag = method.isAnnotationPresent(DqLogOpen.class);
			return obj;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {

		}
	}
}
