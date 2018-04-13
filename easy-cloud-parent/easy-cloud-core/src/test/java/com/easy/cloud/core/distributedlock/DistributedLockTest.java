package com.easy.cloud.core.distributedlock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.EcEasyCloudCoreApplication;
import com.easy.cloud.core.distributedlock.controller.DistributedLockTestController;

/**
 * 
 * <p>
 * 分布式锁测试类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月13日 下午7:24:28
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = EcEasyCloudCoreApplication.class) // 指定我们SpringBoot工程的Application启动类
public class DistributedLockTest {
	// @Autowired
	private MockMvc mockMvc;

	@Autowired
	DistributedLockTestController distributedLockTestController;

	@Autowired
	private WebApplicationContext wac;

	@Before
	public void setup() {
		// MockMvcBuilders使用构建MockMvc对象 （项目拦截器有效）
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

		// 单个类 拦截器无效

		// mockMvc = MockMvcBuilders.standaloneSteup(userController).build();

	}

	@Test
	public void testDistributedLock() throws Exception {
		JSONObject param = new JSONObject();
		param.put("userId", "10");
		String jsonstr = param.toString();
		String uri = "/distributedLockTest/distributedLockTest"; 
		System.out.println("================================请求入参：" + jsonstr);
		RequestBuilder request = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_UTF8)
				.header("SESSIONNO", "").content(jsonstr);

		MvcResult mvcResult = mockMvc.perform(request).andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();

//		Assert.assertTrue("正确", status == 200);
//		Assert.assertFalse("错误", status != 200);

		System.out.println("返回结果：" + status);
		System.out.println(content);

	}
	@Test
	public void testDistributedLockParam() throws Exception {
		JSONObject param = new JSONObject();
		param.put("userId", "10");
		String jsonstr = param.toString();
		String userId = "10";
		String uri = "/distributedLockTest/distributedLockTest"; 
		System.out.println("================================请求入参：" + uri);
		RequestBuilder request = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_UTF8)
				.header("SESSIONNO", "").content(jsonstr);

		MvcResult mvcResult = mockMvc.perform(request).andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();

//		Assert.assertTrue("正确", status == 200);
//		Assert.assertFalse("错误", status != 200);

		System.out.println("返回结果：" + status);
		System.out.println(content);
		Thread.sleep(Long.MAX_VALUE);

	}
}
