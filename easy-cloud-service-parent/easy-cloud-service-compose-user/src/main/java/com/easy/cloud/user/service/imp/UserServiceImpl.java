package com.easy.cloud.user.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeEnum;
import com.dq.easy.cloud.module.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.module.basic.service.DqBaseService;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;
import com.easy.cloud.user.base.constant.UserErrorCodeEnum;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.client.UserClient;
import com.easy.cloud.user.constant.LoginMode;
import com.easy.cloud.user.constant.UserComposeErrorCodeEnum;
import com.easy.cloud.user.pojo.query.UserComposeQuery;
import com.easy.cloud.user.service.inf.UserService;

/**
 * 
 * <p>
 * 用户服务实现类
 * </p>
 *
 * <pre>
 * 详细描述
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月5日 下午7:40:00
 */
@Service

public class UserServiceImpl extends DqBaseService implements UserService{
	@Autowired
	private UserClient userClient;
	
	@Override
	public DqBaseServiceResult register(UserDTO userDTO) {
		return userClient.register(userDTO);
	}
	
	@Override
	public DqBaseServiceResult login(UserComposeQuery userComposeQuery) {
		if(DqBaseUtils.isNull(userComposeQuery)){
			throw DqBaseBusinessException.newInstance(DqBaseErrorCodeEnum.QUERY_OBJ_CANT_NULL);
		}
		if(DqStringUtils.isEmpty(userComposeQuery.getPassword())){
			throw DqBaseBusinessException.newInstance(UserErrorCodeEnum.USER_PASSWOR_CANT_EMPTY);
		}
		Integer loginMode = userComposeQuery.getLoginMode();
		if(LoginMode.isNotAvailableValue(LoginMode.class, loginMode)){
			throw DqBaseBusinessException.newInstance(UserComposeErrorCodeEnum.LOGIN_MODE_WRONG);
		}
		if(LoginMode.isLoginByEmailAndPassword(loginMode)){
			if(DqStringUtils.isEmpty(userComposeQuery.getEmail())){
				throw DqBaseBusinessException.newInstance(UserErrorCodeEnum.USER_EMAIL_CANT_EMPTY);	
			}
		}else if(LoginMode.isLoginByUsernameAndPassword(loginMode)){
			if(DqStringUtils.isEmpty(userComposeQuery.getUserName())){
				throw DqBaseBusinessException.newInstance(UserErrorCodeEnum.USER_NAME_CANT_EMPTY);	
			}
		}
		DqBaseServiceResult dqBaseServiceResult = DqBaseServiceResult.newInstanceOfSuccess();
		if(LoginMode.isLoginByEmailAndPassword(loginMode)){
			dqBaseServiceResult = userClient.loginByEmailAndPassword(userComposeQuery);
		}else if(LoginMode.isLoginByUsernameAndPassword(loginMode)){
			dqBaseServiceResult = userClient.loginByUserNameAndPassword(userComposeQuery);
		}
		return dqBaseServiceResult;
	}

	
	@Override
	public DqBaseServiceResult login(UserComposeQuery userComposeQuery, String userName) {
		return login(userComposeQuery);
	}

}
