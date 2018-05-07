package com.easy.cloud.core.jdbc.audit.pojo.dto;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 
 * <p>
 * 审计数据传输对象
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月5日 上午11:37:27
 */
public class EcAuditDTO {
	/** 实体对象的属性列表 */
	private List<Field> entityFields;
	/** 实体对象列表 */
	private List<?> entitys;
	/** 实体对象 */
	private Object entity;
	/** 审计者 */
	private Object auditor;

	public List<?> getEntitys() {
		return entitys;
	}

	public void setEntitys(List<?> entitys) {
		this.entitys = entitys;
	}


	public Object getEntity() {
		return entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public List<Field> getEntityFields() {
		return entityFields;
	}

	public void setEntityFields(List<Field> entityFields) {
		this.entityFields = entityFields;
	}

	public Object getAuditor() {
		return auditor;
	}

	public void setAuditor(Object auditor) {
		this.auditor = auditor;
	}
	
}
