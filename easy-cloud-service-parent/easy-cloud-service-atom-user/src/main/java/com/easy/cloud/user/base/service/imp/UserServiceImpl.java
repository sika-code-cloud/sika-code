package com.easy.cloud.user.base.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.user.base.constant.UserErrorCodeEnum;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.entity.UserEntity;
import com.easy.cloud.user.base.pojo.query.UserQuery;
import com.easy.cloud.user.base.repository.inf.UserRepository;
import com.easy.cloud.user.base.service.inf.UserService;

@Service(value="userService")
public class UserServiceImpl extends EcBaseService implements UserService {
	@Resource
	private UserRepository userRepository;
	
	@Override
	public UserEntity findUserById(Long id) {
		return userRepository.findUserById(id);
	}
	
	@Override
	@Transactional
	public UserEntity saveUserInfo(UserEntity userEntity) {
		if(EcBaseUtils.isNull(userEntity)){
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
	public EcBaseServiceResult saveUser(UserDTO userDTO) {
		EcBaseServiceResult dqBaseServiceResult = EcBaseServiceResult.newInstanceOfSuccess();
		if(EcBaseUtils.isNull(userDTO)){
			dqBaseServiceResult.buildErrorCode(UserErrorCodeEnum.USER_CANT_NULL);
			return dqBaseServiceResult;
		}
		if(EcStringUtils.isEmpty(userDTO.getUserName())){
			dqBaseServiceResult.buildErrorCode(UserErrorCodeEnum.USER_NAME_CANT_EMPTY);
			return dqBaseServiceResult;
		}
		if(EcStringUtils.isEmpty(userDTO.getPassword())){
			dqBaseServiceResult.buildErrorCode(UserErrorCodeEnum.USER_PASSWOR_CANT_EMPTY);
			return dqBaseServiceResult;
		}
		UserEntity userEntity = userRepository.saveUserInfo(EcJSONUtils.parseObject(userDTO, UserEntity.class));
		return dqBaseServiceResult.buildResult(userEntity);
	}

	@Override
	@Transactional
	public EcBaseServiceResult register(UserDTO userDTO) {
		EcBaseServiceResult dqBaseServiceResult = EcBaseServiceResult.newInstanceOfSuccess();
		if(EcBaseUtils.isNull(userDTO)){
			throw EcBaseBusinessException.newInstance(UserErrorCodeEnum.USER_CANT_NULL);
		}
		if(EcStringUtils.isEmpty(userDTO.getUserName())){
			throw EcBaseBusinessException.newInstance(UserErrorCodeEnum.USER_NAME_CANT_EMPTY);
		}
		if(EcStringUtils.isEmpty(userDTO.getPassword())){
			throw EcBaseBusinessException.newInstance(UserErrorCodeEnum.USER_PASSWOR_CANT_EMPTY);
		}
		if(EcStringUtils.isEmpty(userDTO.getEmail())){
			throw EcBaseBusinessException.newInstance(UserErrorCodeEnum.USER_EMAIL_CANT_EMPTY);
		}
		UserEntity userEntity = userRepository.saveUserInfo(EcJSONUtils.parseObject(userDTO, UserEntity.class));
		return dqBaseServiceResult.buildResult(userEntity);
	}

	@Override
	public EcBaseServiceResult findByEmail(UserQuery userQuery) {
		UserQuery userQueryOfFilter = new UserQuery();
		userQueryOfFilter.setEmail(userQuery.getEmail());
		UserEntity userEntity = userRepository.findUserByQuery(userQueryOfFilter);
		return EcBaseServiceResult.newInstanceOfSucResult(userEntity);
	}

	@Override
	public EcBaseServiceResult loginByUserNameAndPassword(UserQuery userQuery) {
		UserQuery userQueryOfFilter = new UserQuery();
		userQueryOfFilter.setUserName(userQuery.getUserName());
		userQueryOfFilter.setPassword(userQuery.getPassword());
		UserEntity userEntity = userRepository.findUserByQuery(userQueryOfFilter);
		return EcBaseServiceResult.newInstanceOfSucResult(userEntity);
		
	}

	@Override
	public EcBaseServiceResult loginByEmailAndPassword(UserQuery userQuery) {
		UserQuery userQueryOfFilter = new UserQuery();
		userQueryOfFilter.setEmail(userQuery.getEmail());
		userQueryOfFilter.setPassword(userQuery.getPassword());
		UserEntity userEntity = userRepository.findUserByQuery(userQueryOfFilter);
		return EcBaseServiceResult.newInstanceOfSucResult(userEntity);
	}
	
	
}
