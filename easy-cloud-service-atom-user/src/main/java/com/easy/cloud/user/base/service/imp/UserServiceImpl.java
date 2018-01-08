package com.easy.cloud.user.base.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.easy.cloud.user.base.dto.UserDTO;
import com.easy.cloud.user.base.entity.UserEntity;
import com.easy.cloud.user.base.query.UserQuery;
import com.easy.cloud.user.base.repository.inf.UserRepository;
import com.easy.cloud.user.base.service.inf.UserService;

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
		if(DqBaseUtils.isNull(userEntity)){
			return null;
		}
		return userRepository.saveUserInfo(userEntity);
	}

	@Override
	public UserDTO findUserByEmailAndPassword(UserQuery userQuery) {
		return userRepository.findUserByEmailAndPassword(userQuery);
	}

	@Override
	public UserDTO findUserByPhoneNumberAndPassword(UserQuery userQuery) {
		return userRepository.findUserByPhoneNumberAndPassword(userQuery);
	}
	
	
}
