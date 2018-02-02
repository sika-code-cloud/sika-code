package com.easy.cloud.user.base.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dq.easy.cloud.model.basic.controller.DqBaseController;
import com.dq.easy.cloud.model.basic.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.common.response.vo.DqResponseVO;
import com.easy.cloud.user.base.dto.UserDTO;
import com.easy.cloud.user.base.entity.UserEntity;
import com.easy.cloud.user.base.query.UserQuery;
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
	
	@RequestMapping("saveUserInfo")
	public DqBaseServiceResult saveUserInfo(@RequestBody UserDTO userDTO){
		System.out.println("rewrew");
		return userService.saveUser(userDTO);
	}
	
	@RequestMapping("findUserByPhoneNumberAndPassword")
	public DqBaseServiceResult findUserByPhoneNumberAndPassword(@RequestBody UserQuery userQuery){
		return DqBaseServiceResult.newInstanceOfSucResult(userService.findUserByPhoneNumberAndPassword(userQuery));
	}
	
	@RequestMapping("findUserByEmailAndPassword")
	public DqBaseServiceResult findUserByEmailAndPassword(@RequestBody UserQuery userQuery){
		return DqBaseServiceResult.newInstanceOfSucResult(userService.findUserByEmailAndPassword(userQuery));
	}
}
