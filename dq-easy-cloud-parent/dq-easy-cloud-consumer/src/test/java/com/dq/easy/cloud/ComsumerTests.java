package com.dq.easy.cloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DqEasyCloudComsumerApplication.class) // 指定spring-boot的启动类
public class ComsumerTests {
	@Test
	public void testE(){
		System.out.println("rewarew");
	}
}
