package ${sikaPackage.ApiServiceImpl};

import ${sikaPackage.DTO}.${sikaEntityBodyName}DTO;
import ${sikaPackage.Service}.${sikaEntityBodyName}Service;
import ${sikaPackage.ApiService}.${sikaEntityBodyName}ApiService;
import com.cat.reconciliation.platform.infrastructure.interfaces.rpc.base.BaseApiServiceImpl;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * <p>
 * ${table.comment!} API服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@DubboService
public class ${sikaEntityBodyName}ApiServiceImpl extends BaseApiServiceImpl<${sikaPrimaryType}, ${sikaEntityBodyName}DTO, ${sikaEntityBodyName}Service> implements ${sikaEntityBodyName}ApiService {

}
