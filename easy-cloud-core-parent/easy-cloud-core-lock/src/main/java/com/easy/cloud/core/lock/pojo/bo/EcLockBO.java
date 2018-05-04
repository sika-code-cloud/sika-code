package com.easy.cloud.core.lock.pojo.bo;

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
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.core.lock.annotation.EcLockAnnotation;
import com.easy.cloud.core.lock.callback.EcLockCallback;
import com.easy.cloud.core.lock.callback.result.EcLockResult;
import com.easy.cloud.core.lock.callback.result.processor.EcBaseLockResultProcessor;
import com.easy.cloud.core.lock.callback.result.processor.EcDefaultLockResultProcessor;
import com.easy.cloud.core.lock.constant.EcLockConstant.EcLockTemplateTypeEnum;
import com.easy.cloud.core.lock.constant.error.EcLockErrorCodeEnum;
import com.easy.cloud.core.lock.pojo.dto.EcLockDTO;
import com.easy.cloud.core.lock.template.EcLockTemplate;
import com.easy.cloud.core.lock.template.selector.EcLockTemplateSelector;

/**
 * 
 * <p>
 * 分布式锁业务逻辑类
 * </p>
 *
 * @author daiqi 创建时间 2018年4月13日 上午10:36:11
 */
public class EcLockBO {
	/** 数据传输对象 */
	private EcLockDTO lockDTO;
	/** 锁模板选择器 */
	private EcLockTemplateSelector lockTemplateSelector;
	/** 锁的模板对象 */
	private EcLockTemplate lockTemplate;
	/** 切点 */
	private ProceedingJoinPoint joinPoint;
	/** 目标方法 */
	private Method targetMethod;
	/** 分布式锁结果处理器 */
	private EcBaseLockResultProcessor resultProcessor;

	public EcLockBO(EcLockTemplateSelector lockTemplateSelector, ProceedingJoinPoint joinPoint) {
		this.lockTemplateSelector = lockTemplateSelector;
		this.lockDTO = new EcLockDTO();
		this.joinPoint = joinPoint;
	}

	public EcLockBO() {
		
	}

	public EcLockBO buidDistributedLockDTOData() {
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
	private EcLockBO buildEcDistributedLock() {
		// 注解为空先构建注解
		if (EcBaseUtils.isNull(this.lockDTO.getLockAnnotation())) {
			EcLockAnnotation distributedLock = targetMethod.getAnnotation(EcLockAnnotation.class);
			Assert.notNull(distributedLock, "distributedLock注解不能为空");
			this.lockDTO.setLock(distributedLock);
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
	private EcLockBO buildLockName( Object[] args) {
		// 注解为空先构建注解
		EcLockAnnotation distributedLock = buildEcDistributedLock().lockDTO.getLockAnnotation();
		
		String lockName = distributedLock.nameBody();
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
			throw new EcBaseBusinessException(EcLockErrorCodeEnum.LOCK_NAME_CANT_EMPTY);
		}
		this.lockDTO.setLockName(lockName);

		return this;
	}

	private EcLockBO buildLockTemplate() {
		// 先构建注解
		EcLockAnnotation distributedLock = buildEcDistributedLock().lockDTO.getLockAnnotation();
		
		// 根据类型选择分布式锁模板对象
		EcLockTemplate lockTemplate = this.lockTemplateSelector.selectLockTemplateByType(distributedLock.templateType());
		this.lockDTO.setLockTemplate(lockTemplate);
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
	private EcLockBO buildDistributedLockResultProcessor() {
		// 先构建注解
		EcLockAnnotation distributedLock = buildEcDistributedLock().lockDTO.getLockAnnotation();
		Class<? extends EcBaseLockResultProcessor> resultProcessorClass = distributedLock.resultProcessorClass();
		// 结果处理类class为空 使用默认的结果处理类进行处理
		if (EcBaseUtils.isNull(resultProcessorClass)) {
			resultProcessorClass = EcDefaultLockResultProcessor.class;
		}
		// 根据class创建结果处理对象
		resultProcessor = EcBeanFactory.newInstance(resultProcessorClass);
		if (EcBaseUtils.isNull(resultProcessor)) {
			throw new EcBaseBusinessException(EcLockErrorCodeEnum.RESULT_PROCESSOR_OBJ_CREATE_FAIL);
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
		EcLockTemplateTypeEnum templateType = lockDTO.getLockAnnotation().templateType();
		lockTemplate = this.lockTemplateSelector.selectLockTemplateByType(templateType);
		if (this.lockDTO.getLockAnnotation().tryLock()) {
			return doTryLock();
		} else {
			return doLock();
		}
	}

	/** 执行锁操作 */
	private Object doLock() {
		return this.lockTemplate.lock(new EcLockCallback<Object>() {
			@Override
			public Object process(EcLockResult lockResult) {
				return resultProcessor.proceed(joinPoint, lockResult);
			}

			@Override
			public EcLockDTO getDistributedLockDTO() {
				return lockDTO;
			}
		});
	}

	/** 执行try锁操作 */
	private Object doTryLock() {
		return this.lockTemplate.tryLock(new EcLockCallback<Object>() {
			@Override
			public Object process(EcLockResult lockResult) {
				return resultProcessor.proceed(joinPoint, lockResult);
			}

			@Override
			public EcLockDTO getDistributedLockDTO() {
				return lockDTO;
			}
		});
	}


	public EcLockDTO getDistributedLockDTO() {
		return lockDTO;
	}

}
