package com.sika.code.demo.interfaces.business.testtemp.repository;


import com.sika.code.core.base.test.BaseTestRepository;
import com.google.common.collect.Lists;
import com.sika.code.demo.domain.business.testtemp.repository.TestTempRepository;
import com.sika.code.demo.infrastructure.db.business.testtemp.po.TestTempPO;
import com.sika.code.demo.infrastructure.business.testtemp.pojo.query.TestTempQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 测试tem表持久化操作测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:46
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestTestTempRepository extends BaseTestRepository {
    @Resource
    private TestTempRepository testTempRepository;

    @Test
    public void testFindByPrimaryKey() {
        Long primaryKey = 1L;
        TestTempPO testTempPO = testTempRepository.findByPrimaryKey(primaryKey);
        Assert.assertNotNull(testTempPO);
    }

    @Test
    public void testUpdateSelectiveByPrimaryKey() {
        TestTempPO testTempPO = buildTestTempPO();
        testTempPO.setId(null);
        int count = testTempRepository.save(testTempPO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertSelectiveRetPrimaryKey() {
        TestTempPO testTempPO = buildTestTempPO();
        Long primaryKey = testTempRepository.saveRetId(testTempPO);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testInsertSelective() {
        TestTempPO testTempPO = buildTestTempPO();
        int count = testTempRepository.save(testTempPO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertBatchSelective() {
        List<TestTempPO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempPO testTempPO = buildTestTempPO();
            pos.add(testTempPO);
        }
        int count = testTempRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }


    @Test
    public void testUpdateBatchSelectiveByPrimaryKey() {
        List<TestTempPO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempPO testTempPO = buildTestTempPO();
            pos.add(testTempPO);
        }
        int count = testTempRepository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testLogicDelete() {
        int count = testTempRepository.logicDeleteById(1562826380784197633L);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testFind() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        TestTempPO po = testTempRepository.find(testTempQuery);
        Assert.assertNotNull(po);
    }

    @Test
    public void testFindId() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        Long primaryKey = testTempRepository.findId(testTempQuery);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testList() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        List<TestTempPO> pos = testTempRepository.list(testTempQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testListId() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        List<Long> primarys = testTempRepository.listId(testTempQuery);
        Assert.assertTrue(primarys.size() > 0);
    }

    @Test
    public void testPage() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        List<TestTempPO> pos = testTempRepository.page(testTempQuery);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testCount() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        int count = testTempRepository.count(testTempQuery);
        Assert.assertTrue(count > 0);
    }


    private TestTempPO buildTestTempPO() {
        TestTempPO testTempPO = new TestTempPO();
        testTempPO.setId(null);
        testTempPO.setCreateBy(null);
        testTempPO.setUpdateBy(null);
        testTempPO.setTestNo(null);
        testTempPO.setTestName(null);
        testTempPO.setRecordDate(null);
        return testTempPO;
    }
    
    private TestTempQuery buildTestTempQuery() {
        TestTempQuery testTempQuery = new TestTempQuery();
        testTempQuery.setCreateBy(null);
        testTempQuery.setUpdateBy(null);
        testTempQuery.setTestNo(null);
        testTempQuery.setTestName(null);
        testTempQuery.setRecordDate(null);
        return testTempQuery;
    }
}