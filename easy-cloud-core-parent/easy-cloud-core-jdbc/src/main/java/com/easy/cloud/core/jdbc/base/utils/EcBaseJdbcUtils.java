package com.easy.cloud.core.jdbc.base.utils;

import javax.persistence.Table;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;

public class EcBaseJdbcUtils {
	/**
	 * 
	 * <p>
	 * 根据实体类的Class获取其对应的tableName
	 * </p>
	 *
	 * <pre></pre>
	 *
	 * @param clazz
	 * @return
	 *
	 * 		author daiqi 创建时间 2018年1月8日 下午7:47:40
	 */
	public static String getTableNameByEntityClass(final Class<?> entityClazz) {
		if (EcBaseUtils.isNull(entityClazz)) {
			throw new RuntimeException("entityClazz is null");
		}
		// 获取javax.persistence.Table注解
		Table tableAnnotation = entityClazz.getAnnotation(Table.class);
		if (EcBaseUtils.isNull(tableAnnotation)) {
			throw new RuntimeException("实体类没有javax.persistence.Table注解");
		}
		String tableName = tableAnnotation.name();
		if (EcStringUtils.isEmpty(tableName)) {
			throw new RuntimeException("javax.persistence.Table注解name属性为空");
		}
		return tableName;
	}
	
	/**
	 * 
	 * <p>
	 * 判断DqBaseEntity类是否为空
	 * </p>
	 *
	 * @param baseEntity
	 * @return <code>true</code> 若baseEntity为null 或者 baseEntity.id为null
	 * @author daiqi
	 * @date 2017年12月6日 下午1:51:09
	 */
	public static boolean isNullEntity(EcBaseEntity baseEntity) {
		if (EcBaseUtils.isNull(baseEntity) || EcBaseUtils.isNull(baseEntity.getId())) {
			return true;
		}
		return false;
	}
}
