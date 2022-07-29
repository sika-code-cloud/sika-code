package com.sika.code.core.base.test;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSONObject;
import com.sika.code.core.base.util.JSONUtil;
import com.sika.code.core.result.Result;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class BaseTestController extends BaseTest {
    /**
     * 启用Web上下文
     */
    @Autowired
    protected WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        //使用上下文构建MockMvc
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() {
    }

    /**
     * 使用postjson的方式请求
     */
    protected Result postJson(String requestUrl, Object request) {
        //将参数转换成JSON对象
        String json = JSONObject.toJSONString(request);
        //执行请求（使用POST请求，传递对象参数）
        MvcResult mvcResult = null;
        String content;
        int status;
        try {
            mvcResult = mockMvc.perform(
                    MockMvcRequestBuilders.post(requestUrl)
                            .content(json)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            //获取返回编码
            status = mvcResult.getResponse().getStatus();
            //获取返回结果
            MockHttpServletResponse response = mvcResult.getResponse();
            response.setCharacterEncoding(CharsetUtil.UTF_8);
            content = mvcResult.getResponse().getContentAsString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //断言，判断返回编码是否正确
        Assert.assertEquals(200, status);
        log.info("responseContent:{}", content);
        //断言，判断返回结果是否正确
        Result result = JSONUtil.parseObject(content, Result.class);
        Assert.assertEquals(true, result.getSuccess());
        return result;
    }
}
