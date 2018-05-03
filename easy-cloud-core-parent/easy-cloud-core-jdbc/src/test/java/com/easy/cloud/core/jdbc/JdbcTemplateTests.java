package com.easy.cloud.core.jdbc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.jdbc.user.entity.UserEntity;
import com.easy.cloud.core.jdbc.user.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EcCoreJdbcApplication.class) // 指定spring-boot的启动类
public class JdbcTemplateTests {
	@Autowired
	UserService userService;
	@Test
	public void testListPage(){
		List<UserEntity> retList = userService.listPage(1, 10);
		System.out.println("-------------------" + retList.size());
		for(UserEntity userEntity : retList){
			System.out.println("-------------------" + EcJSONUtils.toJSONString(userEntity));
		}
	}
	
	@Test
	public void testSave(){
		save();
	}
	@Transactional
	public void save(){
		String userName = "username11111111";
		String password = "password333333333";
		Integer status = 2;
	}
	@Test
	public void testSaveOrUpdate(){
		String userName = "earewrw";
		String password = "eeew22233";
		Integer status = 2;
		UserEntity userEntity = new UserEntity(userName, password, status);
//		userEntity.setId(388002497479835648L);
		userService.saveUserEntity(userEntity);
	}
	
	
}
