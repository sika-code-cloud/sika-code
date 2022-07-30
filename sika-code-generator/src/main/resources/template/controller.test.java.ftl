package ${package.Entity};


import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.sika.code.core.base.test.BaseTestController;
import com.sika.code.core.result.Result;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaApplicationClassName};
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * <p>
 * ${table.comment!}控制器测试类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@SpringBootTest(classes = ${sikaApplicationSimpleName}.class)
public class ${entity} extends BaseTestController {

    @Test
    public void testSave() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        Result result = postJson("/${sikaEntityBodyName?uncap_first}/save", ${sikaEntityBodyName?uncap_first}DTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testSaveBatch() {
    List<${sikaEntityBodyName}DTO> DTOs = Lists.newArrayList();
        for (int i = 0; i < 10; ++i) {
            ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
            DTOs.add(${sikaEntityBodyName?uncap_first}DTO);
        }
        Result result = postJson("/${sikaEntityBodyName?uncap_first}/saveBatch", DTOs);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testUpdateById() {
        ${sikaEntityBodyName}DTO ${sikaEntityBodyName?uncap_first}DTO = build${sikaEntityBodyName}DTO();
        Result result = postJson("/${sikaEntityBodyName?uncap_first}/save", ${sikaEntityBodyName?uncap_first}DTO);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testPage() {
        ${sikaEntityBodyName}Query query = build${sikaEntityBodyName}Query();
        Result result = postJson("/${sikaEntityBodyName?uncap_first}/page", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testFind() {
        ${sikaEntityBodyName}Query query = build${sikaEntityBodyName}Query();
        Result result = postJson("/${sikaEntityBodyName?uncap_first}/find", query);
        log.info("result:{}", JSONObject.toJSONString(result));
    }

    @Test
    public void testList() {
        ${sikaEntityBodyName}Query query = build${sikaEntityBodyName}Query();
        Result result = postJson("/${sikaEntityBodyName?uncap_first}/list", query);
        log.info("result:{}", JSONObject.toJSONString(result));
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