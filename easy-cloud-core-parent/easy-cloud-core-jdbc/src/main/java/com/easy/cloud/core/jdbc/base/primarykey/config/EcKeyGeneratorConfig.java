package com.easy.cloud.core.jdbc.base.primarykey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.easy.cloud.core.jdbc.base.primarykey.EcSnowflakeIdWorkerBO;

/**
 * 
 * <p>
 * 雪花算法配置类
 * </p>
 *
 *
 * @author daiqi
 * @创建时间 2018年5月4日 下午4:08:58
 */
@Configuration
@PropertySource("classpath:config/snowflakeid.properties")
public class EcKeyGeneratorConfig {
	/** 空间id */
	@Value("${snowflakeIdWorker.workerId}")
	private Long workerId;
	/** 数据中心id */
	@Value("${snowflakeIdWorker.datacenterId}")
	private Long datacenterId;
	@Bean
	public EcSnowflakeIdWorkerBO snowflakeIdWorker() {
		return new EcSnowflakeIdWorkerBO().buidWorkerIdAndDatacenterId(workerId, datacenterId);
	}
}
