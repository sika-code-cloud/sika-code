package com.easy.cloud.user.base.service.imp;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.basic.service.DqBaseService;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;
import com.easy.cloud.user.base.constant.UserErrorCode;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.entity.UserEntity;
import com.easy.cloud.user.base.pojo.query.UserQuery;
import com.easy.cloud.user.base.repository.inf.UserRepository;
import com.easy.cloud.user.base.service.inf.UserService;

@Service(value="userService")
public class UserServiceImpl extends DqBaseService implements UserService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
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

	@Override
	@Transactional
	public DqBaseServiceResult register(UserDTO userDTO) {
		DqBaseServiceResult dqBaseServiceResult = DqBaseServiceResult.newInstanceOfSuccess();
		if(DqBaseUtils.isNull(userDTO)){
			throw DqBaseBusinessException.newInstance(UserErrorCode.USER_CANT_NULL);
		}
		if(DqStringUtils.isEmpty(userDTO.getUserName())){
			throw DqBaseBusinessException.newInstance(UserErrorCode.USER_NAME_CANT_EMPTY);
		}
		if(DqStringUtils.isEmpty(userDTO.getPassword())){
			throw DqBaseBusinessException.newInstance(UserErrorCode.USER_PASSWOR_CANT_EMPTY);
		}
		if(DqStringUtils.isEmpty(userDTO.getEmail())){
			throw DqBaseBusinessException.newInstance(UserErrorCode.USER_EMAIL_CANT_EMPTY);
		}
		UserEntity userEntity = userRepository.saveUserInfo(DqJSONUtils.parseObject(userDTO, UserEntity.class));
		return dqBaseServiceResult.buildResult(userEntity);
	}

	@Override
	public DqBaseServiceResult findByEmail(UserQuery userQuery) {
		UserQuery userQueryOfFilter = new UserQuery();
		userQueryOfFilter.setEmail(userQuery.getEmail());
		UserEntity userEntity = userRepository.findUserByQuery(userQueryOfFilter);
		return DqBaseServiceResult.newInstanceOfSucResult(userEntity);
	}

	@Override
	public DqBaseServiceResult loginByUserNameAndPassword(UserQuery userQuery) {
		UserQuery userQueryOfFilter = new UserQuery();
		userQueryOfFilter.setUserName(userQuery.getUserName());
		userQueryOfFilter.setPassword(userQuery.getPassword());
		UserEntity userEntity = userRepository.findUserByQuery(userQueryOfFilter);
		return DqBaseServiceResult.newInstanceOfSucResult(userEntity);
		
	}

	@Override
	public DqBaseServiceResult loginByEmailAndPassword(UserQuery userQuery) {
		UserQuery userQueryOfFilter = new UserQuery();
		userQueryOfFilter.setEmail(userQuery.getEmail());
		userQueryOfFilter.setPassword(userQuery.getPassword());
		UserEntity userEntity = userRepository.findUserByQuery(userQueryOfFilter);
		return DqBaseServiceResult.newInstanceOfSucResult(userEntity);
	}
	
	
}
