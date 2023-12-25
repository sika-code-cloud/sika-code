package com.sika.code.demo.interfaces.business.testtemp.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sika.code.core.base.test.BaseTestController;
import com.sika.code.core.result.Result;
import com.sika.code.demo.infrastructure.business.testtemp.pojo.dto.TestTempDTO;
import com.sika.code.demo.infrastructure.business.testtemp.pojo.query.TestTempQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p>
 * 测试tem表控制器测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:48
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestTestTempController extends BaseTestController {

    @Test
    public void testSave() {
        TestTempDTO testTempDTO = buildTestTempDTO();
        Result result = postJson("/testTemp/save", testTempDTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testSaveBatch() {
    List<TestTempDTO> DTOs = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempDTO testTempDTO = buildTestTempDTO();
            DTOs.add(testTempDTO);
        }
        Result result = postJson("/testTemp/saveBatch", DTOs);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testUpdateById() {
        TestTempDTO testTempDTO = buildTestTempDTO();
        Result result = postJson("/testTemp/save", testTempDTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testPage() {
        TestTempQuery query = buildTestTempQuery();
        Result result = postJson("/testTemp/page", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testFind() {
        TestTempQuery query = buildTestTempQuery();
        Result result = postJson("/testTemp/find", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testList() {
        TestTempQuery query = buildTestTempQuery();
        Result result = postJson("/testTemp/list", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    private TestTempDTO buildTestTempDTO() {
        TestTempDTO testTempDTO = new TestTempDTO();
        testTempDTO.setId(null);
        testTempDTO.setCreateBy(null);
        testTempDTO.setUpdateBy(null);
        testTempDTO.setTestNo(null);
        testTempDTO.setTestName(null);
        testTempDTO.setRecordDate(null);
        return testTempDTO;
    }

    private TestTempQuery buildTestTempQuery() {
        TestTempQuery testTempQuery = new TestTempQuery();
        testTempQuery.setId(null);
        testTempQuery.setCreateBy(null);
        testTempQuery.setUpdateBy(null);
        testTempQuery.setTestNo(null);
        testTempQuery.setTestName(null);
        testTempQuery.setRecordDate(null);
        return testTempQuery;
    }
}