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
	
	/**
	 * 
	 * <p>
	 *  根据查询对象获取用户信息
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param userQuery : UserQuery : 用户查询对象
	 * @return UserEntity : 用户实体类
	 * @author daiqi
	 * 创建时间    2018年2月7日 下午4:10:38
	 */
	UserEntity findUserByQuery(UserQuery userQuery);
}
