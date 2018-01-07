package com.easy.cloud.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.user.entity.UserEntity;
import com.easy.cloud.user.repository.inf.UserRepository;
import com.easy.cloud.user.service.inf.UserService;

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
