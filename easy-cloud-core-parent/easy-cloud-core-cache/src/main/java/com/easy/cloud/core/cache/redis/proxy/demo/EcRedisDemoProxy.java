package com.easy.cloud.core.cache.redis.proxy.demo;

import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.cache.redis.proxy.EcBaseRedisProxy;
import com.easy.cloud.core.cache.redis.utils.EcRedisUtils;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.string.utils.EcStringUtils;

/**
 * 示例redis代理类
 * @author daiqi
 * @date 2018年4月26日 下午11:11:25
 */
public class EcRedisDemoProxy extends EcBaseRedisProxy{
	private String key;
	
	@Override
	protected void initOtherData() {
		super.initOtherData();
		String keyBody = redisBO.getTParam(1);
		if (EcStringUtils.isEmpty(keyBody)) {
			keyBody = "123";
		}
		key = EcRedisUtils.generateRedisKey("user", keyBody, "ceshi");
	}

	@Override
	protected Object query() {
		EcLogDTO logDTO = EcRedisTemplateHandler.get(key, EcLogDTO.class);
		if (logDTO != null) {
			return logDTO;
		}
		return super.query();
	}

	@Override
	protected Object update() {
		EcLogDTO logDTO = EcRedisTemplateHandler.get(key, EcLogDTO.class);
		if (logDTO != null) {
			logDTO.setRequestPath("更行的数据www.update.com");
			EcRedisTemplateHandler.set(key, logDTO);
		} 
		return super.update();
	}

	@Override
	protected Object save() {
		EcLogDTO logDTO = redisBO.getTReturnValue(EcLogDTO.class);
		EcRedisTemplateHandler.set(key, logDTO);
		return logDTO;
	}

	@Override
	protected Object delete() {
		EcRedisTemplateHandler.delete(key);
		return super.delete();
	}

}
