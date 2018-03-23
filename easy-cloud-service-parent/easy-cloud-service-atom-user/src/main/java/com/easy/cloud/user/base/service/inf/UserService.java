 package com.easy.cloud.user.base.service.inf;

import com.dq.easy.cloud.module.basic.pojo.dto.DqBaseServiceResult;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.entity.UserEntity;
import com.easy.cloud.user.base.pojo.query.UserQuery;

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
	 * 	保存用户信息
	 * </p>
	 * @param userDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月2日 下午4:56:31
	 */
	DqBaseServiceResult saveUser(UserDTO userDTO);
	
	/**
	 * <p>
	 * 用户注册
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     userName : zhangsan : 用户名 : 是
	 *     password : 123456 : 密码 : 是
	 *     email : dq@163.com : 邮箱 : 是
	 * </pre>
	 *
	 * @param userDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月5日 下午8:43:06
	 */
	DqBaseServiceResult register(UserDTO userDTO);
	
	/**
	 * <p>
	 * 根据邮箱获取用户信息
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     email : dq@163.com : 邮箱 : 是
	 * </pre>
	 *
	 * @param userQuery
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月5日 下午8:49:30
	 */
	DqBaseServiceResult findByEmail(UserQuery userQuery);
	
	/**
	 * <p>
	 * 用户注册
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     userName : zhangsan : 用户名 : 是
	 *     password : 123456 : 密码 : 是
	 * </pre>
	 *
	 * @param userDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月5日 下午8:43:06
	 */
	DqBaseServiceResult loginByUserNameAndPassword(UserQuery userQuery);
	
	/**
	 * <p>
	 * 用户注册
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     password : 123456 : 密码 : 是
	 *     email : dq@163.com : 邮箱 : 是
	 * </pre>
	 *
	 * @param userDTO
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月5日 下午8:43:06
	 */
	DqBaseServiceResult loginByEmailAndPassword(UserQuery userQuery);
	
}
