package com.dq.easy.cloud.model.common.log.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.date.utils.DqDateUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.entruster.DqLogEntruster;
import com.dq.easy.cloud.model.common.log.pojo.bo.DqLogBO;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.model.common.reflection.utils.DqReflectionUtils;

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

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Pointcut("@within(com.dq.easy.cloud.model.common.log.annotation.DqLog)")
	public void dqLogOpenPointcut() {
	}

	/**
	 * dqLog日志记录
	 * 
	 * @param joinPoint
	 */
	@Around("dqLogOpenPointcut()")
	public Object dqLogOpenAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
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
			DqLog dqLog = joinPoint.getTarget().getClass().getAnnotation(DqLog.class);
			if (DqBaseUtils.isNotNull(dqLog) && DqBaseUtils.isNotNull(dqLog.dqLogEntrusterClass())) {
				// 构建日志逻辑对象
				DqLogBO dqLogBO = DqLogBO.newInstantce(DqLogDTO.newInstance(beginTimeMillis, endTimeMillis));
				dqLogBO.buildDqLog(dqLog).buildDqLogData(joinPoint);
				dqLogBO.buildTargetReturnValue(targetReturnValue);
				// 获取日志委托处理类
				DqLogEntruster dqLogEntruster = DqLogUtils.getDqLogEntruster(dqLog);
				dqLogEntruster.handle(dqLogBO);
			}
		}
		return targetReturnValue;
	}
}
