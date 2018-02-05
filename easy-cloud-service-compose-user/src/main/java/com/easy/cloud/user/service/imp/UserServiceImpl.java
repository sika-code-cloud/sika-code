package com.easy.cloud.user.service.imp;

import org.springframework.beans.factory.annotation.Autowired;

import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.query.UserQuery;
import com.easy.cloud.user.client.UserClient;
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
public class UserServiceImpl implements UserService{
	@Autowired
	private UserClient userClient;
	
	@Override
	public DqBaseServiceResult register(UserDTO userDTO) {
		return userClient.register(userDTO);
	}
	
	@Override
	public DqBaseServiceResult login(UserQuery userQuery) {
		return userClient.login(userQuery);
	}

}
