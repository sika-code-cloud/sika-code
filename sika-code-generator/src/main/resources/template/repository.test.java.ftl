package ${package.Entity};


import com.sika.code.core.base.test.BaseTestRepository;
import com.google.common.collect.Lists;
import ${sikaPackage.Repository}.${sikaEntityBodyName}Repository;
import ${sikaPackage.PO}.${sikaEntityBodyName}PO;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import org.junit.Assert;
import com.cat.reconciliation.platform.interfaces.CatReconciliationPlatformApplication;
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
@SpringBootTest(classes = CatReconciliationPlatformApplication.class)
public class ${entity} extends BaseTestRepository {
    @Resource
    private ${sikaEntityBodyName}Repository ${sikaEntityBodyName?uncap_first}Repository;

    @Test
    public void testFindByPrimaryKey() {
        ${sikaPrimaryType} primaryKey = 1L;
        ${sikaEntityBodyName}PO ${sikaEntityBodyName?uncap_first}PO = ${sikaEntityBodyName?uncap_first}Repository.findByPrimaryKey(primaryKey);
        Assert.assertNotNull(${sikaEntityBodyName?uncap_first}PO);
    }

    @Test
    public void testUpdateSelectiveByPrimaryKey() {
        ${sikaEntityBodyName}PO ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}PO();
        ${sikaEntityBodyName?uncap_first}PO.setId(null);
        int count = ${sikaEntityBodyName?uncap_first}Repository.save(${sikaEntityBodyName?uncap_first}PO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertSelectiveRetPrimaryKey() {
        ${sikaEntityBodyName}PO ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}PO();
        Long primaryKey = ${sikaEntityBodyName?uncap_first}Repository.saveRetId(${sikaEntityBodyName?uncap_first}PO);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testInsertSelective() {
        ${sikaEntityBodyName}PO ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}PO();
        int count = ${sikaEntityBodyName?uncap_first}Repository.save(${sikaEntityBodyName?uncap_first}PO);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testInsertBatchSelective() {
        List<${sikaEntityBodyName}PO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}PO ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}PO();
            pos.add(${sikaEntityBodyName?uncap_first}PO);
        }
        int count = ${sikaEntityBodyName?uncap_first}Repository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }


    @Test
    public void testUpdateBatchSelectiveByPrimaryKey() {
        List<${sikaEntityBodyName}PO> pos = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}PO ${sikaEntityBodyName?uncap_first}PO = build${sikaEntityBodyName}PO();
            pos.add(${sikaEntityBodyName?uncap_first}PO);
        }
        int count = ${sikaEntityBodyName?uncap_first}Repository.saveBatch(pos);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void testFind() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        ${sikaEntityBodyName}PO po = ${sikaEntityBodyName?uncap_first}Repository.find(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertNotNull(po);
    }

    @Test
    public void testFindId() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        Long primaryKey = ${sikaEntityBodyName?uncap_first}Repository.findId(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(primaryKey > 0);
    }

    @Test
    public void testList() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        List<${sikaEntityBodyName}PO> pos = ${sikaEntityBodyName?uncap_first}Repository.list(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testListId() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        List<Long> primarys = ${sikaEntityBodyName?uncap_first}Repository.listId(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(primarys.size() > 0);
    }

    @Test
    public void testPage() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        List<${sikaEntityBodyName}PO> pos = ${sikaEntityBodyName?uncap_first}Repository.page(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(pos.size() > 0);
    }

    @Test
    public void testCount() {
        ${sikaEntityBodyName}Query ${sikaEntityBodyName?uncap_first}Query = build${sikaEntityBodyName}Query();
        int count = ${sikaEntityBodyName?uncap_first}Repository.count(${sikaEntityBodyName?uncap_first}Query);
        Assert.assertTrue(count > 0);
    }


    private ${sikaEntityBodyName}PO build${sikaEntityBodyName}PO() {
        ${sikaEntityBodyName}PO ${sikaEntityBodyName?uncap_first}PO = new ${sikaEntityBodyName}PO();
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