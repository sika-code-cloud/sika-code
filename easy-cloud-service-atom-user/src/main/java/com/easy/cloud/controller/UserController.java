package com.easy.cloud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dq.easy.cloud.model.basic.controller.DqBaseController;
import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.easy.cloud.user.base.pojo.dto.UserDTO;
import com.easy.cloud.user.base.pojo.entity.UserEntity;
import com.easy.cloud.user.base.pojo.query.UserQuery;
import com.easy.cloud.user.base.service.inf.UserService;

@RestController
@RequestMapping("user/")
public class UserController extends DqBaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping("getUserInfoById")
	public DqBaseServiceResult getUserInfoById(@RequestParam Long id){
		UserEntity userEntity = userService.findUserById(id);
		return DqBaseServiceResult.newInstanceOfSucResult(userEntity);
	}
	
	@RequestMapping("register")
	public DqBaseServiceResult register(@RequestBody UserDTO userDTO){
		return userService.register(userDTO);
	}
	
	@RequestMapping("findByEmail")
	public DqBaseServiceResult findUserByPhoneNumberAndPassword(@RequestBody UserQuery userQuery){
		return DqBaseServiceResult.newInstanceOfSucResult(userService.findByEmail(userQuery));
	}
	@RequestMapping("loginByEmailAndPassword")
	public DqBaseServiceResult loginByEmailAndPassword(@RequestBody UserQuery userQuery){
		return userService.loginByEmailAndPassword(userQuery);
	}
	
	@RequestMapping("loginByUserNameAndPassword")
	public DqBaseServiceResult loginByUserNameAndPassword(@RequestBody UserQuery userQuery){
		return userService.loginByUserNameAndPassword(userQuery);
	}
}
