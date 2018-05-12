package com.easy.cloud.core.jdbc.primarykey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * <p>
 * 主键配置类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年5月12日 下午5:11:55
 */
@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:config/snowflake.properties" })
public class EcPrimaryKeyConfig {
	private static volatile Long workerId;
	private static volatile Long datacenterId;
	
	@Value(value = "${snowflakeIdWorker.workerId}")
	public void setWorkerId(Long workerId) {
		EcPrimaryKeyConfig.workerId = workerId;
	}
	
	@Value(value = "${snowflakeIdWorker.datacenterId}")
	public void setDatacenterId(Long datacenterId) {
		EcPrimaryKeyConfig.datacenterId = datacenterId;
	}

	public static Long getWorkerId() {
		return workerId;
	}

	public static Long getDatacenterId() {
		return datacenterId;
	}
	
}
