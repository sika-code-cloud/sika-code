package ${package.Entity};


import com.sika.code.core.base.test.BaseTestService;
import com.sika.code.core.base.pojo.query.Page;
import com.google.common.collect.Lists;
import ${sikaPackage.Service}.${sikaEntityBodyName}Service;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaApplicationClassName};
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务测试类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@SpringBootTest(classes = ${sikaApplicationSimpleName}.class)
public class ${entity} extends BaseTestService {
    @Resource
    private ${sikaEntityBodyName}Service ${sikaEntityBodyName?uncap_first}Service;

    @Test
    public void testFindByPrimaryKey() {
        ${sikaPrimaryType} key = 1L;
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = ${sikaEntityBodyName?uncap_first}Service.findByPrimaryKey(key);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}DTO);
    }

    @Test
    public void testSaveAndRet() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTORet = ${sikaEntityBodyName?uncap_first}Service.saveAndRet(${sikaEntityBodyName?uncap_first}DTO);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}DTORet);
    }

    @Test
    public void testSave() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        boolean result = ${sikaEntityBodyName?uncap_first}Service.save(${sikaEntityBodyName?uncap_first}DTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveBatchAndRet() {
        List<${sikaEntityBodyName}DTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
            pos.add(${sikaEntityBodyName?uncap_first}DTO);
        }
        List<${sikaEntityBodyName}DTO> posRet = ${sikaEntityBodyName?uncap_first}Service.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveBatch() {
        List<${sikaEntityBodyName}DTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
            pos.add(${sikaEntityBodyName?uncap_first}DTO);
        }
        boolean result = ${sikaEntityBodyName?uncap_first}Service.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateAndRet() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        ${sikaEntityBodyName?uncap_first}DTO.setId(null);
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTORet = ${sikaEntityBodyName?uncap_first}Service.saveAndRet(${sikaEntityBodyName?uncap_first}DTO);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}DTORet);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        ${sikaEntityBodyName?uncap_first}DTO.setId(null);
        boolean result = ${sikaEntityBodyName?uncap_first}Service.save(${sikaEntityBodyName?uncap_first}DTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testUpdateBatchAndRet() {
        List<${sikaEntityBodyName}DTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
            pos.add(${sikaEntityBodyName?uncap_first}DTO);
        }
        List<${sikaEntityBodyName}DTO> posRet = ${sikaEntityBodyName?uncap_first}Service.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testUpdateBatch() {
        List<${sikaEntityBodyName}DTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
            pos.add(${sikaEntityBodyName?uncap_first}DTO);
        }
        boolean result = ${sikaEntityBodyName?uncap_first}Service.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateAndRet() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTORet = ${sikaEntityBodyName?uncap_first}Service.saveAndRet(${sikaEntityBodyName?uncap_first}DTO);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}DTORet);
    }

    @Test
    public void testSaveOrUpdate() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        boolean result = ${sikaEntityBodyName?uncap_first}Service.save(${sikaEntityBodyName?uncap_first}DTO);
        Assert.assertTrue(result);
    }

    @Test
    public void testSaveOrUpdateBatchAndRet() {
        List<${sikaEntityBodyName}DTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
            pos.add(${sikaEntityBodyName?uncap_first}DTO);
        }
        List<${sikaEntityBodyName}DTO> posRet = ${sikaEntityBodyName?uncap_first}Service.saveBatchAndRet(pos);
        Assert.assertTrue(posRet.size() > 0);
    }

    @Test
    public void testSaveOrUpdateBatch() {
        List<${sikaEntityBodyName}DTO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
            pos.add(${sikaEntityBodyName?uncap_first}DTO);
        }
        boolean result = ${sikaEntityBodyName?uncap_first}Service.saveBatch(pos);
        Assert.assertTrue(result);
    }

    @Test
    public void testFind() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTORet = ${sikaEntityBodyName?uncap_first}Service.find(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}DTORet);
    }

    @Test
    public void testFindId() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        Long key = ${sikaEntityBodyName?uncap_first}Service.findId(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertNotNull(key);
    }

    @Test
    public void testList() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        List<${sikaEntityBodyName}DTO> ${sikaEntityBodyName?uncap_first}DTOS = ${sikaEntityBodyName?uncap_first}Service.list(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(${sikaEntityBodyName?uncap_first}DTOS.size() > 0);
    }

    @Test
    public void testListId() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        List<Long> keys = ${sikaEntityBodyName?uncap_first}Service.listId(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(keys.size() > 0);
    }

    @Test
    public void testPage() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        Page<${sikaEntityBodyName}DTO> page = ${sikaEntityBodyName?uncap_first}Service.page(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(page.getTotal() > 0);
    }


    private ${sikaEntityBodyName}DTO build${sikaEntityBodyName}DTO() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = new ${sikaEntityBodyName}DTO();
        ${sikaEntityBodyName?uncap_first}DTO.setId(null);
        <#list table.fields as field>
        ${sikaEntityBodyName?uncap_first}DTO.set${field.capitalName}(null);
        </#list>
        return ${sikaEntityBodyName?uncap_first}DTO;
    }

    private ${sikaEntityBodyName}Query build${sikaEntityBodyName}Query() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = new ${sikaEntityBodyName}Query();
        ${sikaEntityBodyName?uncap_first}Query.setId(null);
        <#list table.fields as field>
        ${sikaEntityBodyName?uncap_first}Query.set${field.capitalName}(null);
        </#list>
        return ${sikaEntityBodyName?uncap_first}Query;
    }
}