package com.dq.easy.user.logic.impl;

import org.springframework.stereotype.Component;
import com.dq.easy.user.srvice.UserService;
import com.dq.easy.cloud.module.basic.logic.DqBaseLogic;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述：用户业务逻辑实现类
 * @author THINK
 * @date 2018-03-28 10:07:37
 */
@Component(value = "userService")
public class UserLogicImpl extends DqBaseLogic{
	
	/** 用户业务逻辑实现类服务接口 */
	@Autowired
	private UserService userService;
		
	
}
