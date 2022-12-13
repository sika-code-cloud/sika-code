package ${package.Entity};

import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Service}.${sikaEntityBodyName}Service;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sika.check.infrastructure.common.annotation.Log;
import com.sika.check.infrastructure.common.annotation.RepeatSubmit;
import com.sika.check.infrastructure.common.core.controller.BaseController;
import com.sika.check.infrastructure.common.core.domain.R;
import com.sika.check.infrastructure.common.core.page.TableDataInfo;
import com.sika.check.infrastructure.common.core.validate.*;
import com.sika.check.infrastructure.common.enums.BusinessType;
import com.sika.check.infrastructure.common.utils.poi.ExcelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("${sikaEntityBodyName?uncap_first}")
public class ${entity} extends BaseController {

    private final ${sikaEntityBodyName}Service ${sikaEntityBodyName?uncap_first}Service;


    /**
     * 查询${table.comment!}列表
     */
    @SaCheckPermission("${sikaEntityBodyName}:page")
    @PostMapping("/page")
        public TableDataInfo<${sikaEntityBodyName}DTO> list(@Validated(PageGroup.class) @RequestBody ${sikaEntityBodyName}Query query) {
        return ${sikaEntityBodyName?uncap_first}Service.queryPageList(query);
    }

    /**
     * 导出${table.comment!}列表
     */
    @Log(title = "${table.comment!}", businessType = BusinessType.EXPORT)
    @SaCheckPermission("${sikaEntityBodyName}:export")
    @PostMapping("/export")
    public void export(@RequestBody ${sikaEntityBodyName}Query query, HttpServletResponse response) {
        List<${sikaEntityBodyName}DTO> list = ${sikaEntityBodyName?uncap_first}Service.queryList(query);
        ExcelUtil.exportExcel(list, "${table.comment!}", ${sikaEntityBodyName}DTO.class, response);
    }

    /**
     * 获取${table.comment!}详细信息
     *
     * @param query 主键
     */
    @SaCheckPermission("${sikaEntityBodyName}:get")
    @PostMapping("/get")
    public R<${sikaEntityBodyName}DTO> getInfo(@Validated(GetGroup.class) @RequestBody ${sikaEntityBodyName}Query query) {
        return R.ok(${sikaEntityBodyName?uncap_first}Service.queryById(query.getId()));
    }

    /**
     * 新增${table.comment!}
     */
    @SaCheckPermission("${sikaEntityBodyName}:add")
    @Log(title = "${table.comment!}", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("add")
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ${sikaEntityBodyName}DTO dto) {
        return toAjax(${sikaEntityBodyName?uncap_first}Service.insert(dto) ? 1 : 0);
    }

    /**
     * 修改${table.comment!}
     */
    @SaCheckPermission("${sikaEntityBodyName}:edit")
    @Log(title = "${table.comment!}", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PostMapping("edit")
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ${sikaEntityBodyName}DTO dto) {
        return toAjax(${sikaEntityBodyName?uncap_first}Service.update(dto) ? 1 : 0);
    }

    /**
     * 删除${table.comment!}
     *
     * @param dto 主键串
     */
    @SaCheckPermission("${sikaEntityBodyName}:remove")
    @Log(title = "${table.comment!}", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public R<Void> remove(@Validated(DeleteIdsGroup.class) @RequestBody ${sikaEntityBodyName}DTO dto) {
        return toAjax(${sikaEntityBodyName?uncap_first}Service.deleteWithValidByIds(dto.getIds(), true) ? 1 : 0);
    }
}
