package ${package.Entity};

import ${sikaPackage.Entity}.${sikaEntityBodyName};
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaPackage.Mapper}.${sikaEntityBodyName}Mapper;
import com.sika.check.domain.common.base.repository.BaseCheckRepository;
/**
 * <p>
 * ${table.comment!} 持久化操作类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity} extends BaseCheckRepository<${sikaEntityBodyName}${r","} ${sikaEntityBodyName}Query${r","} ${sikaEntityBodyName}Mapper> {

}
