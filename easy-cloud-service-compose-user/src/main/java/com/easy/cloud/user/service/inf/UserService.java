package com.easy.cloud.user.service.inf;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dq.easy.cloud.model.basic.dto.DqBaseServiceResult;
import com.easy.cloud.user.base.dto.UserDTO;

@FeignClient("EASY-CLOUD-SERVICE-ATOM-USER")
public interface UserService {
	@RequestMapping("user/saveUserInfo")
    DqBaseServiceResult saveUser(@RequestBody UserDTO userDTO);
}
