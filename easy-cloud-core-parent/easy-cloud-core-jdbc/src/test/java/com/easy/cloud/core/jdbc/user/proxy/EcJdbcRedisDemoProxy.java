package com.easy.cloud.core.jdbc.user.proxy;

import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.cache.redis.proxy.EcBaseRedisProxy;
import com.easy.cloud.core.cache.redis.utils.EcRedisUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.jdbc.user.entity.UserEntity;

/**
 * 示例redis代理类
 * @author daiqi
 * @date 2018年4月26日 下午11:11:25
 */
public class EcJdbcRedisDemoProxy extends EcBaseRedisProxy{
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
		UserEntity obj = EcRedisTemplateHandler.get(key, UserEntity.class);
		System.out.println("获取的数据为：" + EcJSONUtils.toJSONString(obj));
		if (obj != null) {
			return obj;
		}
		Object retObj = super.query();
		EcRedisTemplateHandler.set(key, retObj);
		return retObj;
	}

	@Override
	protected Object update() {
		UserEntity userEntity = EcRedisTemplateHandler.get(key, UserEntity.class);
		if (userEntity != null) {
			EcRedisTemplateHandler.set(key, userEntity);
		} 
		return super.update();
	}

	@Override
	protected Object save() {
		Object retObj = super.save();
		UserEntity obj = redisBO.getTParam(UserEntity.class);
		key = EcRedisUtils.generateRedisKey("user", obj.getId(), "ceshi");
		EcRedisTemplateHandler.set(key, obj);
		System.out.println("Baocun luo ");
		return retObj;
	}

	@Override
	protected Object delete() {
		EcRedisTemplateHandler.delete(key);
		return super.delete();
	}

}
