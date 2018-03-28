package com.dq.easy.user.srvice.impl;

import com.dq.easy.user.dao.UserDAO;
import com.dq.easy.cloud.module.basic.service.DqBaseService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 描述：用户服务实现类
 * @author THINK
 * @date 2018-03-28 10:07:37
 */
@Service(value = "userDAO")
public class UserServiceImpl extends DqBaseService{
	
	/** 用户服务实现类数据处理接口 */
	@Autowired
	private UserDAO userDAO;
		
	
}
