package com.easy.cloud.core.reptile.common.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.easy.cloud.core.reptile.common.config.properties.EcReptileEngineProperties;
import com.geccocrawler.gecco.GeccoEngine;

/**
 * 
 * <p>
 * 爬虫引擎配置类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年6月6日 下午3:04:58
 */
@Configuration
@PropertySource("classpath:config/reptile-engine.properties")
public class EcReptileConfig {

	@Autowired
	private EcReptileEngineProperties reptileEngineProperties;

	@Bean
	public GeccoEngine geccoEngine() {
		GeccoEngine ge = GeccoEngine.create(reptileEngineProperties.getClasspath());
		ge.interval(reptileEngineProperties.getInterval());
		ge.loop(true);
		ge.thread(reptileEngineProperties.getThread());
		ge.debug(reptileEngineProperties.getDebug());
		ge.mobile(reptileEngineProperties.getMobile());
		ge.retry(reptileEngineProperties.getRetry());
		// 使用run方法保证后续的loop设置为false有效
		ge.run();
		ge.loop(false);
		return ge;
	}
}
