package com.easy.cloud.core.jdbc.audit.pojo.bo;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.Assert;

import com.easy.cloud.core.basic.factory.EcBeanFactory;
import com.easy.cloud.core.basic.pojo.bo.EcBaseAspectBO;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.reflection.utils.EcReflectionUtils;
import com.easy.cloud.core.jdbc.audit.annotation.EcAuditAnnotation;
import com.easy.cloud.core.jdbc.audit.pojo.dto.EcAuditDTO;
import com.easy.cloud.core.jdbc.audit.procced.EcBaseAuditProcced;

/**
 * jdbc组件逻辑类
 * 
 * @author daiqi
 * @date 2018年5月4日 下午10:58:40
 */
public class EcAuditBO extends EcBaseAspectBO {
	private EcAuditDTO auditDTO;
	private EcAuditAnnotation auditAnnotation;

	public EcAuditBO() {
		this(new EcAuditDTO());
	}

	public EcAuditBO(EcAuditDTO jdbcAuditorDTO) {
		this.auditDTO = jdbcAuditorDTO;
	}

	/** 构建审计者 */
	public EcAuditBO buildAuditor(Object auditor) {
		this.auditDTO.setAuditor(auditor);
		return this;
	}

	public final EcAuditBO buildAuditorData(ProceedingJoinPoint joinPoint) {
		// 构建基础切面数据
		super.buildBaseAspectData(joinPoint);
		// 构建注解
		auditAnnotation = super.targetMethod.getAnnotation(EcAuditAnnotation.class);
		// 构建实体数据
		buildEntityData();
		return this;
	}

	/** 处理方法 */
	public final Object procced() throws Throwable {
		if (EcBaseUtils.isNull(auditAnnotation)) {
			return super.joinPoint.proceed();
		}
		Class<? extends EcBaseAuditProcced> clazz = auditAnnotation.proccedClass();
		EcBaseAuditProcced auditProcced = EcBeanFactory.newInstance(clazz);
		return auditProcced.procced(this);
	}

	/** 构建实体数据 */
	private void buildEntityData() {
		Object arg = super.getTParam(1);
		Assert.notNull(arg, "save or update obj cant null");
		Class<?> entityClass = null;
		if (arg instanceof List) {
			List<?> entitys = (List<?>) arg;
			if (EcCollectionsUtils.isEmpty(entitys)) {
				throw new RuntimeException("batch save data cant null");
			}
			auditDTO.setEntitys(entitys);
			entityClass = entitys.get(0).getClass();
		} else {
			auditDTO.setEntity(arg);
			entityClass = arg.getClass();
		}
		this.auditDTO.setEntityFields(EcReflectionUtils.getDeclaredFieldsIncSup(entityClass));
	}

	public EcAuditAnnotation getAuditAnnotation() {
		return auditAnnotation;
	}

	public EcAuditDTO getAuditDTO() {
		return auditDTO;
	}
}
