package ${package.Entity};


import org.springframework.web.bind.annotation.RequestMapping;

import com.sika.code.core.result.Result;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;

import ${sikaPackage.Service}.${sikaEntityBodyName}Service;
import com.sika.code.demo.interfaces.common.controller.BaseBizController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@RequestMapping("${sikaEntityBodyName?uncap_first}")
public class ${entity} extends BaseBizController {

    @Resource
    private ${sikaEntityBodyName}Service ${sikaEntityBodyName?uncap_first}Service;

    @RequestMapping(value = "save")
    public Result save(@RequestBody ${sikaEntityBodyName}DTO dto) {
        return success(${sikaEntityBodyName?uncap_first}Service.save(dto));
    }


    @RequestMapping(value = "saveBatch")
    public Result saveBatch(@RequestBody List<${sikaEntityBodyName}DTO> dtos) {
         return success(${sikaEntityBodyName?uncap_first}Service.saveBatch(dtos));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody ${sikaEntityBodyName}Query query) {
        return success(${sikaEntityBodyName?uncap_first}Service.page(query));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody ${sikaEntityBodyName}Query query) {
        return success(${sikaEntityBodyName?uncap_first}Service.find(query));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody ${sikaEntityBodyName}Query query) {
        return success(${sikaEntityBodyName?uncap_first}Service.list(query));
    }
}
