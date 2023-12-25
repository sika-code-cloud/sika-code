package com.sika.code.demo.interfaces.business.node.repository;


import cn.hutool.core.collection.CollUtil;
import com.sika.code.core.base.pojo.query.SortType;
import com.sika.code.core.base.test.BaseTestRepository;
import com.google.common.collect.Lists;
import com.sika.code.core.util.IdUtil;
import com.sika.code.demo.domain.business.node.repository.NodeRepository;
import com.sika.code.demo.infrastructure.db.business.node.po.NodePO;
import com.sika.code.demo.infrastructure.business.node.pojo.query.NodeQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 节点表持久化操作测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-07-30 13:45:32
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestNodeRepository extends BaseTestRepository {
    @Resource
    private NodeRepository nodeRepository;

    @Test
    public void testFindByPrimaryKey() {
        Long primaryKey = 1596425245660662685L;
        NodePO nodePO = nodeRepository.findByPrimaryKey(primaryKey);
        Assert.assertNotNull(nodePO);
    }

    @Test
    public void testUpdateSelectiveByPrimaryKey() {
        NodePO nodePO = buildNodePO();
        nodePO.setId(null);
        int count = nodeRepository.save(nodePO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertSelectiveRetPrimaryKey() {
        NodePO nodePO = buildNodePO();
        Long primaryKey = nodeRepository.saveRetId(nodePO);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testInsertSelective() {
        NodePO nodePO = buildNodePO();
        int count = nodeRepository.save(nodePO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertBatchSelective() {
        List<NodePO> pos = Lists.newArrayList();
        for (int i = 0; i < 20000; ++i) {
            NodePO nodePO = buildNodePO();
            nodePO.setNodeName("nodeName-" + IdUtil.objectId());
            nodePO.setNodeNo(IdUtil.objectId());
            nodePO.setTenantId(10001L);
            pos.add(nodePO);
        }
        int count = nodeRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }


    @Test
    public void testUpdateBatchSelectiveByPrimaryKey() {
        List<NodePO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            NodePO nodePO = buildNodePO();
            pos.add(nodePO);
        }
        int count = nodeRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testFind() {
        NodeQuery nodeQuery = buildNodeQuery();
        nodeQuery.setId(1596425245660662685L);
        NodePO po = nodeRepository.find(nodeQuery);
        Assert.assertNotNull(po);
    }

    @Test
    public void testFindId() {
        NodeQuery nodeQuery = buildNodeQuery();
        Long primaryKey = nodeRepository.findId(nodeQuery);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testList() {
        NodeQuery nodeQuery = buildNodeQuery();
        List<NodePO> pos = nodeRepository.list(nodeQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testListId() {
        NodeQuery nodeQuery = buildNodeQuery();
        List<Long> primarys = nodeRepository.listId(nodeQuery);
        Assert.assertTrue(primarys.size() > 0);
    }

    @Test
    public void testPage() {
        doRead(5, 100000);
        System.out.println();
        doRead(5, 50000);
        System.out.println();
        doRead(5, 20000);
        System.out.println();
        doRead(5, 10000);
        System.out.println();
        doRead(5, 5000);
        System.out.println();
        doRead(5, 2000);
        System.out.println();
        doRead(5, 1000);
        System.out.println();
    }

    private void doRead(int loop, int pageSize) {
        int maxCount = 3000000;
        for (int i = 0; i < loop; ++i) {
            NodeQuery nodeQuery = buildNodeQuery();
            nodeQuery.setPageSize(pageSize);
            nodeQuery.setStartIndex(0L);
            nodeQuery.setSortColumn("id");
            nodeQuery.setSortType(SortType.ASC);
            int count = 0;
            long startTime = System.currentTimeMillis();
            while (true) {
//                if (count >=
                List<NodePO> pos = nodeRepository.page(nodeQuery);
                if (CollUtil.isNotEmpty(pos)) {
                    count += pos.size();
//                log.info("已经加载配置的数据量有：{}，第一条数据ID为：{}", count, CollUtil.getFirst(pos).getId());
                    nodeQuery.setStartIndex(CollUtil.getLast(pos).getId());
                    // 去做缓存
                } else {
                    break;
                }
            }
            long diffTime = System.currentTimeMillis() - startTime;
            log.info("执行第【{}】次，加载配置的数据量为：{}条，消耗的总耗时为：{} ms, 每秒读取条数为：{} 万条/S，每次读取的数量为：{} 条", i, count, diffTime, count / (diffTime / 1000 ) * 1.0 / 10000, pageSize);
            Assert.assertTrue(count > 0);

        }
    }

    @Test
    public void testCount() {
        NodeQuery nodeQuery = buildNodeQuery();
        int count = nodeRepository.count(nodeQuery);
        Assert.assertTrue(count > 0);
    }


    private NodePO buildNodePO() {
        NodePO nodePO = new NodePO();
        nodePO.setId(null);
        nodePO.setCreateBy(null);
        nodePO.setUpdateBy(null);
        nodePO.setNodeNo(null);
        nodePO.setNodeName(null);
        nodePO.setNodeClazz(null);
        nodePO.setNodeType(null);
        nodePO.setNodeScript(null);
        nodePO.setTenantId(null);
        nodePO.setApplicationId(null);
        return nodePO;
    }

    private NodeQuery buildNodeQuery() {
        NodeQuery nodeQuery = new NodeQuery();
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