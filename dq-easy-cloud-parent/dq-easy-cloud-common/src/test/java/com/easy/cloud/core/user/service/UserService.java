package com.dq.easy.cloud.model.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.easy.cloud.model.user.entity.UserEntity;
import com.dq.easy.cloud.model.user.repository.UserRepository;
import com.dq.easy.cloud.module.cache.redis.handler.DqRedisTemplateHandler;
import com.dq.easy.cloud.module.jdbc.handler.DqJdbcTemplateHandler;

//@Service
public class UserService {
	@Resource
	private UserRepository userRepository;
	@Transactional
	public UserEntity saveUserEntity(UserEntity userEntity){
		return DqJdbcTemplateHandler.saveOrUpdate(userEntity);
//		return userRepository.saveOrUpdate(userEntity);
	}
	
	public void set(String key, Object obj){
		DqRedisTemplateHandler.set(key, obj);
	}
}
