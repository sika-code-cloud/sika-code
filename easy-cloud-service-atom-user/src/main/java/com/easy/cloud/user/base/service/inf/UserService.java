 package com.easy.cloud.user.base.service.inf;

import com.dq.easy.cloud.model.basic.dto.DqBaseServiceResult;
import com.easy.cloud.user.base.dto.UserDTO;
import com.easy.cloud.user.base.entity.UserEntity;
import com.easy.cloud.user.base.query.UserQuery;

/**
 * 用户服务接口
 * @author daiqi
 * @date 2018年1月8日 下午5:31:42
 */
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
	
	UserDTO findUserByEmailAndPassword(UserQuery userQuery);
	
	UserDTO findUserByPhoneNumberAndPassword(UserQuery userQuery);
	
	UserEntity saveUserInfo(UserEntity userEntity);
	
	/**
	 * <p>
	 * 保存用户信息
	 * </p>
	 * @param userDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:56:31
	 */
	DqBaseServiceResult saveUser(UserDTO userDTO);
}
