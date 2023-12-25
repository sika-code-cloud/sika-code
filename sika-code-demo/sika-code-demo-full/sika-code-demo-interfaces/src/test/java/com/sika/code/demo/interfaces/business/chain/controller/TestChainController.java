package com.sika.code.demo.interfaces.business.chain.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sika.code.core.base.test.BaseTestController;
import com.sika.code.core.result.Result;
import com.sika.code.demo.infrastructure.business.chain.pojo.dto.ChainDTO;
import com.sika.code.demo.infrastructure.business.chain.pojo.query.ChainQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p>
 * 流程表控制器测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:21
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestChainController extends BaseTestController {

    @Test
    public void testSave() {
        ChainDTO chainDTO = buildChainDTO();
        Result result = postJson("/chain/save", chainDTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testSaveBatch() {
    List<ChainDTO> DTOs = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainDTO chainDTO = buildChainDTO();
            DTOs.add(chainDTO);
        }
        Result result = postJson("/chain/saveBatch", DTOs);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testUpdateById() {
        ChainDTO chainDTO = buildChainDTO();
        Result result = postJson("/chain/save", chainDTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testPage() {
        ChainQuery query = buildChainQuery();
        Result result = postJson("/chain/page", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testFind() {
        ChainQuery query = buildChainQuery();
        Result result = postJson("/chain/find", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testList() {
        ChainQuery query = buildChainQuery();
        Result result = postJson("/chain/list", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    private ChainDTO buildChainDTO() {
        ChainDTO chainDTO = new ChainDTO();
        chainDTO.setId(null);
        chainDTO.setCreateBy(null);
        chainDTO.setUpdateBy(null);
        chainDTO.setChainName(null);
        chainDTO.setTenantId(null);
        chainDTO.setApplicationId(null);
        chainDTO.setDeleted(null);
        return chainDTO;
    }

    private ChainQuery buildChainQuery() {
        ChainQuery chainQuery = new ChainQuery();
        chainQuery.setId(null);
        chainQuery.setCreateBy(null);
        chainQuery.setUpdateBy(null);
        chainQuery.setChainName(null);
        chainQuery.setTenantId(null);
        chainQuery.setApplicationId(null);
        chainQuery.setDeleted(null);
        return chainQuery;
    }
}