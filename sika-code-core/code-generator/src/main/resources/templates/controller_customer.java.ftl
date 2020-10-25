package ${package.Controller};

import com.sika.code.result.Result;
import com.sika.code.standard.base.controller.BaseStandardController;
import ${table.modulePackage}.business.${package.ModuleName}.logic.${table.classBodyName}Logic;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}CommonQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}ListQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}PageQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.save.${table.classBodyName}SaveBatchRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.save.${table.classBodyName}SaveRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.update.${table.classBodyName}UpdateByIdRequestBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@RestController(value = "${table.controllerName?uncap_first}")
@RequestMapping("${table.nameRemovePrefix}")
public class ${table.controllerName} extends BaseStandardController {
    @Autowired
    private ${table.classBodyName}Logic ${table.classBodyName?uncap_first}Logic;

    @RequestMapping(value = "save")
    public Result save(@RequestBody ${table.classBodyName}SaveRequestBO request) {
        return super.generateResult(${table.classBodyName?uncap_first}Logic.save(request));
    }

    @RequestMapping(value = "save_batch")
    public Result saveBatch(@RequestBody ${table.classBodyName}SaveBatchRequestBO request) {
        return super.generateResult(${table.classBodyName?uncap_first}Logic.saveBatch(request));
    }

    @RequestMapping(value = "update_by_id")
    public Result updateById(@RequestBody ${table.classBodyName}UpdateByIdRequestBO request) {
        return super.generateResult(${table.classBodyName?uncap_first}Logic.updateById(request));
    }

    @RequestMapping(value = "page")
    public Result page(@RequestBody ${table.classBodyName}PageQueryRequestBO request) {
        return super.generateResult(${table.classBodyName?uncap_first}Logic.page(request));
    }

    @RequestMapping(value = "find")
    public Result find(@RequestBody ${table.classBodyName}CommonQueryRequestBO request) {
        return super.generateResult(${table.classBodyName?uncap_first}Logic.find(request));
    }

    @RequestMapping(value = "list")
    public Result list(@RequestBody ${table.classBodyName}ListQueryRequestBO request) {
        return super.generateResult(${table.classBodyName?uncap_first}Logic.list(request));
    }
}
