package com.easy.cloud.core.jdbc.primarykey.snowflake;

import java.io.Serializable;

import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;
import com.easy.cloud.core.jdbc.primarykey.EcBasePrimaryKeyGenerator;
import com.easy.cloud.core.jdbc.primarykey.config.EcPrimaryKeyConfig;

/**
 * 
 * <p>
 * 雪花主键生成器
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * @创建时间 2018年5月8日 下午8:04:56
 */

public class EcPrimaryKeySnowflakeGenerator implements EcBasePrimaryKeyGenerator {

	@Override
	public Serializable generate(Object entityObj) {
		if (entityObj != null && entityObj instanceof EcBaseEntity) {
			EcBaseEntity baseEntity = (EcBaseEntity) entityObj;
			if (baseEntity.getId() != null) {
				return baseEntity.getId();
			}
		}
		Long workerId = EcPrimaryKeyConfig.getWorkerId();
		Long datacenterId = EcPrimaryKeyConfig.getDatacenterId();
		return EcSnowflakeIdAlgorithm.getSingleInstance(workerId, datacenterId).nextId();
	}

}
