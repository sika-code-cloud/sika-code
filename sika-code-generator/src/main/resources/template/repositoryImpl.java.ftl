package ${package.Entity};

import ${sikaPackage.Entity}.${sikaEntityBodyName};
import ${sikaPackage.Mapper}.${sikaEntityBodyName}Mapper;
import ${sikaPackage.Query}.${sikaEntityBodyName}Query;
import ${sikaPackage.Repository}.${sikaEntityBodyName}Repository;
import com.sika.check.domain.common.base.repository.impl.BaseCheckRepositoryImpl;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * ${table.comment!} 持久化操作实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
@RequiredArgsConstructor
public class ${entity} extends BaseCheckRepositoryImpl<${sikaEntityBodyName}${r","} ${sikaEntityBodyName}Query${r","} ${sikaEntityBodyName}Mapper> implements ${sikaEntityBodyName}Repository {

}

