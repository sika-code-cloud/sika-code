package com.easy.cloud.core.basic.pojo.bo;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.easy.cloud.core.basic.pojo.dto.EcBaseAspectDTO;

/**
 * 切面业务逻辑对象类
 * 
 * @author daiqi
 * @date 2018年4月20日 下午11:09:39
 */
public class EcBaseAspectBO {
	protected EcBaseAspectDTO baseAspectDTO;
	protected Method targetMethod;
	protected Class<?> targetClass;
	public EcBaseAspectBO() {
		this.baseAspectDTO = new EcBaseAspectDTO();
	}

	public EcBaseAspectBO(EcBaseAspectDTO baseAspectDTO) {
		this.baseAspectDTO = baseAspectDTO;
	}
	
	protected void buildBaseAspectData(ProceedingJoinPoint pjp) {
		Signature signature = pjp.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();
		Class<?> targetClass = pjp.getTarget().getClass();
		
		this.buildTargetClass(targetClass).buildTargetMethod(targetMethod);
		this.buildTargetClassName(targetClass.getName()).buildTargetMethodName(targetMethod.getName());
		this.buildTargetParameterTypes(targetMethod.getParameterTypes()).buildTargetParameterValues(pjp.getArgs());
		this.buildTargetReturnType(targetMethod.getReturnType()).buildTargetClass(targetClass);
		
	}
	
	private EcBaseAspectBO buildTargetParameterValues(Object[] targetParameterValues) {
		this.baseAspectDTO.setTargetParameterValues(targetParameterValues);
		return this;
	}

	private EcBaseAspectBO buildTargetParameterTypes(Class<?>[] targetParameterTypes) {
		this.baseAspectDTO.setTargetParameterTypes(targetParameterTypes);
		return this;
	}

	private EcBaseAspectBO buildTargetMethodName(String targetMethodName) {
		this.baseAspectDTO.setTargetMethodName(targetMethodName);
		return this;
	}

	private EcBaseAspectBO buildTargetClassName(String targetClassName) {
		this.baseAspectDTO.setTargetClassName(targetClassName);
		return this;
	}

	private EcBaseAspectBO buildTargetReturnType(Class<?> targetReturnType) {
		this.baseAspectDTO.setTargetReturnType(targetReturnType);
		return this;
	}

	protected EcBaseAspectBO buildTargetReturnValue(Object targetReturnValue) {
		this.baseAspectDTO.setTargetReturnValue(targetReturnValue);
		return this;
	}
	private EcBaseAspectBO buildTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
		return this;
	}

	public EcBaseAspectBO buildTargetMethod(Method targetMethod) {
		this.targetMethod = targetMethod;
		return this;
	}

	public EcBaseAspectDTO getBaseAspectDTO() {
		return baseAspectDTO;
	}
	
}
