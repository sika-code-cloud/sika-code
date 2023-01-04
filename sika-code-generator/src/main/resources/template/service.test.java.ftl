package ${package.Entity};


import ${sikaPackage.Service}.${sikaEntityBodyName}Service;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaApplicationClassName};

import com.sika.check.infrastructure.common.core.page.TableDataInfo;
import com.sika.code.core.base.test.BaseTestService;
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
    public void testQueryById() {
        Long key = 1L;
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = ${sikaEntityBodyName?uncap_first}Service.queryById(key);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}DTO);
    }

    @Test
    public void testQueryPageList() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        TableDataInfo<${sikaEntityBodyName}DTO> tableDataInfo = ${sikaEntityBodyName?uncap_first}Service.queryPageList(${sikaEntityBodyName?uncap_first}Query, ${sikaEntityBodyName?uncap_first}Query.getPageQuery());
        Assert.assertNotNull(tableDataInfo);
    }

    @Test
    public void testQueryList() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        List<${sikaEntityBodyName}DTO> ${sikaEntityBodyName?uncap_first}s = ${sikaEntityBodyName?uncap_first}Service.queryList(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}s);
    }

    @Test
    public void testInsert() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        Boolean flag = ${sikaEntityBodyName?uncap_first}Service.insert(${sikaEntityBodyName?uncap_first}DTO);
        Assert.assertTrue(flag);
    }

    @Test
    public void testUpdate() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        ${sikaEntityBodyName?uncap_first}DTO.setId(1L);
        boolean result = ${sikaEntityBodyName?uncap_first}Service.update(${sikaEntityBodyName?uncap_first}DTO);
        Assert.assertTrue(result);
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