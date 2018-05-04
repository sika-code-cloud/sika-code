package com.easy.cloud.core.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.jdbc.user.entity.UserEntity;
import com.easy.cloud.core.jdbc.user.service.UserService;

@RestController
public class EcCoreJdbcController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "testListPage")
	public void testListPage() {
		List<UserEntity> retList = userService.listPage(1, 10);
		System.out.println("-------------------" + retList.size());
		for (UserEntity userEntity : retList) {
			System.out.println("-------------------" + EcJSONUtils.toJSONString(userEntity));
		}
	}

	@RequestMapping(value = "save")
	public UserEntity save() {
		String userName = "username11111111";
		String password = "password333333333";
		Integer status = 2;
		return userService.saveUserEntity(new UserEntity(userName, password, status));
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
