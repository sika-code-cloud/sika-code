package com.easy.cloud.core.basic.pojo.bo;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import com.easy.cloud.core.basic.pojo.dto.EcBaseAspectDTO;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;

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
	protected ProceedingJoinPoint joinPoint;

	public EcBaseAspectBO() {
		this.baseAspectDTO = new EcBaseAspectDTO();
	}

	public EcBaseAspectBO(EcBaseAspectDTO baseAspectDTO) {
		this.baseAspectDTO = baseAspectDTO;
	}

	protected void buildBaseAspectData(final ProceedingJoinPoint pjp) {
		this.joinPoint = pjp;
		Signature signature = this.joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		this.targetMethod = methodSignature.getMethod();
		Class<?> targetClass = this.joinPoint.getTarget().getClass();
		this.buildTargetClass(targetClass).buildTargetMethod(targetMethod);
		this.buildTargetClassName(targetClass.getName()).buildTargetMethodName(targetMethod.getName());
		this.buildTargetParameterTypes(targetMethod.getParameterTypes());
		this.buildTargetParameterValues(this.joinPoint.getArgs());
		this.buildTargetReturnType(targetMethod.getReturnType()).buildTargetClass(targetClass);

	}

	/** 获取目标方法执行的返回值---转换为范型class对应的对象 */
	public final <T> T getTReturnValue(final Class<T> clazz) {
		if (EcBaseUtils.isNull(clazz)) {
			return null;
		}
		Object returnValue = proceed();
		return EcJSONUtils.parseObject(returnValue, clazz);
	}

	/**
	 * 
	 * <p>
	 * 获取方法参数中范型class对应的值
	 * </p>
	 *
	 * <pre>
	 * 若方法行参列表存在多个同类型的参数则默认取第一个
	 * </pre>
	 *
	 * @param clazz
	 * @return
	 *
	 * @author daiqi
	 * @创建时间 2018年4月26日 下午11:46:47
	 */
	public final <T> T getTParam(final Class<T> clazz) {
		if (EcBaseUtils.isNull(clazz)) {
			return null;
		}
		Object[] args = joinPoint.getArgs();
		if (EcArrayUtils.isEmpty(args)) {
			return null;
		}
		for (Object arg : args) {
			if (EcBaseUtils.isNull(arg)) {
				continue;
			}
			if (EcBaseUtils.equals(arg.getClass(), clazz)) {
				return EcJSONUtils.parseObject(arg, clazz);
			}
		}
		return null;
	}

	/** 根据形参位置获取泛型参数值 */
	@SuppressWarnings("unchecked")
	public final <T> T getTParam(final int paramPosition) {
		int tempPosition = paramPosition;
		Object[] args = joinPoint.getArgs();
		if (tempPosition <= 0) {
			tempPosition = 1;
		}
		if (paramPosition > args.length) {
			throw new IndexOutOfBoundsException(
					"redis指定参数的位置越界:paramPosition = " + paramPosition + ",argsLengh = " + args.length);
		}
		return (T) args[paramPosition - 1];
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

	public Object proceed() {
		try {
			return joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public Method getTargetMethod() {
		return targetMethod;
	}

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public ProceedingJoinPoint getJoinPoint() {
		return joinPoint;
	}

}
