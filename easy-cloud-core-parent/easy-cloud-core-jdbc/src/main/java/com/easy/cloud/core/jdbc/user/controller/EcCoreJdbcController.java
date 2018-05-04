package com.easy.cloud.core.jdbc.user.controller;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.jdbc.user.entity.UserEntity;
import com.easy.cloud.core.jdbc.user.service.UserService;

@RestController
public class EcCoreJdbcController {
	ExecutorService executorService = Executors.newFixedThreadPool(100); 
	@Autowired
	UserService userService;

	@RequestMapping(value = "testListPage")
	public List<UserEntity> testListPage() {
		List<UserEntity> retList = userService.listPage(1, 10);
		System.out.println("-------------------" + retList.size());
		for (UserEntity userEntity : retList) {
			System.out.println("-------------------" + EcJSONUtils.toJSONString(userEntity));
		}
		return retList;
	}

	@RequestMapping(value = "save")
	public UserEntity save() {
		for (int i= 0 ; i < 2000; ++i) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					String userName = "username11111111";
					String password = "password333333333";
					Integer status = 2;
					userService.saveUserEntity(new UserEntity(userName, password, status));
				}
			});
		}
		return new UserEntity();
	}
	@RequestMapping(value = "update")
	public UserEntity update(Long id) {
		String userName = "earewrw";
		String password = "eeew22233";
		Integer status = 2;
		UserEntity userEntity = new UserEntity(userName, password, status);
		userEntity.setId(id);
		return userService.updateUserEntity(userEntity);
	}

}
