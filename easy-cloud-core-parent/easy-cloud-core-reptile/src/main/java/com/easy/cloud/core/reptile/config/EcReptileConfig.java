package com.easy.cloud.core.reptile.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.easy.cloud.core.reptile.config.properties.EcReptileEngineProperties;
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
		GeccoEngine ge = GeccoEngine.create();
		ge.classpath(reptileEngineProperties.getClasspath());
		ge.interval(reptileEngineProperties.getInterval());
		ge.loop(reptileEngineProperties.getLoop());
		ge.thread(reptileEngineProperties.getThread());
		ge.debug(reptileEngineProperties.getDebug());
		ge.mobile(reptileEngineProperties.getMobile());
		ge.retry(reptileEngineProperties.getRetry());
		ge.engineStart();
		return ge;
	}
}
