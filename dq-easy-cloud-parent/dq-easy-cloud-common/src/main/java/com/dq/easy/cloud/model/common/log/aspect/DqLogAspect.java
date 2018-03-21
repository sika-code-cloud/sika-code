package com.dq.easy.cloud.model.common.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.date.utils.DqDateUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.pojo.bo.DqLogBO;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;

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

	@Pointcut("@within(com.dq.easy.cloud.model.common.log.annotation.DqLog)")
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
	protected Object doLogLogic(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTimeMillis = DqDateUtils.getCurrentTimeMillis();
		Object targetReturnValue = null;
		try {
			targetReturnValue = joinPoint.proceed();
		} catch (Throwable e) {
			throw e;
		} finally {
			long endTimeMillis = DqDateUtils.getCurrentTimeMillis();
//			构建日志逻辑对象--设置日志数据
			DqLogBO dqLogBO = DqLogBO.newInstantce(DqLogDTO.newInstance(beginTimeMillis, endTimeMillis));
			dqLogBO.buildDqLogData(joinPoint).buildTargetReturnValue(targetReturnValue);
//			获取日志注解
			DqLog dqLog = dqLogBO.getDqLog();
			if (DqBaseUtils.isNotNull(dqLog) && DqBaseUtils.isNotNull(dqLog.dqLogProxyClass())){
//				根据注解获取Log委托处理对象执行日志处理
				DqLogUtils.getDqLogProxy(dqLog).handle(dqLogBO);
			}
		}
		return targetReturnValue;
	}
	
	
}
