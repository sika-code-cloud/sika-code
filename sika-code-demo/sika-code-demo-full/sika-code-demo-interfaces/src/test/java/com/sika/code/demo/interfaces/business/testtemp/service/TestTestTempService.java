package com.sika.code.demo.interfaces.business.testtemp.service;


import cn.hutool.core.util.IdUtil;
import com.sika.code.core.base.test.BaseTestService;
import com.sika.code.core.base.pojo.query.Page;
import com.google.common.collect.Lists;
import com.sika.code.demo.application.business.testtemp.service.TestTempService;
import com.sika.code.demo.infrastructure.business.testtemp.pojo.dto.TestTempDTO;
import com.sika.code.demo.infrastructure.business.testtemp.pojo.query.TestTempQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 测试tem表 服务测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-25 23:29:47
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestTestTempService extends BaseTestService {
    @Resource
    private TestTempService testTempService;

    @Test
    public void testFindByPrimaryKey() {
        Long key = 1L;
        TestTempDTO testTempDTO = testTempService.findByPrimaryKey(key);
        Assert.assertNotNull(testTempDTO);
    }

    @Test
    public void testSaveAndRet() {
        TestTempDTO testTempDTO = buildTestTempDTO();
        TestTempDTO testTempDTORet = testTempService.saveAndRet(testTempDTO);
        Assert.assertNotNull(testTempDTORet);
    }

    @Test
    public void testSave() {
        TestTempDTO testTempDTO = buildTestTempDTO();
        testTempDTO.setTestName(IdUtil.fastSimpleUUID());
        boolean result = testTempService.save(testTempDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveBatchAndRet() {
        List<TestTempDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempDTO testTempDTO = buildTestTempDTO();
            pos.add(testTempDTO);
        }
        List<TestTempDTO> posRet = testTempService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveBatch() {
        List<TestTempDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempDTO testTempDTO = buildTestTempDTO();
            pos.add(testTempDTO);
        }
        boolean result = testTempService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateAndRet() {
        TestTempDTO testTempDTO = buildTestTempDTO();
        testTempDTO.setId(null);
        TestTempDTO testTempDTORet = testTempService.saveAndRet(testTempDTO);
        Assert.assertNotNull(testTempDTORet);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        TestTempDTO testTempDTO = buildTestTempDTO();
        testTempDTO.setId(null);
        boolean result = testTempService.save(testTempDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateBatchAndRet() {
        List<TestTempDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempDTO testTempDTO = buildTestTempDTO();
            pos.add(testTempDTO);
        }
        List<TestTempDTO> posRet = testTempService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testUpdateBatch() {
        List<TestTempDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempDTO testTempDTO = buildTestTempDTO();
            pos.add(testTempDTO);
        }
        boolean result = testTempService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateAndRet() {
        TestTempDTO testTempDTO = buildTestTempDTO();
        TestTempDTO testTempDTORet = testTempService.saveAndRet(testTempDTO);
        Assert.assertNotNull(testTempDTORet);
    }

    @Test
    public void testSaveOrUpdate() {
        TestTempDTO testTempDTO = buildTestTempDTO();
        boolean result = testTempService.save(testTempDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateBatchAndRet() {
        List<TestTempDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempDTO testTempDTO = buildTestTempDTO();
            pos.add(testTempDTO);
        }
        List<TestTempDTO> posRet = testTempService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveOrUpdateBatch() {
        List<TestTempDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            TestTempDTO testTempDTO = buildTestTempDTO();
            pos.add(testTempDTO);
        }
        boolean result = testTempService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testFind() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        TestTempDTO testTempDTORet = testTempService.find(testTempQuery);
        Assert.assertNotNull(testTempDTORet);
    }

    @Test
    public void testFindId() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        Long key = testTempService.findId(testTempQuery);
        Assert.assertNotNull(key);
    }

    @Test
    public void testList() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        List<TestTempDTO> testTempDTOS = testTempService.list(testTempQuery);
        Assert.assertTrue(testTempDTOS.size() > 0);
    }

    @Test
    public void testListId() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        List<Long> keys = testTempService.listId(testTempQuery);
        Assert.assertTrue(keys.size() > 0);
    }

    @Test
    public void testPage() {
        TestTempQuery testTempQuery = buildTestTempQuery();
        Page<TestTempDTO> page = testTempService.page(testTempQuery);
        Assert.assertTrue(page.getTotal() > 0);
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