package ${package.Entity};

import ${sikaPackage.Entity}.${sikaEntityBodyName}PO;
import com.sika.code.db.repository.BaseRepository;

/**
 * <p>
 * ${table.comment!} 持久化操作类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity} extends BaseRepository<${sikaEntityBodyName}PO${r","} ${sikaPrimaryType}> {
    /**
     * 校验ID对应的协作器是否不存在-不存在抛出异常
     * @param id : 主键ID
     */
     void verify${sikaEntityBodyName}UnExistById(${sikaPrimaryType} id);
}
