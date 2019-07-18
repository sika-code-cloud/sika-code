package com.sika.code.basic.pojo.bo;

import com.sika.code.basic.pojo.dto.BaseAspectDTO;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.array.ArrayUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.ServletRequest;
import java.lang.reflect.Method;

/**
 * 切面业务逻辑对象类
 * 
 * @author daiqi
 * @date 2018年4月20日 下午11:09:39
 */
public class BaseAspectBO {
	protected BaseAspectDTO baseAspectDTO;
	protected Method targetMethod;
	protected Class<?> targetClass;
	protected ProceedingJoinPoint joinPoint;

	public BaseAspectBO() {
		this.baseAspectDTO = new BaseAspectDTO();
	}

	public BaseAspectBO(BaseAspectDTO baseAspectDTO) {
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
		Object [] ags = new Object[joinPoint.getArgs().length];
		for (int i = 0 ; i < joinPoint.getArgs().length ; ++i) {
			Object arg = joinPoint.getArgs()[i];
			if (arg instanceof ServletRequest) {
				ags[i] = arg.getClass();
			} else {
				ags[i] = arg;
			}
		}
		this.buildTargetParameterValues(ags);
		this.buildTargetReturnType(targetMethod.getReturnType()).buildTargetClass(targetClass);

	}

	/** 获取目标方法执行的返回值---转换为范型class对应的对象 */
	@SuppressWarnings("unchecked")
	public final <T> T getTReturnValue(final Class<T> clazz) {
		if (BaseUtil.isNull(clazz)) {
			return null;
		}
		Object returnValue = proceed();
		return (T) returnValue;
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
	@SuppressWarnings("unchecked")
	public final <T> T getTParam(final Class<T> clazz) {
		if (BaseUtil.isNull(clazz)) {
			return null;
		}
		Object[] args = joinPoint.getArgs();
		if (ArrayUtil.isEmpty(args)) {
			return null;
		}
		for (Object arg : args) {
			if (BaseUtil.isNull(arg)) {
				continue;
			}
			if (clazz.isAssignableFrom(arg.getClass())) {
				return (T) arg;
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
					"指定参数的位置越界:paramPosition = " + paramPosition + ",argsLengh = " + args.length);
		}
		return (T) args[paramPosition - 1];
	}

	private BaseAspectBO buildTargetParameterValues(Object[] targetParameterValues) {
		this.baseAspectDTO.setTargetParameterValues(targetParameterValues);
		return this;
	}

	private BaseAspectBO buildTargetParameterTypes(Class<?>[] targetParameterTypes) {
		this.baseAspectDTO.setTargetParameterTypes(targetParameterTypes);
		return this;
	}

	private BaseAspectBO buildTargetMethodName(String targetMethodName) {
		this.baseAspectDTO.setTargetMethodName(targetMethodName);
		return this;
	}

	private BaseAspectBO buildTargetClassName(String targetClassName) {
		this.baseAspectDTO.setTargetClassName(targetClassName);
		return this;
	}

	private BaseAspectBO buildTargetReturnType(Class<?> targetReturnType) {
		this.baseAspectDTO.setTargetReturnType(targetReturnType);
		return this;
	}

	protected BaseAspectBO buildTargetReturnValue(Object targetReturnValue) {
		this.baseAspectDTO.setTargetReturnValue(targetReturnValue);
		return this;
	}

	private BaseAspectBO buildTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
		return this;
	}

	public BaseAspectBO buildTargetMethod(Method targetMethod) {
		this.targetMethod = targetMethod;
		return this;
	}

	public BaseAspectDTO getBaseAspectDTO() {
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
