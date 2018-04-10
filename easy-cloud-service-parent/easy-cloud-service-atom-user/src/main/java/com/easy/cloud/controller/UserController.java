package com.easy.cloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.entity.UserEntity;
import com.easy.cloud.user.base.pojo.query.UserQuery;
import com.easy.cloud.user.base.service.inf.UserService;

@RestController
@RequestMapping("user/")
public class UserController extends EcBaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping("getUserInfoById")
	public EcBaseServiceResult getUserInfoById(@RequestParam Long id){
		UserEntity userEntity = userService.findUserById(id);
		return EcBaseServiceResult.newInstanceOfSucResult(userEntity);
	}
	
	@RequestMapping("register")
	public EcBaseServiceResult register(@RequestBody UserDTO userDTO){
		return userService.register(userDTO);
	}
	
	@RequestMapping("findByEmail")
	public EcBaseServiceResult findUserByPhoneNumberAndPassword(@RequestBody UserQuery userQuery){
		return EcBaseServiceResult.newInstanceOfSucResult(userService.findByEmail(userQuery));
	}
	@RequestMapping("loginByEmailAndPassword")
	public EcBaseServiceResult loginByEmailAndPassword(@RequestBody UserQuery userQuery){
		return userService.loginByEmailAndPassword(userQuery);
	}
	
	@RequestMapping("loginByUserNameAndPassword")
	public EcBaseServiceResult loginByUserNameAndPassword(@RequestBody UserQuery userQuery){
		return userService.loginByUserNameAndPassword(userQuery);
	}
}
