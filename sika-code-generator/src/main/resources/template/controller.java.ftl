package ${package.Entity};


import org.springframework.web.bind.annotation.RequestMapping;

import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Service}.${sikaEntityBodyName}Service;
import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sika.check.infrastructure.common.annotation.Log;
import com.sika.check.infrastructure.common.annotation.RepeatSubmit;
import com.sika.check.infrastructure.common.core.controller.BaseController;
import com.sika.check.infrastructure.common.core.domain.MpPageQuery;
import com.sika.check.infrastructure.common.core.domain.R;
import com.sika.check.infrastructure.common.core.page.TableDataInfo;
import com.sika.check.infrastructure.common.core.validate.AddGroup;
import com.sika.check.infrastructure.common.core.validate.EditGroup;
import com.sika.check.infrastructure.common.enums.BusinessType;
import com.sika.check.infrastructure.common.utils.poi.ExcelUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;
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
    @SaCheckPermission("${sikaEntityBodyName?uncap_first}:list")
    @GetMapping("/list")
    public TableDataInfo<${sikaEntityBodyName}DTO> list(${sikaEntityBodyName}Query query, MpPageQuery pageQuery) {
        return ${sikaEntityBodyName?uncap_first}Service.queryPageList(query, pageQuery);
    }

    /**
     * 导出${table.comment!}列表
     */
    @Log(title = "${table.comment!}", businessType = BusinessType.EXPORT)
    @SaCheckPermission("${sikaEntityBodyName?uncap_first}:export")
    @PostMapping("/export")
    public void export(${sikaEntityBodyName}Query query, HttpServletResponse response) {
    List<${sikaEntityBodyName}DTO> list = ${sikaEntityBodyName?uncap_first}Service.queryList(query);
        ExcelUtil.exportExcel(list, "${table.comment!}", ${sikaEntityBodyName}DTO.class, response);
    }

    /**
     * 获取${table.comment!}详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("${sikaEntityBodyName?uncap_first}:query")
    @GetMapping("/{id}")
    public R<${sikaEntityBodyName}DTO> getInfo(@NotNull(message = "主键不能为空") @PathVariable Long id) {
        return R.ok(${sikaEntityBodyName?uncap_first}Service.queryById(id));
    }

    /**
     * 新增${table.comment!}
     */
    @SaCheckPermission("${sikaEntityBodyName?uncap_first}:add")
    @Log(title = "${table.comment!}", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ${sikaEntityBodyName}DTO dto) {
        return toAjax(${sikaEntityBodyName?uncap_first}Service.insert(dto) ? 1 : 0);
    }

    /**
     * 修改${table.comment!}
     */
    @SaCheckPermission("${sikaEntityBodyName?uncap_first}:edit")
    @Log(title = "${table.comment!}", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ${sikaEntityBodyName}DTO bo) {
        return toAjax(${sikaEntityBodyName?uncap_first}Service.update(bo) ? 1 : 0);
    }

    /**
     * 删除${table.comment!}
     *
     * @param ids 主键串
     */
    @SaCheckPermission("${sikaEntityBodyName?uncap_first}:remove")
    @Log(title = "${table.comment!}", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ids) {
        return toAjax(${sikaEntityBodyName?uncap_first}Service.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
