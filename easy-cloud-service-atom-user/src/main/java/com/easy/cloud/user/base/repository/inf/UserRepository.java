package com.easy.cloud.user.base.repository.inf;

import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.entity.UserEntity;
import com.easy.cloud.user.base.pojo.query.UserQuery;

public interface UserRepository {
	UserEntity saveUserInfo(UserEntity userEntity);
	UserEntity findUserById(Long id);
	
	/**
	 * 
	 * <p>根据手机号和密码获取用户信息</p>
	 *
	 * <pre></pre>
	 *
	 * @param userQuery
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月8日 下午7:03:05
	 */
	UserDTO findUserByPhoneNumberAndPassword(UserQuery userQuery);
	
	/**
	 * 
	 * <p>根据邮箱和密码获取用户信息</p>
	 *
	 * <pre></pre>
	 *
	 * @param userQuery
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月8日 下午7:03:21
	 */
	UserDTO findUserByEmailAndPassword(UserQuery userQuery);
}
