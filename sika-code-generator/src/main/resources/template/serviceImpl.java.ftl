package ${package.Entity};

import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.PO}.${sikaEntityBodyName}PO;
import ${sikaPackage.Repository}.${sikaEntityBodyName}Repository;
import ${sikaPackage.Service}.${sikaEntityBodyName}Service;
import com.sika.code.core.base.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
public class ${entity} extends BaseServiceImpl<${sikaPrimaryType}, ${sikaEntityBodyName}PO, ${sikaEntityBodyName}DTO, ${sikaEntityBodyName}Repository> implements ${sikaEntityBodyName}Service {

}
