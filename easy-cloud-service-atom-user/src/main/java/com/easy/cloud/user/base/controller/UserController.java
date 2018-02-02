package com.easy.cloud.user.base.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.response.vo.DqResponseVO;
import com.easy.cloud.user.base.entity.UserEntity;
import com.easy.cloud.user.base.query.UserQuery;
import com.easy.cloud.user.base.service.inf.UserService;

@RestController
@RequestMapping("user/")
public class UserController{
	@Autowired
	private UserService userService;
	@Value(value="${from}")
	private String from;
	
	@RequestMapping("getFrom")
	public String getFrom(){
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("from", from);
		return DqJSONUtils.parseObject(retMap, String.class);
	}
	@RequestMapping("getUserInfoById")
	public String getUserInfoById(@RequestParam Long id){
		UserEntity userEntity = userService.findUserById(id);
		return DqResponseVO.toString(userEntity);
	}
	
	@RequestMapping("saveUserInfo")
	public String saveUserInfo(@RequestBody UserEntity userEntity){
		return DqResponseVO.toString(userService.saveUserInfo(userEntity));
	}
	
	@RequestMapping("findUserByPhoneNumberAndPassword")
	public String findUserByPhoneNumberAndPassword(@RequestBody UserQuery userQuery){
		return DqResponseVO.toString(userService.findUserByPhoneNumberAndPassword(userQuery));
	}
	
	@RequestMapping("findUserByEmailAndPassword")
	public String findUserByEmailAndPassword(@RequestBody UserQuery userQuery){
		return DqResponseVO.toString(userService.findUserByEmailAndPassword(userQuery));
	}
}
