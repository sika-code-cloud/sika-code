package com.easy.cloud.core.distributedlock.pojo.bo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.util.Assert;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.factory.EcBeanFactory;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.distributedlock.annotation.EcDistributedLock;
import com.easy.cloud.core.distributedlock.callback.EcDistributedLockCallback;
import com.easy.cloud.core.distributedlock.callback.result.EcDistributedLockResult;
import com.easy.cloud.core.distributedlock.callback.result.processor.EcBaseDistributedLockResultProcessor;
import com.easy.cloud.core.distributedlock.callback.result.processor.EcDefaultDistributedLockResultProcessor;
import com.easy.cloud.core.distributedlock.constant.EcDistributedLockConstant.EcDistributedLockTemplateTypeEnum;
import com.easy.cloud.core.distributedlock.constant.error.EcDistributedLockErrorCodeEnum;
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
	/** 目标方法 */
	private Method targetMethod;
	/** 分布式锁结果处理器 */
	private EcBaseDistributedLockResultProcessor resultProcessor;

	public EcDistributedLockBO(EcDistributedLockTemplateSelector lockTemplateSelector, ProceedingJoinPoint joinPoint) {
		this.lockTemplateSelector = lockTemplateSelector;
		this.distributedLockDTO = new EcDistributedLockDTO();
		this.joinPoint = joinPoint;
	}

	public EcDistributedLockBO() {
		
	}

	public EcDistributedLockBO buidDistributedLockDTOData() {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		targetMethod = methodSignature.getMethod();
		Object[] args = joinPoint.getArgs();
		// 构建注解
		this.buildEcDistributedLock();
		this.buildLockName(args);
		this.buildLockTemplate();
		this.buildDistributedLockResultProcessor();
		return this;
	}

	/** 根据方法构建数据传输对象的分布式注解 */
	private EcDistributedLockBO buildEcDistributedLock() {
		// 注解为空先构建注解
		if (EcBaseUtils.isNull(this.distributedLockDTO.getDistributedLock())) {
			EcDistributedLock distributedLock = targetMethod.getAnnotation(EcDistributedLock.class);
			Assert.notNull(distributedLock, "distributedLock注解不能为空");
			this.distributedLockDTO.setDistributedLock(distributedLock);
		}
		return this;
	}

	/**
	 * 
	 * <p>
	 * 构建锁的名称
	 * </p>
	 *
	 * @param args
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月13日 上午11:37:42
	 */
	private EcDistributedLockBO buildLockName( Object[] args) {
		// 注解为空先构建注解
		EcDistributedLock distributedLock = buildEcDistributedLock().distributedLockDTO.getDistributedLock();
		
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
		if (EcStringUtils.isEmpty(lockName)) {
			throw new EcBaseBusinessException(EcDistributedLockErrorCodeEnum.LOCK_NAME_CANT_EMPTY);
		}
		this.distributedLockDTO.setLockName(lockName);

		return this;
	}

	private EcDistributedLockBO buildLockTemplate() {
		// 先构建注解
		EcDistributedLock distributedLock = buildEcDistributedLock().distributedLockDTO.getDistributedLock();
		
		// 根据类型选择分布式锁模板对象
		EcDistributedLockTemplate lockTemplate = this.lockTemplateSelector.selectLockTemplateByType(distributedLock.templateType());
		this.distributedLockDTO.setLockTemplate(lockTemplate);
		return this;
	}
	
	/**
	 * 
	 * <p>
	 * 构建分布式锁结果处理对象
	 * </p>
	 * 
	 * @return
	 * @author daiqi
	 * @创建时间 2018年4月14日 下午12:00:15
	 */
	private EcDistributedLockBO buildDistributedLockResultProcessor() {
		// 先构建注解
		EcDistributedLock distributedLock = buildEcDistributedLock().distributedLockDTO.getDistributedLock();
		Class<? extends EcBaseDistributedLockResultProcessor> resultProcessorClass = distributedLock.resultProcessorClass();
		// 结果处理类class为空 使用默认的结果处理类进行处理
		if (EcBaseUtils.isNull(resultProcessorClass)) {
			resultProcessorClass = EcDefaultDistributedLockResultProcessor.class;
		}
		// 根据class创建结果处理对象
		resultProcessor = EcBeanFactory.newInstance(resultProcessorClass);
		if (EcBaseUtils.isNull(resultProcessor)) {
			throw new EcBaseBusinessException(EcDistributedLockErrorCodeEnum.RESULT_PROCESSOR_OBJ_CREATE_FAIL);
		}
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
		return this.lockTemplate.lock(new EcDistributedLockCallback<Object>() {
			@Override
			public Object process(EcDistributedLockResult lockResult) {
				return resultProcessor.proceed(joinPoint, lockResult);
			}

			@Override
			public EcDistributedLockDTO getDistributedLockDTO() {
				return distributedLockDTO;
			}
		});
	}

	/** 执行try锁操作 */
	private Object doTryLock() {
		return this.lockTemplate.tryLock(new EcDistributedLockCallback<Object>() {
			@Override
			public Object process(EcDistributedLockResult lockResult) {
				return resultProcessor.proceed(joinPoint, lockResult);
			}

			@Override
			public EcDistributedLockDTO getDistributedLockDTO() {
				return distributedLockDTO;
			}
		});
	}


	public EcDistributedLockDTO getDistributedLockDTO() {
		return distributedLockDTO;
	}

}
