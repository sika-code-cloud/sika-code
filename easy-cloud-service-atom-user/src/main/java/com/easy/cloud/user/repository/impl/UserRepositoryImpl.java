package com.easy.cloud.user.repository.impl;

import org.springframework.stereotype.Repository;

import com.dq.easy.cloud.model.basic.repository.DqBaseRepository;
import com.dq.easy.cloud.model.jdbc.handler.DqJdbcTemplateHandler;
import com.easy.cloud.user.base.entity.UserEntity;
import com.easy.cloud.user.repository.inf.UserRepository;

@Repository(value="userRepository")
public class UserRepositoryImpl extends DqBaseRepository implements UserRepository{

	@Override
	public UserEntity findUserById(Long id) {
		return DqJdbcTemplateHandler.findOne(UserEntity.class, id);
	}

	@Override
	public UserEntity saveUserInfo(UserEntity userEntity) {
		return save(userEntity);
	}
	
	
}
