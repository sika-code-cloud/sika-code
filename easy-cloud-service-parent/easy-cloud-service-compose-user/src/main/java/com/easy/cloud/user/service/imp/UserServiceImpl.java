package com.easy.cloud.user.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
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

public class UserServiceImpl extends EcBaseService implements UserService{
	@Autowired
	private UserClient userClient;
	
	@Override
	public EcBaseServiceResult register(UserDTO userDTO) {
		return userClient.register(userDTO);
	}
	
	@Override
	public EcBaseServiceResult login(UserComposeQuery userComposeQuery) {
		if(EcBaseUtils.isNull(userComposeQuery)){
			throw EcBaseBusinessException.newInstance(EcBaseErrorCodeEnum.QUERY_OBJ_CANT_NULL);
		}
		if(EcStringUtils.isEmpty(userComposeQuery.getPassword())){
			throw EcBaseBusinessException.newInstance(UserErrorCodeEnum.USER_PASSWOR_CANT_EMPTY);
		}
		Integer loginMode = userComposeQuery.getLoginMode();
		if(LoginMode.isNotAvailableValue(LoginMode.class, loginMode)){
			throw EcBaseBusinessException.newInstance(UserComposeErrorCodeEnum.LOGIN_MODE_WRONG);
		}
		if(LoginMode.isLoginByEmailAndPassword(loginMode)){
			if(EcStringUtils.isEmpty(userComposeQuery.getEmail())){
				throw EcBaseBusinessException.newInstance(UserErrorCodeEnum.USER_EMAIL_CANT_EMPTY);	
			}
		}else if(LoginMode.isLoginByUsernameAndPassword(loginMode)){
			if(EcStringUtils.isEmpty(userComposeQuery.getUserName())){
				throw EcBaseBusinessException.newInstance(UserErrorCodeEnum.USER_NAME_CANT_EMPTY);	
			}
		}
		EcBaseServiceResult dqBaseServiceResult = EcBaseServiceResult.newInstanceOfSuccess();
		if(LoginMode.isLoginByEmailAndPassword(loginMode)){
			dqBaseServiceResult = userClient.loginByEmailAndPassword(userComposeQuery);
		}else if(LoginMode.isLoginByUsernameAndPassword(loginMode)){
			dqBaseServiceResult = userClient.loginByUserNameAndPassword(userComposeQuery);
		}
		return dqBaseServiceResult;
	}

	
	@Override
	public EcBaseServiceResult login(UserComposeQuery userComposeQuery, String userName) {
		return login(userComposeQuery);
	}

}
