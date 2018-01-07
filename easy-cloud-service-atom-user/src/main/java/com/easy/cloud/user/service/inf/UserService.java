 package com.easy.cloud.user.service.inf;

import com.easy.cloud.user.base.entity.UserEntity;


public interface UserService {
	/**
	 * 
	 * <p>根据id获取用户信息</p>
	 *
	 * <pre></pre>
	 *
	 * @param id
	 * @return
	 *
	 * author daiqi
	 * 创建时间  2018年1月3日 下午10:26:24
	 */
	UserEntity findUserById(Long id);
	
	UserEntity saveUserInfo(UserEntity userEntity);
}
