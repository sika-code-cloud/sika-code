package com.easy.cloud.core.common.log.pojo.bo;

import java.lang.reflect.Method;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.log.annotation.EcLog;
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
public class EcLogBO {
	private EcLogDTO ecLogDTO;
	private EcLog ecLog;

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
	
	public EcLogBO buildDqLog(EcLog ecLog) {
		this.ecLog = ecLog;
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
	 * @author daiqi
	 * 创建时间    2018年2月9日 上午11:14:51
	 */
	public EcLogBO buildDqLogData(ProceedingJoinPoint pjp){
		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();
		Class<?> targetClass = pjp.getTarget().getClass();
		
		this.buildTargetClassName(targetClass.getName()).buildTargetMethodName(targetMethod.getName());
		this.buildTargetParameterTypes(targetMethod.getParameterTypes()).buildTargetParameterValues(pjp.getArgs());
		this.buildTargetReturnType(targetMethod.getReturnType()).buildLogger(targetClass);
		this.buildRequestPath().buildDqLog(targetClass, targetMethod);
		
		return this;
	}
	/**
	 * 
	 * <p>
	 * 根据切点获取日志注解
	 * </p>
	 *
	 * @param joinPoint
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月9日 下午7:36:32
	 */
	private void buildDqLog(Class<?> targetClass, Method targetMethod){
		EcLog ecLog = targetMethod.getAnnotation(EcLog.class);
		if (EcBaseUtils.isNotNull(ecLog)){
			buildDqLog(ecLog);
		}else{
			buildDqLog(targetClass.getAnnotation(EcLog.class));
		}
	}
	/** 根据目标class构建日志记录对象 */
	private EcLogBO buildLogger(Class<?> targetClass){
		if(EcBaseUtils.isNull(targetClass)){
			return this;
		}
		this.buildLogger( LoggerFactory.getLogger(targetClass));
		return this;
	}
	/**
	 * 
	 * <p>
	 * 构建请求路径
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月9日 下午4:46:14
	 */
	private EcLogBO buildRequestPath(){
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        if (EcBaseUtils.isNull(sra)) {
        	return this;
        }
        HttpServletRequest request = sra.getRequest();

        String requestPath = request.getServletPath().toString();
        if (EcStringUtils.isEmpty(requestPath)){
        	requestPath = request.getPathInfo();
        }
        if (EcStringUtils.isEmpty(requestPath)){
        	requestPath = request.getRequestURI();
        }
        return buildRequestPath(requestPath);
	}
	public EcLogBO buildTargetParameterValues(Object[] targetParameterValues) {
		this.ecLogDTO.setTargetParameterValues(targetParameterValues);
		return this;
	}

	public EcLogBO buildTargetParameterTypes(Class<?>[] targetParameterTypes) {
		this.ecLogDTO.setTargetParameterTypes(targetParameterTypes);
		return this;
	}

	public EcLogBO buildTargetMethodName(String targetMethodName) {
		this.ecLogDTO.setTargetMethodName(targetMethodName);
		return this;
	}

	public EcLogBO buildTargetClassName(String targetClassName) {
		this.ecLogDTO.setTargetClassName(targetClassName);
		return this;
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

	public EcLogBO buildTargetReturnType(Class<?> targetReturnType) {
		this.ecLogDTO.setTargetReturnType(targetReturnType);
		return this;
	}

	public EcLogBO buildTargetReturnValue(Object targetReturnValue) {
		this.ecLogDTO.setTargetReturnValue(targetReturnValue);
		return this;
	}
	
	public EcLogBO buildLogger(Logger logger) {
		this.ecLogDTO.setLogger(logger);
		return this;
	}
	
	public EcLogDTO getDqLogDTO() {
		return ecLogDTO;
	}
	public EcLog getDqLog() {
		return ecLog;
	}
}
