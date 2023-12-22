package com.sika.code.demo.interfaces.business.node.service;


import com.sika.code.core.base.test.BaseTestService;
import com.sika.code.core.base.pojo.query.Page;
import com.google.common.collect.Lists;
import com.sika.code.demo.application.business.node.service.NodeService;
import com.sika.code.demo.infrastructure.business.node.pojo.dto.NodeDTO;
import com.sika.code.demo.infrastructure.business.node.pojo.query.NodeQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 节点表 服务测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:34
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestNodeService extends BaseTestService {
    @Resource
    private NodeService nodeService;

    @Test
    public void testFindByPrimaryKey() {
        Long key = 1L;
        NodeDTO nodeDTO = nodeService.findByPrimaryKey(key);
        Assert.assertNotNull(nodeDTO);
    }

    @Test
    public void testSaveAndRet() {
        NodeDTO nodeDTO = buildNodeDTO();
        NodeDTO nodeDTORet = nodeService.saveAndRet(nodeDTO);
        Assert.assertNotNull(nodeDTORet);
    }

    @Test
    public void testSave() {
        NodeDTO nodeDTO = buildNodeDTO();
        boolean result = nodeService.save(nodeDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveBatchAndRet() {
        List<NodeDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodeDTO nodeDTO = buildNodeDTO();
            pos.add(nodeDTO);
        }
        List<NodeDTO> posRet = nodeService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveBatch() {
        List<NodeDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodeDTO nodeDTO = buildNodeDTO();
            pos.add(nodeDTO);
        }
        boolean result = nodeService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateAndRet() {
        NodeDTO nodeDTO = buildNodeDTO();
        nodeDTO.setId(null);
        NodeDTO nodeDTORet = nodeService.saveAndRet(nodeDTO);
        Assert.assertNotNull(nodeDTORet);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        NodeDTO nodeDTO = buildNodeDTO();
        nodeDTO.setId(null);
        boolean result = nodeService.save(nodeDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateBatchAndRet() {
        List<NodeDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodeDTO nodeDTO = buildNodeDTO();
            pos.add(nodeDTO);
        }
        List<NodeDTO> posRet = nodeService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testUpdateBatch() {
        List<NodeDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodeDTO nodeDTO = buildNodeDTO();
            pos.add(nodeDTO);
        }
        boolean result = nodeService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateAndRet() {
        NodeDTO nodeDTO = buildNodeDTO();
        NodeDTO nodeDTORet = nodeService.saveAndRet(nodeDTO);
        Assert.assertNotNull(nodeDTORet);
    }

    @Test
    public void testSaveOrUpdate() {
        NodeDTO nodeDTO = buildNodeDTO();
        boolean result = nodeService.save(nodeDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateBatchAndRet() {
        List<NodeDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodeDTO nodeDTO = buildNodeDTO();
            pos.add(nodeDTO);
        }
        List<NodeDTO> posRet = nodeService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveOrUpdateBatch() {
        List<NodeDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodeDTO nodeDTO = buildNodeDTO();
            pos.add(nodeDTO);
        }
        boolean result = nodeService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testFind() {
        NodeQuery nodeQuery = buildNodeQuery();
        NodeDTO nodeDTORet = nodeService.find(nodeQuery);
        Assert.assertNotNull(nodeDTORet);
    }

    @Test
    public void testFindId() {
        NodeQuery nodeQuery = buildNodeQuery();
        Long key = nodeService.findId(nodeQuery);
        Assert.assertNotNull(key);
    }

    @Test
    public void testList() {
        NodeQuery nodeQuery = buildNodeQuery();
        List<NodeDTO> nodeDTOS = nodeService.list(nodeQuery);
        Assert.assertTrue(nodeDTOS.size() > 0);
    }

    @Test
    public void testListId() {
        NodeQuery nodeQuery = buildNodeQuery();
        List<Long> keys = nodeService.listId(nodeQuery);
        Assert.assertTrue(keys.size() > 0);
    }

    @Test
    public void testPage() {
        NodeQuery nodeQuery = buildNodeQuery();
        Page<NodeDTO> page = nodeService.page(nodeQuery);
        Assert.assertTrue(page.getTotal() > 0);
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