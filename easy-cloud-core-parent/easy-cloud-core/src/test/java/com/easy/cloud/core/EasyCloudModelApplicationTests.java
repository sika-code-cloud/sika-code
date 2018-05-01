package com.easy.cloud.core;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.easy.cloud.EcEasyCloudCoreApplication;
import com.easy.cloud.core.jdbc.handler.EcJdbcTemplateHandler;
import com.easy.cloud.core.user.entity.UserEntity;
import com.easy.cloud.core.user.service.UserService;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EcEasyCloudCoreApplication.class) // 指定spring-boot的启动类
public class EasyCloudModelApplicationTests {
	@Autowired
	UserService userService;
	@Test
	public void testFindTList(){
		String sql = "select * from " + EcJdbcTemplateHandler.getEntityTableName(UserEntity.class);
		List<Object> list = new ArrayList<>();
		List<UserEntity> retList = EcJdbcTemplateHandler.findTList(sql, list, UserEntity.class);
		System.out.println("-------------------" + retList.size());
		for(UserEntity userEntity : retList){
			System.out.println("-------------------" + userEntity.getId());
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
		System.out.println(EcJdbcTemplateHandler.save(new UserEntity(userName, password, status)));
	}
	@Test
	public void testSaveOrUpdate(){
		String userName = "earewrw";
		String password = "eeew22233";
		Integer status = 2;
		UserEntity userEntity = new UserEntity(userName, password, status);
//		userEntity.setId(388002497479835648L);
		System.out.println(userService.saveUserEntity(userEntity));
	}
	
	@Test
	public void testFindOne(){
		System.out.println(EcJdbcTemplateHandler.findOne(UserEntity.class, 388002497479835648L));
	}
	
}
