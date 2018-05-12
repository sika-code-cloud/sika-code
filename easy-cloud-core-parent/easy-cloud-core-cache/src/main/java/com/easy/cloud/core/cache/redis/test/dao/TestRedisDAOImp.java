package com.easy.cloud.core.cache.redis.test.dao;

import org.springframework.stereotype.Repository;

import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;

@Repository
public class TestRedisDAOImp implements TestRedisDAO{

	@Override
	public EcLogDTO queryRequestPath(String id) {
		EcLogDTO logDTO = new EcLogDTO();
		logDTO.setRequestPath("dao下的对象");
		return logDTO;
	}
	
}
