package ${package.Entity};

import ${sikaPackage.Repository}.${sikaEntityBodyName}Repository;
import ${sikaPackage.Service}.${sikaEntityBodyName}Service;
import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaPackage.Entity}.${sikaEntityBodyName};
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sika.check.infrastructure.common.core.domain.MpPageQuery;
import com.sika.check.infrastructure.common.core.page.TableDataInfo;
import com.sika.code.core.base.constant.BaseConstant;
import com.sika.code.core.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@RequiredArgsConstructor
public class ${entity} implements ${sikaEntityBodyName}Service {
    private final ${sikaEntityBodyName}Repository ${sikaEntityBodyName ? uncap_first}Repository;

    /**
     * 查询对账核心配置
     */
    @Override
    public ${sikaEntityBodyName}DTO queryById(Long id) {
        return ${sikaEntityBodyName ? uncap_first}Repository.selectById(id, ${sikaEntityBodyName}DTO.class);
    }

    /**
     * 查询对账核心配置列表
     */
    @Override
    public TableDataInfo<${sikaEntityBodyName}DTO> queryPageList(${sikaEntityBodyName}Query query, MpPageQuery pageQuery) {
        Page<${sikaEntityBodyName}DTO> result = ${sikaEntityBodyName ? uncap_first}Repository.selectPage(pageQuery.build(), query, ${sikaEntityBodyName}DTO.class);
        return TableDataInfo.build(result);
    }

    /**
     * 查询对账核心配置列表
     */
    @Override
    public List<${sikaEntityBodyName}DTO> queryList(${sikaEntityBodyName}Query query) {
        return ${sikaEntityBodyName ? uncap_first}Repository.list(query, ${sikaEntityBodyName}DTO.class);
    }

    /**
    * 新增对账核心配置
    */
    @Override
    public Boolean insert(${sikaEntityBodyName}DTO dto) {
        ${sikaEntityBodyName} add = BeanUtil.toBean(dto, ${sikaEntityBodyName}.class);
        validEntityBeforeSave(add);
        boolean flag = ${sikaEntityBodyName ? uncap_first}Repository.insert(add) > 0;
        if (flag) {
            dto.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改对账核心配置
     */
    @Override
    public Boolean update(${sikaEntityBodyName}DTO dto) {
        ${sikaEntityBodyName} update = BeanUtil.toBean(dto, ${sikaEntityBodyName}.class);
        validEntityBeforeSave(update);
        return ${sikaEntityBodyName ? uncap_first}Repository.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(${sikaEntityBodyName} entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除对账核心配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<${sikaPrimaryType}> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return ${sikaEntityBodyName ? uncap_first}Repository.deleteBatchIds(ids) > 0;
    }
}