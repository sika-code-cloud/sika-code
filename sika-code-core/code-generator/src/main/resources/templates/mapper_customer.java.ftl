package ${package.Mapper};

import ${package.Entity}.${entity};
import org.springframework.stereotype.Repository;
import com.sika.code.standard.base.basemapper.BaseStandardMapper;

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
@Repository
public interface ${table.mapperName} extends BaseStandardMapper<${entity}> {

}
</#if>
