package ${package.Entity};

import ${sikaPackage.Entity}.${sikaEntityBodyName}PO;
import ${sikaPackage.Mapper}.${sikaEntityBodyName}Mapper;
import com.sika.code.demo.domain.common.base.repository.BaseBizRepository;

/**
 * <p>
 * ${table.comment!} 持久化操作类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity} extends BaseBizRepository<${sikaEntityBodyName}PO${r","} ${sikaEntityBodyName}Mapper> {

}
