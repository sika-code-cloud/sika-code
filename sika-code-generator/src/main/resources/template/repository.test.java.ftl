package ${package.Entity};


import ${sikaPackage.Repository}.${sikaEntityBodyName}Repository;
import ${sikaPackage.PO}.${sikaEntityBodyName};
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaApplicationClassName};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.sika.check.infrastructure.common.core.domain.MpPageQuery;
import com.sika.code.core.base.test.BaseTestRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;



/**
 * <p>
 * ${table.comment!}持久化操作测试类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@SpringBootTest(classes = ${sikaApplicationSimpleName}.class)
public class ${entity} extends BaseTestRepository {
    @Resource
    private ${sikaEntityBodyName}Repository ${sikaEntityBodyName?uncap_first}Repository;

    @Test
    public void testSelectById() {
        Long primaryKey = 1L;
        ${sikaEntityBodyName} ${sikaEntityBodyName?uncap_first}PO = ${sikaEntityBodyName?uncap_first}Repository.selectById(primaryKey);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}PO);
    }

    @Test
    public void testInsertSelectiveRetPrimaryKey() {
        ${sikaEntityBodyName} ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}();
        int count = ${sikaEntityBodyName?uncap_first}Repository.insert(${sikaEntityBodyName?uncap_first}PO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testUpdateById() {
        ${sikaEntityBodyName} ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}();
        ${sikaEntityBodyName?uncap_first}PO.setId(1L);
        int count = ${sikaEntityBodyName?uncap_first}Repository.updateById(${sikaEntityBodyName?uncap_first}PO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertBatch() {
        List<${sikaEntityBodyName}> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName} ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}();
            pos.add(${sikaEntityBodyName?uncap_first}PO);
        }
        boolean flag = ${sikaEntityBodyName?uncap_first}Repository.insertBatch(pos);
        Assert.assertTrue(flag);
    }


    @Test
    public void testUpdateBatchById() {
        List<${sikaEntityBodyName}> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName} ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}();
            pos.add(${sikaEntityBodyName?uncap_first}PO);
        }
        boolean flag = ${sikaEntityBodyName?uncap_first}Repository.updateBatchById(pos);
        Assert.assertTrue(flag);
    }

    @Test
    public void testPage() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        MpPageQuery pageQuery = new MpPageQuery();
        Page<${sikaEntityBodyName}DTO> pos = ${sikaEntityBodyName?uncap_first}Repository.selectPage(pageQuery.build(), ${sikaEntityBodyName?uncap_first}Query, ${sikaEntityBodyName}DTO.class);
        Assert.assertTrue(pos.getSize() > 0);
    }

    @Test
    public void testFind() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        ${sikaEntityBodyName} po = ${sikaEntityBodyName?uncap_first}Repository.find(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertNotNull(po);
    }

    @Test
    public void testList() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        List<${sikaEntityBodyName}> pos = ${sikaEntityBodyName?uncap_first}Repository.list(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testCount() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        int count = ${sikaEntityBodyName?uncap_first}Repository.count(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(count > 0);
    }

    private ${sikaEntityBodyName} build${sikaEntityBodyName}() {
        ${sikaEntityBodyName} ${sikaEntityBodyName?uncap_first}PO = new ${sikaEntityBodyName}();
        ${sikaEntityBodyName?uncap_first}PO.setId(null);
    <#list table.fields as field>
        ${sikaEntityBodyName?uncap_first}PO.set${field.capitalName}(null);
    </#list>
        return ${sikaEntityBodyName?uncap_first}PO;
    }
    
    private ${sikaEntityBodyName}Query build${sikaEntityBodyName}Query() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = new ${sikaEntityBodyName}Query();
        <#list table.fields as field>
        ${sikaEntityBodyName?uncap_first}Query.set${field.capitalName}(null);
        </#list>
        return ${sikaEntityBodyName?uncap_first}Query;
    }
}