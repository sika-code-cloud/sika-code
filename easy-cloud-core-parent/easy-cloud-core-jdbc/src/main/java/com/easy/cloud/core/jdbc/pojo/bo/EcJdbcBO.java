package com.easy.cloud.core.jdbc.pojo.bo;

import java.lang.reflect.Field;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.easy.cloud.core.basic.pojo.bo.EcBaseAspectBO;
import com.easy.cloud.core.common.array.EcArrayUtils;
import com.easy.cloud.core.common.reflection.utils.EcReflectionUtils;
import com.easy.cloud.core.jdbc.pojo.dto.EcJdbcDTO;

/**
 * jdbc组件逻辑类
 * @author daiqi
 * @date 2018年5月4日 下午10:58:40
 */
public class EcJdbcBO extends EcBaseAspectBO{
	private EcJdbcDTO jdbcDTO;

	public EcJdbcBO() {
		this(new EcJdbcDTO());
	}

	public EcJdbcBO(EcJdbcDTO jdbcDTO) {
		this.jdbcDTO = jdbcDTO;
	}
	
	public EcJdbcBO buildJdbcData(ProceedingJoinPoint joinPoint) {
		super.buildBaseAspectData(joinPoint);
		Object entityObj = super.getTParam(1);
		
		this.jdbcDTO.setEntityFields(EcReflectionUtils.getDeclaredFieldsIncSup(targetClass));
		return this;
		
	}
	
	public Object procced() throws Throwable {
		Object [] newArgs = buildNewArgs();
		if (EcArrayUtils.isNotEmpty(newArgs)) {
			return super.joinPoint.proceed(newArgs);
		}
		return super.joinPoint.proceed();
	}
	
	public EcJdbcDTO getJdbcDTO() {
		return jdbcDTO;
	}
	private Object [] buildNewArgs() {
		Object [] newArgs = null;
		//  保存类型--执行保存
		newArgs = doSave();
		// 更新类型--执行更新
		newArgs = doUpdate();
		return newArgs;
	}
	
	private Object [] doSave() {
		for (Field field : jdbcDTO.getEntityFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(CreatedBy.class)) {
				
			} else if (field.isAnnotationPresent(CreatedDate.class)) {
				
			}
		}
		return null;
	}
	
	private Object [] doUpdate() {
		for (Field field : jdbcDTO.getEntityFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(LastModifiedBy.class)) {
				
			} else if (field.isAnnotationPresent(LastModifiedDate.class)) {
				
			}
		}
		return null;
	}
}
