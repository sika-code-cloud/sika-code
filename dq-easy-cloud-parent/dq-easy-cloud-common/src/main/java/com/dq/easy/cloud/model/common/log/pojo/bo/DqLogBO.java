package com.dq.easy.cloud.model.common.log.pojo.bo;

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
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.log.annotation.DqLog;
import com.dq.easy.cloud.model.common.log.pojo.dto.DqLogDTO;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;

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
public class DqLogBO {
	private DqLogDTO dqLogDTO;
	private DqLog dqLog;

	private static DqLogBO newInstance() {
		return new DqLogBO();
	}

	public static DqLogBO newInstantce(DqLogDTO dqLogDTO) {
		return newInstance().buildDqLogDTO(dqLogDTO);
	}

	public DqLogBO buildDqLogDTO(DqLogDTO dqLogDTO) {
		this.dqLogDTO = dqLogDTO;
		return this;
	}
	
	public DqLogBO buildDqLog(DqLog dqLog) {
		this.dqLog = dqLog;
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
	public DqLogBO buildDqLogData(ProceedingJoinPoint pjp){
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
		DqLog dqLog = targetMethod.getAnnotation(DqLog.class);
		if (DqBaseUtils.isNotNull(dqLog)){
			buildDqLog(dqLog);
		}else{
			buildDqLog(targetClass.getAnnotation(DqLog.class));
		}
	}
	/** 根据目标class构建日志记录对象 */
	private DqLogBO buildLogger(Class<?> targetClass){
		if(DqBaseUtils.isNull(targetClass)){
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
	private DqLogBO buildRequestPath(){
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String requestPath = request.getServletPath().toString();
        if (DqStringUtils.isEmpty(requestPath)){
        	requestPath = request.getPathInfo();
        }
        if (DqStringUtils.isEmpty(requestPath)){
        	requestPath = request.getRequestURI();
        }
        return buildRequestPath(requestPath);
	}
	public DqLogBO buildTargetParameterValues(Object[] targetParameterValues) {
		this.dqLogDTO.setTargetParameterValues(targetParameterValues);
		return this;
	}

	public DqLogBO buildTargetParameterTypes(Class<?>[] targetParameterTypes) {
		this.dqLogDTO.setTargetParameterTypes(targetParameterTypes);
		return this;
	}

	public DqLogBO buildTargetMethodName(String targetMethodName) {
		this.dqLogDTO.setTargetMethodName(targetMethodName);
		return this;
	}

	public DqLogBO buildTargetClassName(String targetClassName) {
		this.dqLogDTO.setTargetClassName(targetClassName);
		return this;
	}

	public DqLogBO buildRequestPath(String requestPath) {
		this.dqLogDTO.setRequestPath(requestPath);
		return this;
	}

	public DqLogBO buildBeginTimeMillis(Long beginTimeMillis) {
		this.dqLogDTO.setBeginTimeMillis(beginTimeMillis);
		return this;
	}

	public DqLogBO buildEndTimeMillis(Long endTimeMillis) {
		this.dqLogDTO.setEndTimeMillis(endTimeMillis);
		return this;
	}

	public DqLogBO buildTargetReturnType(Class<?> targetReturnType) {
		this.dqLogDTO.setTargetReturnType(targetReturnType);
		return this;
	}

	public DqLogBO buildTargetReturnValue(Object targetReturnValue) {
		this.dqLogDTO.setTargetReturnValue(targetReturnValue);
		return this;
	}
	
	public DqLogBO buildLogger(Logger logger) {
		this.dqLogDTO.setLogger(logger);
		return this;
	}
	
	public DqLogDTO getDqLogDTO() {
		return dqLogDTO;
	}
	public DqLog getDqLog() {
		return dqLog;
	}
}
