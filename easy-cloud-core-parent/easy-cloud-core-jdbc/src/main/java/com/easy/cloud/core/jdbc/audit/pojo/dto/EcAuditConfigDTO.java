package com.easy.cloud.core.jdbc.audit.pojo.dto;

import com.easy.cloud.core.jdbc.audit.procced.EcBaseAuditProcced;
import com.easy.cloud.core.jdbc.audit.procced.impl.EcDefaultAuditProcced;

/**
 * 
 * <p>
 * 审计配置数据传输对象
 * </p>
 *
 * <pre>
 *  说明：若数据处理层不能通过注解实现审计功能，则可以通过该类来配置需要审计的方法
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月11日 上午9:28:07
 */
public class EcAuditConfigDTO {
	private String auditMethodName;
	private int actionType;
	private int type;
	private Class<? extends EcBaseAuditProcced> proccedClass;

	public EcAuditConfigDTO(String auditMethodName, int actionType, int type) {
		this(auditMethodName, actionType, type, EcDefaultAuditProcced.class);
	}

	public EcAuditConfigDTO(String auditMethodName, int actionType, int type,
			Class<? extends EcBaseAuditProcced> proccedClass) {
		this.auditMethodName = auditMethodName;
		this.actionType = actionType;
		this.type = type;
		this.proccedClass = proccedClass;
	}
	
	public String getAuditMethodName() {
		return auditMethodName;
	}

	public void setAuditMethodName(String auditMethodName) {
		this.auditMethodName = auditMethodName;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Class<? extends EcBaseAuditProcced> getProccedClass() {
		return proccedClass;
	}

	public void setProccedClass(Class<? extends EcBaseAuditProcced> proccedClass) {
		this.proccedClass = proccedClass;
	}

}
