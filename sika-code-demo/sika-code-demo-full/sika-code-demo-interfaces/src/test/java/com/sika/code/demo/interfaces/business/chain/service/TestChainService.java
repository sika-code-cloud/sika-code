package com.sika.code.demo.interfaces.business.chain.service;


import com.sika.code.core.base.test.BaseTestService;
import com.sika.code.core.base.pojo.query.Page;
import com.google.common.collect.Lists;
import com.sika.code.demo.application.business.chain.service.ChainService;
import com.sika.code.demo.infrastructure.business.chain.pojo.dto.ChainDTO;
import com.sika.code.demo.infrastructure.business.chain.pojo.query.ChainQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 流程表 服务测试类
 * </p>
 *
 * @author sikadai
 * @since 2022-08-19 00:12:20
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestChainService extends BaseTestService {
    @Resource
    private ChainService chainService;

    @Test
    public void testFindByPrimaryKey() {
        Long key = 1L;
        ChainDTO chainDTO = chainService.findByPrimaryKey(key);
        Assert.assertNotNull(chainDTO);
    }

    @Test
    public void testSaveAndRet() {
        ChainDTO chainDTO = buildChainDTO();
        ChainDTO chainDTORet = chainService.saveAndRet(chainDTO);
        Assert.assertNotNull(chainDTORet);
    }

    @Test
    public void testSave() {
        ChainDTO chainDTO = buildChainDTO();
        boolean result = chainService.save(chainDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveBatchAndRet() {
        List<ChainDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainDTO chainDTO = buildChainDTO();
            pos.add(chainDTO);
        }
        List<ChainDTO> posRet = chainService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveBatch() {
        List<ChainDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainDTO chainDTO = buildChainDTO();
            pos.add(chainDTO);
        }
        boolean result = chainService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateAndRet() {
        ChainDTO chainDTO = buildChainDTO();
        chainDTO.setId(null);
        ChainDTO chainDTORet = chainService.saveAndRet(chainDTO);
        Assert.assertNotNull(chainDTORet);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        ChainDTO chainDTO = buildChainDTO();
        chainDTO.setId(null);
        boolean result = chainService.save(chainDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateBatchAndRet() {
        List<ChainDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainDTO chainDTO = buildChainDTO();
            pos.add(chainDTO);
        }
        List<ChainDTO> posRet = chainService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testUpdateBatch() {
        List<ChainDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainDTO chainDTO = buildChainDTO();
            pos.add(chainDTO);
        }
        boolean result = chainService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateAndRet() {
        ChainDTO chainDTO = buildChainDTO();
        ChainDTO chainDTORet = chainService.saveAndRet(chainDTO);
        Assert.assertNotNull(chainDTORet);
    }

    @Test
    public void testSaveOrUpdate() {
        ChainDTO chainDTO = buildChainDTO();
        boolean result = chainService.save(chainDTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateBatchAndRet() {
        List<ChainDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainDTO chainDTO = buildChainDTO();
            pos.add(chainDTO);
        }
        List<ChainDTO> posRet = chainService.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveOrUpdateBatch() {
        List<ChainDTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ChainDTO chainDTO = buildChainDTO();
            pos.add(chainDTO);
        }
        boolean result = chainService.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testFind() {
        ChainQuery chainQuery = buildChainQuery();
        ChainDTO chainDTORet = chainService.find(chainQuery);
        Assert.assertNotNull(chainDTORet);
    }

    @Test
    public void testFindId() {
        ChainQuery chainQuery = buildChainQuery();
        Long key = chainService.findId(chainQuery);
        Assert.assertNotNull(key);
    }

    @Test
    public void testList() {
        ChainQuery chainQuery = buildChainQuery();
        List<ChainDTO> chainDTOS = chainService.list(chainQuery);
        Assert.assertTrue(chainDTOS.size() > 0);
    }

    @Test
    public void testListId() {
        ChainQuery chainQuery = buildChainQuery();
        List<Long> keys = chainService.listId(chainQuery);
        Assert.assertTrue(keys.size() > 0);
    }

    @Test
    public void testPage() {
        ChainQuery chainQuery = buildChainQuery();
        Page<ChainDTO> page = chainService.page(chainQuery);
        Assert.assertTrue(page.getTotal() > 0);
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