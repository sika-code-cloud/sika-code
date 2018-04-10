package com.easy.cloud.user.service.inf;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.pojo.query.UserComposeQuery;

/**
 * 
 * <p>
 * 用户服务接口类
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月5日 下午7:38:09
 */
public interface UserService {
	/**
	 * <p>
	 * 用户注册接口
	 * </p>
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     userName : zhangsan : 用户名 : 是
	 *     password : 123456 : 密码 : 是
	 *     email : dq@163.com : 邮箱 : 是
	 * </pre>
	 * @param userDTO : UserDTO : 用户数据传输对象
	 * @return EcBaseServiceResult
	 * @author daiqi
	 * 创建时间    2018年2月5日 下午7:39:10
	 */
	EcBaseServiceResult register(UserDTO userDTO);
	
	/**
	 * 
	 * <p>
	 * 用户登录
	 * </p>
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     userName : zhangsan : 用户名 : 是
	 *     password : 123456 : 密码 : 是
	 * </pre>
	 * @param userComposeQuery
	 * @return EcBaseServiceResult
	 * @author daiqi
	 * 创建时间    2018年2月5日 下午7:49:51
	 */
	EcBaseServiceResult login(UserComposeQuery userComposeQuery);
	
	EcBaseServiceResult login(UserComposeQuery userComposeQuery, String userName);
}
