package com.sika.code.demo.interfaces.business.chain.repository;


import com.sika.code.core.base.test.BaseTestRepository;
import com.google.common.collect.Lists;
import com.sika.code.demo.domain.business.chain.repository.ChainRepository;
import com.sika.code.demo.infrastructure.db.business.chain.po.ChainPO;
import com.sika.code.demo.infrastructure.business.chain.pojo.query.ChainQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 流程表持久化操作测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:20
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestChainRepository extends BaseTestRepository {
    @Resource
    private ChainRepository chainRepository;

    @Test
    public void testFindByPrimaryKey() {
        Long primaryKey = 1L;
        ChainPO chainPO = chainRepository.findByPrimaryKey(primaryKey);
        Assert.assertNotNull(chainPO);
    }

    @Test
    public void testUpdateSelectiveByPrimaryKey() {
        ChainPO chainPO = buildChainPO();
        chainPO.setId(null);
        int count = chainRepository.save(chainPO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertSelectiveRetPrimaryKey() {
        ChainPO chainPO = buildChainPO();
        Long primaryKey = chainRepository.saveRetId(chainPO);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testInsertSelective() {
        ChainPO chainPO = buildChainPO();
        int count = chainRepository.save(chainPO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertBatchSelective() {
        List<ChainPO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainPO chainPO = buildChainPO();
            pos.add(chainPO);
        }
        int count = chainRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }


    @Test
    public void testUpdateBatchSelectiveByPrimaryKey() {
        List<ChainPO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainPO chainPO = buildChainPO();
            pos.add(chainPO);
        }
        int count = chainRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testFind() {
        ChainQuery chainQuery = buildChainQuery();
        ChainPO po = chainRepository.find(chainQuery);
        Assert.assertNotNull(po);
    }

    @Test
    public void testFindId() {
        ChainQuery chainQuery = buildChainQuery();
        Long primaryKey = chainRepository.findId(chainQuery);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testList() {
        ChainQuery chainQuery = buildChainQuery();
        List<ChainPO> pos = chainRepository.list(chainQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testListId() {
        ChainQuery chainQuery = buildChainQuery();
        List<Long> primarys = chainRepository.listId(chainQuery);
        Assert.assertTrue(primarys.size() > 0);
    }

    @Test
    public void testPage() {
        ChainQuery chainQuery = buildChainQuery();
        List<ChainPO> pos = chainRepository.page(chainQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testCount() {
        ChainQuery chainQuery = buildChainQuery();
        int count = chainRepository.count(chainQuery);
        Assert.assertTrue(count > 0);
    }


    private ChainPO buildChainPO() {
        ChainPO chainPO = new ChainPO();
        chainPO.setId(null);
        chainPO.setCreateBy(null);
        chainPO.setUpdateBy(null);
        chainPO.setChainName(null);
        chainPO.setTenantId(null);
        chainPO.setApplicationId(null);
        chainPO.setDeleted(null);
        return chainPO;
    }
    
    private ChainQuery buildChainQuery() {
        ChainQuery chainQuery = new ChainQuery();
        chainQuery.setCreateBy(null);
        chainQuery.setUpdateBy(null);
        chainQuery.setChainName(null);
        chainQuery.setTenantId(null);
        chainQuery.setApplicationId(null);
        chainQuery.setDeleted(null);
        return chainQuery;
    }
}