package com.dq.easy.cloud.model.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dq.easy.cloud.DqEasyCloudModelApplication;
import com.dq.easy.cloud.model.user.entity.UserEntity;
import com.dq.easy.cloud.model.user.service.UserService;
import com.dq.easy.cloud.module.jdbc.handler.DqJdbcTemplateHandler;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DqEasyCloudModelApplication.class) // 指定spring-boot的启动类
public class JdbcTemplateTests {
	@Autowired
	UserService userService;
	@Test
	public void testFindTList(){
		String sql = "select * from " + DqJdbcTemplateHandler.getEntityTableName(UserEntity.class);
		List<Object> list = new ArrayList<>();
		List<UserEntity> retList = DqJdbcTemplateHandler.findTList(sql, list, UserEntity.class);
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
		System.out.println(DqJdbcTemplateHandler.save(new UserEntity(userName, password, status)));
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
		System.out.println(DqJdbcTemplateHandler.findOne(UserEntity.class, 388002497479835648L));
	}
	
}
