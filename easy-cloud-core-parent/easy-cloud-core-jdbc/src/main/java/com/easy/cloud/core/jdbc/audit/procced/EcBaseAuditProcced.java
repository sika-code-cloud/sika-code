package com.easy.cloud.core.jdbc.audit.procced;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Id;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.util.Assert;

import com.easy.cloud.core.basic.factory.EcBeanFactory;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.jdbc.audit.annotation.EcAuditAnnotation;
import com.easy.cloud.core.jdbc.audit.constant.EcAuditConstant.EcActionType;
import com.easy.cloud.core.jdbc.audit.constant.EcAuditConstant.EcType;
import com.easy.cloud.core.jdbc.audit.pojo.bo.EcAuditBO;
import com.easy.cloud.core.jdbc.audit.pojo.dto.EcAuditDTO;
import com.easy.cloud.core.jdbc.base.primarykey.EcBasePrimaryKeyGenerator;
import com.easy.cloud.core.jdbc.base.primarykey.annotation.EcGenericGenerator;

/**
 * 
 * <p>
 * 审计处理类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月5日 下午12:52:14
 */
public abstract class EcBaseAuditProcced {
	protected EcAuditBO auditBO;
	protected EcAuditDTO auditDTO;
	protected EcAuditAnnotation auditAnnotation;
	protected ProceedingJoinPoint joinPoint;

	/** 处理方法 */
	public final Object procced(EcAuditBO auditBO) throws Throwable {
		initData(auditBO);
		return doProcced();
	}

	private final Object doProcced() throws Throwable {
		Object[] newArgs = buildNewArgs();
		if (EcArrayUtils.isNotEmpty(newArgs)) {
			return joinPoint.proceed(newArgs);
		}
		return joinPoint.proceed();
	}

	private final void initData(EcAuditBO auditBO) {
		this.auditBO = auditBO;
		this.auditDTO = auditBO.getAuditDTO();
		this.joinPoint = auditBO.getJoinPoint();
		this.auditAnnotation = auditBO.getAuditAnnotation();
	}

	/** 构建新的参数 */
	protected final Object[] buildNewArgs() throws Exception {
		int actionType = auditAnnotation.actionType();
		Assert.notNull(actionType, "actionTypeEnum cant null");
		switch (actionType) {
		case EcActionType.SAVE:
			return doSave();
		case EcActionType.UPDATE:
			return doUpdate();
		case EcActionType.SAVE_BATCH:
			return doSaveBatch();
		case EcActionType.UPDATE_BATCH:
			return doUpdateBatch();
		default:
			return doOrtherAction(actionType);
		}
	}

	/** 执行其他的操作类型---子类可以通过重写此方法来实现定制化需求 */
	protected Object[] doOrtherAction(int actionType) {
		return joinPoint.getArgs();
	}

	/**
	 * 执行保存
	 * 
	 * @throws Exception
	 */
	protected Object[] doSave() throws Exception {
		return doSingleCore();
	}

	/**
	 * 执行更新
	 * 
	 * @throws Exception
	 */
	protected Object[] doUpdate() throws Exception {
		return doSingleCore();
	}

	/** 执行单个实体核心方法 */
	protected Object[] doSingleCore() throws Exception {
		Object[] args = joinPoint.getArgs();
		args[0] = buildNewEntity(auditDTO.getEntity());
		return args;
	}

	/** 执行批量保存 */
	protected Object[] doSaveBatch() throws Exception {
		return doBatchCore();
	}

	/** 执行批量更新 */
	protected Object[] doUpdateBatch() throws Exception {
		return doBatchCore();
	}

	/** 执行批量核心方法 */
	protected Object[] doBatchCore() throws Exception {
		for (Object entity : auditDTO.getEntitys()) {
			buildNewEntity(entity);
		}
		Object[] args = joinPoint.getArgs();
		args[0] = auditDTO.getEntitys();
		return args;
	}

	/**
	 * 
	 * <p>
	 * 构建新的持久化对象
	 * </p>
	 *
	 * @return
	 * @throws Exception
	 * @author daiqi
	 * @创建时间 2018年5月5日 下午2:21:18
	 */
	protected final Object buildNewEntity(final Object entity) throws Exception {
		Date currentDate = EcDateUtils.getCurrentDate();
		for (Field field : auditDTO.getEntityFields()) {
			field.setAccessible(true);
			boolean isSaveType = auditAnnotation.type() == EcType.SAVE;
			// 主键生成器
			if (isSaveType && field.isAnnotationPresent(Id.class)) {
				Serializable primaryKey = getPrimarykey(entity, field, isSaveType);
				if (EcBaseUtils.isNotNull(primaryKey)) {
					field.set(entity, primaryKey);
					continue;
				}
			}
			// 审计设置
			if (field.isAnnotationPresent(CreatedBy.class) && isSaveType) {
				field.set(entity, auditDTO.getAuditor());
			} else if (field.isAnnotationPresent(CreatedDate.class) && isSaveType) {
				field.set(entity, currentDate);
			} else if (field.isAnnotationPresent(LastModifiedDate.class)) {
				field.set(entity, currentDate);
			} else if (field.isAnnotationPresent(LastModifiedBy.class)) {
				field.set(entity, auditDTO.getAuditor());
			}
		}
		return entity;
	}

	/**
	 * 
	 * <p>
	 * 获取主键
	 * </p>
	 *
	 * @param entity
	 * @param field
	 * @param isSaveType
	 * @return
	 * @author daiqi
	 * @创建时间 2018年5月8日 下午8:51:18
	 */
	private final Serializable getPrimarykey(Object entity, Field field, boolean isSaveType) {
		EcGenericGenerator generatorAnnotation = field.getAnnotation(EcGenericGenerator.class);
		if (generatorAnnotation == null) {
			return null;
		}
		Class<? extends EcBasePrimaryKeyGenerator> clazz = generatorAnnotation.primaryKeyGeneratorClass();
		Assert.notNull(clazz, "primaryKeyGeneratorClass cant null");
		EcBasePrimaryKeyGenerator basePrimaryKeyGenerator = EcBeanFactory.newInstance(clazz);
		Assert.notNull(clazz, "primaryKeyGenerator obj create fail");
		return basePrimaryKeyGenerator.generate(entity);
	}
}
