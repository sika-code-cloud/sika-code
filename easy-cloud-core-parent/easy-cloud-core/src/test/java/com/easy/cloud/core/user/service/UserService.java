package com.easy.cloud.core.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.jdbc.handler.EcJdbcTemplateHandler;
import com.easy.cloud.core.user.entity.UserEntity;
import com.easy.cloud.core.user.repository.UserRepository;

//@Service
public class UserService {
	@Resource
	private UserRepository userRepository;
	@Transactional
	public UserEntity saveUserEntity(UserEntity userEntity){
		return EcJdbcTemplateHandler.saveOrUpdate(userEntity);
//		return userRepository.saveOrUpdate(userEntity);
	}
	
	public void set(String key, Object obj){
		EcRedisTemplateHandler.set(key, obj);
	}
}
