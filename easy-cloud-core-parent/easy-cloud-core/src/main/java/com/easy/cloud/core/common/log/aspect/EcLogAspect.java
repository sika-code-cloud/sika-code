package com.easy.cloud.core.common.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.pojo.bo.EcLogBO;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.log.utils.EcLogUtils;

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
public class EcLogAspect {

	@Pointcut("@within(com.easy.cloud.core.common.log.annotation.EcLogAnnotation)")
	public void dqLogPointcut() {
		
	}

	/**
	 * dqLog日志记录
	 * 
	 * @param joinPoint
	 */
	@Around("dqLogPointcut()")
	public Object dqLogAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return doLogLogic(proceedingJoinPoint);
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
	protected Object doLogLogic(ProceedingJoinPoint joinPoint) {
		long beginTimeMillis = EcDateUtils.getCurrentTimeMillis();
		Object targetReturnValue = null;
		try {
			targetReturnValue = joinPoint.proceed();
		} catch (Throwable e) {
			throw new RuntimeException(e);
		} finally {
			long endTimeMillis = EcDateUtils.getCurrentTimeMillis();
//			构建日志逻辑对象--设置日志数据
			EcLogBO ecLogBO = EcLogBO.newInstantce(EcLogDTO.newInstance(beginTimeMillis, endTimeMillis));
			ecLogBO.buildDqLogData(joinPoint, targetReturnValue);
//			获取日志注解
			EcLogAnnotation ecLogAnnotation = ecLogBO.getLogAnnotation();
			if (EcBaseUtils.isNotNull(ecLogAnnotation) && EcBaseUtils.isNotNull(ecLogAnnotation.proxyClass())){
//				根据注解获取Log委托处理对象执行日志处理
				EcLogUtils.getDqLogProxy(ecLogAnnotation).handle(ecLogBO);
			}
		}
		return targetReturnValue;
	}
	
	
}
