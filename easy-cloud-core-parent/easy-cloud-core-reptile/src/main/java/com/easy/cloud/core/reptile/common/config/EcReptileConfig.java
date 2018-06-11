package com.easy.cloud.core.reptile.common.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.easy.cloud.core.common.map.utils.EcMapUtils;
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
	/** reptileEngineNo和爬虫引擎的映射关系 */
	private static Map<Integer, GeccoEngine> reptileEngineNoMappingGeccoEngine = EcMapUtils.newConcurrentHashMap();
	
	/**
	 * 
	 * <p>
	 * 将beanClassName和GeccoEngine关系放入map中
	 * </p>
	 *
	 * @param reptileEngineNo
	 * @param geccoEngine
	 * @author daiqi
	 * @创建时间 2018年6月11日 上午10:24:09
	 */
	public static void putGeccoEngineToMapping(Integer reptileEngineNo, GeccoEngine geccoEngine) {
		reptileEngineNoMappingGeccoEngine.put(reptileEngineNo, geccoEngine);
	}
	/**
	 * 
	 * <p>
	 * 从mapping将beanClassName对应的GeccoEngine移除
	 * </p>
	 *
	 * @param reptileEngineNo
	 * @author daiqi
	 * @创建时间 2018年6月11日 上午10:24:09
	 */
	public static GeccoEngine removeGeccoEngineFromMapping(Integer reptileEngineNo) {
		return reptileEngineNoMappingGeccoEngine.remove(reptileEngineNo);
	}
	
	/**
	 * 
	 * <p>
	 * 根据beanClassName获取爬虫引擎
	 * </p>
	 *
	 * @param reptileEngineNo
	 * @return
	 * @author daiqi
	 * @创建时间 2018年6月11日 上午10:21:44
	 */
	public static GeccoEngine getGeccoEngineByReptileEngineNo(Integer reptileEngineNo) {
		return reptileEngineNoMappingGeccoEngine.get(reptileEngineNo);
	}

//	@Bean
	protected GeccoEngine geccoEngine() {
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
