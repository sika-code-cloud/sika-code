package com.easy.cloud.core.cache.redis.test;

import org.springframework.beans.factory.annotation.Autowired;

import com.easy.cloud.core.cache.redis.test.dao.TestRedisDAO;

public class TestBaseRedisService {
	@Autowired
	protected TestRedisDAO testRedisDAO;
}
