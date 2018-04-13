package com.easy.cloud.core.distributedlock.pojo.bo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.distributedlock.annotation.EcDistributedLock;
import com.easy.cloud.core.distributedlock.callback.EcDistributedLockCallback;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockTemplateTypeEnum;
import com.easy.cloud.core.distributedlock.pojo.dto.EcDistributedLockDTO;
import com.easy.cloud.core.distributedlock.template.EcDistributedLockTemplate;
import com.easy.cloud.core.distributedlock.template.selector.EcDistributedLockTemplateSelector;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;

/**
 * 
 * <p>
 * 分布式锁业务逻辑类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月13日 上午10:36:11
 */
public class EcDistributedLockBO {
	/** 数据传输对象 */
	private EcDistributedLockDTO distributedLockDTO;
	/** 锁模板选择器 */
	private EcDistributedLockTemplateSelector lockTemplateSelector;
	/** 锁的模板对象 */
	private EcDistributedLockTemplate lockTemplate;
	/** 切点 */
	private ProceedingJoinPoint joinPoint;

	public EcDistributedLockBO(EcDistributedLockTemplateSelector lockTemplateSelector, ProceedingJoinPoint joinPoint) {
		this.lockTemplateSelector = lockTemplateSelector;
		this.joinPoint = joinPoint;
		this.distributedLockDTO = new EcDistributedLockDTO();
	}

	public EcDistributedLockBO(EcDistributedLockDTO distributedLockDTO,
			EcDistributedLockTemplateSelector lockTemplateSelector) {
		this.distributedLockDTO = distributedLockDTO;
		this.lockTemplateSelector = lockTemplateSelector;
	}

	public EcDistributedLockBO buidDistributedLockDTOData() {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();
		Object[] args = joinPoint.getArgs();
		// 构建注解
		this.buildEcDistributedLock(targetMethod);
		this.buildLockName(targetMethod, args);
		this.buildLockTemplate(targetMethod);
		return this;
	}

	/** 根据方法构建数据传输对象的分布式注解 */
	private EcDistributedLockBO buildEcDistributedLock(Method targetMethod) {
		EcDistributedLock distributedLock = targetMethod.getAnnotation(EcDistributedLock.class);
		Assert.notNull(distributedLock, "distributedLock注解不能为空");
		this.distributedLockDTO.setDistributedLock(distributedLock);
		return this;
	}

	/**
	 * 
	 * <p>
	 * 构建锁的名称
	 * </p>
	 *
	 * @param targetMethod
	 * @param args
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月13日 上午11:37:42
	 */
	private EcDistributedLockBO buildLockName(Method targetMethod, Object[] args) {
		// 注解为空先构建注解
		if (EcBaseUtils.isNull(this.distributedLockDTO.getDistributedLock())) {
			buildEcDistributedLock(targetMethod);
		}
		EcDistributedLock distributedLock = this.distributedLockDTO.getDistributedLock();
		Assert.notNull(distributedLock, "distributedLock注解不存在");

		String lockName = distributedLock.lockName();
		if (EcStringUtils.isEmpty(lockName)) {
			if (EcArrayUtils.isEmpty(args)) {
				throw new EcBaseBusinessException(EcBaseErrorCodeEnum.OBJECT_CANT_NULL);
			}
			String param = distributedLock.param();
			int argNum = distributedLock.argNum();
			// 注解中param为empty，直接使用方法形参所在的位置的值作为lockName
			if (EcStringUtils.isEmpty(param)) {
				lockName = String.valueOf(getArgByArgNum(args, argNum));
			} else {
				Object arg = getArgByArgNum(args, argNum);
				lockName = getParamValue(arg, param);
			}
		}
		this.distributedLockDTO.setLockName(lockName);

		return this;
	}

	private EcDistributedLockBO buildLockTemplate(Method targetMethod) {
		// 注解为空先构建注解
		if (EcBaseUtils.isNull(this.distributedLockDTO.getDistributedLock())) {
			buildEcDistributedLock(targetMethod);
		}
		EcDistributedLock distributedLock = this.distributedLockDTO.getDistributedLock();
		Assert.notNull(distributedLock, "distributedLock注解不存在");
		// 根据类型选择分布式锁模板对象
		EcDistributedLockTemplate lockTemplate = this.lockTemplateSelector.selectLockTemplateByType(distributedLock.templateType());
		this.distributedLockDTO.setLockTemplate(lockTemplate);
		return this;
	}

	/**
	 * 
	 * <p>
	 * 获取args数组中指定位置的对象
	 * </p>
	 *
	 * @param args : Object ： 参数数组
	 * @param argNum  : int : 参数所在的位置
	 * @return
	 * @author daiqi 
	 * @创建时间 2018年4月13日 上午11:32:57
	 */
	private Object getArgByArgNum(Object[] args, int argNum) {
		if (argNum > 0) {
			return args[argNum - 1];
		} else {
			return args[0];
		}
	}

	/**
	 * 
	 * <p>
	 * 从方法参数获取数据
	 * </p>
	 *
	 * @param arg : Object : 方法的参数对象
	 * @param param : String : 对象中属性的名称
	 * @return
	 * @author daiqi 
	 * @创建时间 2018年4月13日 上午11:30:02
	 */
	@SuppressWarnings("unchecked")
	public String getParamValue(Object arg, String paramName) {
		if (EcBaseUtils.isNull(arg) || EcStringUtils.isEmpty(paramName)) {
			return null;
		}

		Map<String, Object> argsMap = EcJSONUtils.parseObject(arg, HashMap.class);
		return EcMapUtils.getString(argsMap, paramName);
	}

	/** 加锁 */
	public Object lock() {
		EcDistributedLockTemplateTypeEnum templateType = distributedLockDTO.getDistributedLock().templateType();
		lockTemplate = this.lockTemplateSelector.selectLockTemplateByType(templateType);
		if (this.distributedLockDTO.getDistributedLock().tryLock()) {
			return doTryLock();
		} else {
			return doLock();
		}
	}

	/** 执行锁操作 */
	private Object doLock() {
		final ProceedingJoinPoint joinPoint = this.joinPoint;
		final EcDistributedLockDTO distributedLockDTO = this.distributedLockDTO;
		return this.lockTemplate.lock(new EcDistributedLockCallback<Object>() {
			@Override
			public Object process() {
				return proceed(joinPoint);
			}

			@Override
			public EcDistributedLockDTO getDistributedLockDTO() {
				return distributedLockDTO;
			}
		});
	}

	/** 执行try锁操作 */
	private Object doTryLock() {
		final ProceedingJoinPoint joinPoint = this.joinPoint;
		final EcDistributedLockDTO distributedLockDTO = this.distributedLockDTO;
		return this.lockTemplate.tryLock(new EcDistributedLockCallback<Object>() {
			@Override
			public Object process() {
				return proceed(joinPoint);
			}

			@Override
			public EcDistributedLockDTO getDistributedLockDTO() {
				return distributedLockDTO;
			}
		});
	}

	private Object proceed(ProceedingJoinPoint pjp) {
		try {
			return pjp.proceed();
		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);
		}
	}

	public EcDistributedLockDTO getDistributedLockDTO() {
		return distributedLockDTO;
	}

}
