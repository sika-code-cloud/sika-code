package com.easy.cloud.user.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.easy.cloud.base.constant.UserServiceName;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.query.UserQuery;
import com.easy.cloud.user.client.constant.UserServiceReqMapping;

/**
 * 
 * <p>
 * 用户服务客户端
 * </p>
 *
 * <pre>
 * 主要是对用户原子服务相关接口的调用
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月5日 下午7:25:10
 */
@FeignClient(UserServiceName.SERVICE_NAME_ATOM_USER)
public interface UserClient {
	
	@RequestMapping(UserServiceReqMapping.REGISTER)
    DqBaseServiceResult register(@RequestBody UserDTO userDTO);
	
	@RequestMapping(UserServiceReqMapping.LOGIN_BY_USERNAME_AND_PASSWORD)
	DqBaseServiceResult loginByUserNameAndPassword(@RequestBody UserQuery userQuery);
	
	@RequestMapping(UserServiceReqMapping.LOGIN_BY_EMAIL_AND_PASSWORD)
	DqBaseServiceResult loginByEmailAndPassword(@RequestBody UserQuery userQuery);
}
