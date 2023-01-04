package ${package.Entity};

import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import com.sika.check.infrastructure.common.core.page.TableDataInfo;
import java.util.List;
import java.util.Collection;

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
    ${sikaEntityBodyName}DTO queryById(${sikaPrimaryType} id);

    /**
     * 查询分页列表数据
     */
    TableDataInfo<${sikaEntityBodyName}DTO> queryPageList(${sikaEntityBodyName}Query query);

    /**
     * 查询列表数据
     */
    List<${sikaEntityBodyName}DTO> queryList(${sikaEntityBodyName}Query query);

    /**
     * 新增数据
     */
    Boolean insert(${sikaEntityBodyName}DTO dto);

    /**
     * 修改数据
     */
    Boolean update(${sikaEntityBodyName}DTO dto);

    /**
     * 批量删除数据
     */
    Boolean deleteWithValidByIds(Collection<${sikaPrimaryType}> ids, Boolean isValid);
}
