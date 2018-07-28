package com.easy.cloud.core.jdbc.audit.config;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.jdbc.audit.pojo.dto.EcAuditConfigDTO;

/**
 * 
 * <p>
 * 审计配置类
 * </p>
 *
 * <pre>
 *  说明：子类可以通过继承此 调用静态的put方法配置需要审计的方法
 *  约定：为了正确的初始化，子类配置审计的方法应该放在static块中,同时在类上使用@Component注解将配置类交给容器管理
 *  使用示例：static {initData()}  public static void initData() {put(new EcAuditConfigDTO("save", EcActionType.SAVE, EcType.SAVE); )}
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月11日 上午10:24:02
 */
@Component
public class EcAuditConfig {
	private static Map<String, EcAuditConfigDTO> AUDIT_CONFIG_CONTAINER = EcMapUtils.newHashMap();
	
	/**
	 * 
	 * <p>
	 * 将auditConfigDTO对象put到审计配置容器中
	 * </p>
	 *
	 * <pre>
	 *	使用audtiMethodName为key，auditConfigDTO对象为容器的值
	 * </pre>
	 *
	 * @param auditConfigDTO
	 * @author daiqi
	 * @创建时间 2018年5月11日 上午9:40:41
	 */
	public static void put(EcAuditConfigDTO auditConfigDTO) {
		AUDIT_CONFIG_CONTAINER.put(auditConfigDTO.getAuditMethodName(), auditConfigDTO);
	}
	
	/**
	 * 
	 * <p>
	 * 根据审计方法名称获取审计配置数据传输对象
	 * </p>
	 *
	 * @param auditMethodName
	 * @return
	 * @author daiqi
	 * @创建时间 2018年5月11日 上午9:53:24
	 */
	public static EcAuditConfigDTO get(String auditMethodName) {
		return AUDIT_CONFIG_CONTAINER.get(auditMethodName);
	}
}
