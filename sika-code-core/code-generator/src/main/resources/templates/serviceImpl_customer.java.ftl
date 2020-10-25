package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.DTO}.${table.classBodyName}DTO;
import ${package.Query}.${table.classBodyName}Query;
import ${package.Service}.${table.serviceName};
import ${table.modulePackage}.business.${package.ModuleName}.convert.${table.classBodyName}Convert;

import com.sika.code.standard.base.convert.BaseConvert;
import com.sika.code.standard.base.service.impl.BaseStandardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Service(value = "${table.serviceName?uncap_first}")
public class ${table.classBodyName}ServiceImpl extends BaseStandardServiceImpl<${table.classBodyName}Mapper, ${table.classBodyName}Entity, ${table.classBodyName}DTO> implements ${table.classBodyName}Service {
    @Autowired
    private ${table.classBodyName}Mapper creditQuotaUsageTemplateMapper;


    @Override
    protected BaseConvert<${table.classBodyName}Entity, ${table.classBodyName}DTO> convert() {
        return ${table.classBodyName}Convert.INSTANCE;
    }
}

