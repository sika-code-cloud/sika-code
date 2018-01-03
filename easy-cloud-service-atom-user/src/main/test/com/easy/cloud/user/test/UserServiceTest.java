package com.easy.cloud.user.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.easy.cloud.UserAtomApplication;
import com.easy.cloud.user.entity.UserEntity;
import com.easy.cloud.user.service.inf.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserAtomApplication.class) // 指定spring-boot的启动类
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Test
	public void testFindOne(){
		Long id = 398261488243965952l;
		System.out.println("==================" + userService.findUserById(id));
	}
	
	@Test
	public void testSave(){
		UserEntity userEntity = new UserEntity("userName", "password", 1);
		userService.saveUserInfo(userEntity);
	}
}
