package ${package.Entity};

import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import com.sika.check.infrastructure.common.core.domain.MpPageQuery;
import com.sika.check.infrastructure.common.core.page.TableDataInfo;
import com.sika.code.core.base.service.BaseService;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity} {
    /**
     * 根据ID查询数据
     */
    ${sikaEntityBodyName} queryById(${sikaPrimaryType} id);

    /**
     * 查询分页列表数据
     */
    TableDataInfo<${sikaEntityBodyName}> queryPageList(${sikaEntityBodyName}Query query, MpPageQuery pageQuery);

    /**
     * 查询列表数据
     */
    List<${sikaEntityBodyName}> queryList(${sikaEntityBodyName}Query query);

    /**
     * 新增数据
     */
    Boolean insert(${sikaEntityBodyName} dto);

    /**
     * 修改数据
     */
    Boolean updateById(${sikaEntityBodyName} dto);

    /**
     * 批量删除数据
     */
    Boolean deleteWithValidByIds(Collection<${sikaPrimaryType}> ids, Boolean isValid);
}
