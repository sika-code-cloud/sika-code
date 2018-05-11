package com.easy.cloud.core.jdbc.primarykey.snowflake;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.common.properties.utils.EcPropUtils;
import com.easy.cloud.core.jdbc.base.entity.EcBaseEntity;
import com.easy.cloud.core.jdbc.primarykey.EcBasePrimaryKeyGenerator;

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
	private static volatile Long workerId;
	private static volatile Long datacenterId;
	private static final Logger LOGGER = LoggerFactory.getLogger(EcPrimaryKeySnowflakeGenerator.class);
	static {
		initData();
	}
	
	private static void initData() {
		try {
			workerId = EcPropUtils.getLong("config/snowflake", "snowflakeIdWorker.workerId");
			datacenterId = EcPropUtils.getLong("config/snowflake", "snowflakeIdWorker.datacenterId");
		} catch (Exception e) {
			LOGGER.warn(e.getMessage(), e);
		}
		if (workerId == null) {
			workerId = 0L;
		}
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
		return EcSnowflakeIdAlgorithm.getSingleInstance(workerId, datacenterId).nextId();
	}

}
