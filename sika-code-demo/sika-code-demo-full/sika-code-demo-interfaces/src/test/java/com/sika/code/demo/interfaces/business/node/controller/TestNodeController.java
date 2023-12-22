package com.sika.code.demo.interfaces.business.node.controller;


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sika.code.core.base.test.BaseTestController;
import com.sika.code.core.result.Result;
import com.sika.code.demo.infrastructure.business.node.pojo.dto.NodeDTO;
import com.sika.code.demo.infrastructure.business.node.pojo.query.NodeQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p>
 * 节点表控制器测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:35
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestNodeController extends BaseTestController {

    @Test
    public void testSave() {
        NodeDTO nodeDTO = buildNodeDTO();
        Result result = postJson("/node/save", nodeDTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testSaveBatch() {
    List<NodeDTO> DTOs = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodeDTO nodeDTO = buildNodeDTO();
            DTOs.add(nodeDTO);
        }
        Result result = postJson("/node/saveBatch", DTOs);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testUpdateById() {
        NodeDTO nodeDTO = buildNodeDTO();
        Result result = postJson("/node/save", nodeDTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testPage() {
        NodeQuery query = buildNodeQuery();
        Result result = postJson("/node/page", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testFind() {
        NodeQuery query = buildNodeQuery();
        Result result = postJson("/node/find", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testList() {
        NodeQuery query = buildNodeQuery();
        Result result = postJson("/node/list", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    private NodeDTO buildNodeDTO() {
        NodeDTO nodeDTO = new NodeDTO();
        nodeDTO.setId(null);
        nodeDTO.setCreateBy(null);
        nodeDTO.setUpdateBy(null);
        nodeDTO.setNodeNo(null);
        nodeDTO.setNodeName(null);
        nodeDTO.setNodeClazz(null);
        nodeDTO.setNodeType(null);
        nodeDTO.setNodeScript(null);
        nodeDTO.setTenantId(null);
        nodeDTO.setApplicationId(null);
        return nodeDTO;
    }

    private NodeQuery buildNodeQuery() {
        NodeQuery nodeQuery = new NodeQuery();
        nodeQuery.setId(null);
        nodeQuery.setCreateBy(null);
        nodeQuery.setUpdateBy(null);
        nodeQuery.setNodeNo(null);
        nodeQuery.setNodeName(null);
        nodeQuery.setNodeClazz(null);
        nodeQuery.setNodeType(null);
        nodeQuery.setNodeScript(null);
        nodeQuery.setTenantId(null);
        nodeQuery.setApplicationId(null);
        return nodeQuery;
    }
}