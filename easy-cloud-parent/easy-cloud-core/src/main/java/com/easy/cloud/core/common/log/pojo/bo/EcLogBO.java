package com.easy.cloud.core.common.log.pojo.bo;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.easy.cloud.core.basic.pojo.bo.EcBaseAspectBO;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 
 * <p>
 * 日志业务逻辑对象
 * </p>
 *
 * <pre>
 *  说明：该类对日志数据传输对象进行逻辑处理
 *  约定：使用该类必须使用newInstance方法进行实例初始化。默认构造只是为了提供给json序列号
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月8日 上午10:22:06
 */
public class EcLogBO extends EcBaseAspectBO {
	private EcLogDTO ecLogDTO;
	private EcLogAnnotation logAnnotation;

	public EcLogBO() {
		
	}

	private static EcLogBO newInstance() {
		return new EcLogBO();
	}

	public static EcLogBO newInstantce(EcLogDTO ecLogDTO) {
		return newInstance().buildDqLogDTO(ecLogDTO);
	}

	public EcLogBO buildDqLogDTO(EcLogDTO ecLogDTO) {
		this.ecLogDTO = ecLogDTO;
		return this;
	}

	public EcLogBO buildLogAnnotation() {
		this.logAnnotation = targetMethod.getAnnotation(EcLogAnnotation.class);
		if (EcBaseUtils.isNull(this.logAnnotation)) {
			this.logAnnotation = targetClass.getAnnotation(EcLogAnnotation.class);
		}
		return this;
	}

	/**
	 * 
	 * <p>
	 * 构建日志数据
	 * </p>
	 *
	 * @param pjp
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 上午11:14:51
	 */
	public EcLogBO buildDqLogData(ProceedingJoinPoint pjp, Object targetReturnValue) {
		super.buildBaseAspectData(pjp);
		super.buildTargetReturnValue(targetReturnValue);
		
		this.ecLogDTO.setBaseAspectDTO(baseAspectDTO);
		this.buildLogger(super.targetClass);
		this.buildRequestPath();
		this.buildLogAnnotation();
		return this;
	}

	/** 根据目标class构建日志记录对象 */
	private EcLogBO buildLogger(Class<?> targetClass) {
		if (EcBaseUtils.isNull(targetClass)) {
			return this;
		}
		this.buildLogger(LoggerFactory.getLogger(targetClass));
		return this;
	}

	/**
	 * 
	 * <p>
	 * 构建请求路径
	 * </p>
	 *
	 * @return
	 * @author daiqi 创建时间 2018年2月9日 下午4:46:14
	 */
	private EcLogBO buildRequestPath() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes sra = (ServletRequestAttributes) ra;
		if (EcBaseUtils.isNull(sra)) {
			return this;
		}
		HttpServletRequest request = sra.getRequest();

		String requestPath = request.getServletPath().toString();
		if (EcStringUtils.isEmpty(requestPath)) {
			requestPath = request.getPathInfo();
		}
		if (EcStringUtils.isEmpty(requestPath)) {
			requestPath = request.getRequestURI();
		}
		return buildRequestPath(requestPath);
	}

	public EcLogBO buildRequestPath(String requestPath) {
		this.ecLogDTO.setRequestPath(requestPath);
		return this;
	}

	public EcLogBO buildBeginTimeMillis(Long beginTimeMillis) {
		this.ecLogDTO.setBeginTimeMillis(beginTimeMillis);
		return this;
	}

	public EcLogBO buildEndTimeMillis(Long endTimeMillis) {
		this.ecLogDTO.setEndTimeMillis(endTimeMillis);
		return this;
	}

	public EcLogBO buildLogger(Logger logger) {
		this.ecLogDTO.setLogger(logger);
		return this;
	}

	public EcLogDTO getDqLogDTO() {
		return ecLogDTO;
	}

	public EcLogAnnotation getDqLog() {
		return logAnnotation;
	}
}
