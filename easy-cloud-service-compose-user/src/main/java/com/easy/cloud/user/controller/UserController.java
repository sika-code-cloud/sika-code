package com.easy.cloud.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dq.easy.cloud.model.basic.controller.DqBaseController;
import com.dq.easy.cloud.model.basic.dto.DqBaseServiceResult;
import com.easy.cloud.user.base.dto.UserDTO;
import com.easy.cloud.user.service.inf.UserService;

@RestController
@RequestMapping("user")
public class UserController extends DqBaseController{
	@Autowired
	private UserService userService;
	@RequestMapping("saveUserInfo")
	public DqBaseServiceResult saveUser(@RequestBody UserDTO userDTO){
		return userService.saveUser(userDTO);
	}
}
