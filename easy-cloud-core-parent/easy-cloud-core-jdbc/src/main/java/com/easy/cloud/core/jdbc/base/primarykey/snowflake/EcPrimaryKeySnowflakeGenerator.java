package com.easy.cloud.core.jdbc.base.primarykey.snowflake;

import java.io.Serializable;

import com.easy.cloud.core.common.properties.utils.EcPropUtils;
import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;
import com.easy.cloud.core.jdbc.base.primarykey.EcBasePrimaryKeyGenerator;

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
	private static Long workerId;
	private static Long datacenterId;
	static {
		workerId = EcPropUtils.getLong("config/snowflake", "snowflakeIdWorker.workerId");
		if (workerId == null) {
			workerId = 0L;
		}
		datacenterId = EcPropUtils.getLong("config/snowflake", "snowflakeIdWorker.datacenterId");
		if (datacenterId == null) {
			datacenterId = 0L;
		}
	}

	@Override
	public Serializable generate(Object entityObj) {
		if (entityObj != null && entityObj instanceof EcBaseEntity) {
			EcBaseEntity baseEntity = (EcBaseEntity) entityObj;
			if (baseEntity.getId() != null) {
				return baseEntity.getId();
			}
		}
		return EcSnowflakeIdAlgorithm.getInstance(workerId, datacenterId).nextId();
	}

}
