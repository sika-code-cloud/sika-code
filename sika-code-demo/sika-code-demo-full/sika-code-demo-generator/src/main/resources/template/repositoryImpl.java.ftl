package ${package.Entity};

import ${sikaPackage.Entity}.${sikaEntityBodyName}PO;
import ${sikaPackage.Mapper}.${sikaEntityBodyName}Mapper;
import ${sikaPackage.Repository}.${sikaEntityBodyName}Repository;
import  com.sika.code.demo.domain.common.base.repository.impl.BaseBizRepositoryImpl;
import org.springframework.stereotype.Repository;
import cn.hutool.core.lang.Assert;

/**
 * <p>
 * ${table.comment!} 持久化操作实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Repository
public class ${entity} extends BaseBizRepositoryImpl<${sikaEntityBodyName}PO${r","} ${sikaEntityBodyName}Mapper> implements ${sikaEntityBodyName}Repository {

}

