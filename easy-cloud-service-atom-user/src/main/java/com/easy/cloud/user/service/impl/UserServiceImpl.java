package com.easy.cloud.user.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.easy.cloud.user.entity.UserEntity;
import com.easy.cloud.user.repository.inf.UserRepository;
import com.easy.cloud.user.service.inf.UserService;
import com.easy.cloud.user.vo.UserVo;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	@Resource
	private UserRepository userRepository;
	@Override
	public UserEntity findUserById(Long id) {
		return userRepository.findUserById(id);
	}
	@Override
	@Transactional
	public UserEntity saveUserInfo(UserEntity userEntity) {
		return userRepository.saveUserInfo(userEntity);
	}
	
	
}
