package com.easy.cloud.core.cache.redis.proxy.demo;

import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.cache.redis.proxy.EcBaseRedisProxy;
import com.easy.cloud.core.cache.redis.utils.EcRedisUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
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
		String keyBody = redisBO.getTParam(1).toString();
		if (EcStringUtils.isEmpty(keyBody)) {
			keyBody = "123";
		}
		key = EcRedisUtils.generateRedisKey("user", keyBody, "ceshi");
	}

	@Override
	protected Object query() {
		Object obj = EcRedisTemplateHandler.get(key, Object.class);
		System.out.println("获取的数据为：" + EcJSONUtils.toJSONString(obj));
		if (obj != null) {
			return obj;
		}
		obj = super.query();
		EcRedisTemplateHandler.set(key, obj);
		return obj;
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
		Object obj = redisBO.getTReturnValue(Object.class);
		EcRedisTemplateHandler.set(key, obj);
		System.out.println("Baocun luo ");
		return obj;
	}

	@Override
	protected Object delete() {
		EcRedisTemplateHandler.delete(key);
		return super.delete();
	}

}
