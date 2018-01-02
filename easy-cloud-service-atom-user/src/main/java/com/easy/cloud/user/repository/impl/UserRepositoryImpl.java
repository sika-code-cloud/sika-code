package com.easy.cloud.user.repository.impl;

import org.springframework.stereotype.Repository;

import com.dq.easy.cloud.model.basic.repository.DqBaseRepository;
import com.dq.easy.cloud.model.jdbc.handler.DqJdbcTemplateHandler;
import com.easy.cloud.user.repository.inf.UserRepository;
import com.easy.cloud.user.vo.UserVo;

@Repository(value="userRepository")
public class UserRepositoryImpl extends DqBaseRepository implements UserRepository{

	@Override
	public UserVo findUserById(Long id) {
		return DqJdbcTemplateHandler.findOne(UserVo.class, id);
	}

}
