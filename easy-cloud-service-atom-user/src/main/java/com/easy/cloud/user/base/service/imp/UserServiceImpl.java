package com.easy.cloud.user.base.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.easy.cloud.model.basic.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.easy.cloud.user.base.constant.UserErrorCode;
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

	@Override
	@Transactional
	public DqBaseServiceResult saveUser(UserDTO userDTO) {
		DqBaseServiceResult dqBaseServiceResult = DqBaseServiceResult.newInstanceOfSuccess();
		if(DqBaseUtils.isNull(userDTO)){
			dqBaseServiceResult.buildErrorCode(UserErrorCode.USER_CANT_NULL);
			return dqBaseServiceResult;
		}
		if(DqStringUtils.isEmpty(userDTO.getUserName())){
			dqBaseServiceResult.buildErrorCode(UserErrorCode.USER_NAME_CANT_EMPTY);
			return dqBaseServiceResult;
		}
		if(DqStringUtils.isEmpty(userDTO.getPassword())){
			dqBaseServiceResult.buildErrorCode(UserErrorCode.USER_PASSWOR_CANT_EMPTY);
			return dqBaseServiceResult;
		}
		UserEntity userEntity = userRepository.saveUserInfo(DqJSONUtils.parseObject(userDTO, UserEntity.class));
		return dqBaseServiceResult.buildResult(userEntity);
	}
	
}
