package ${package.QueryRequestBO};

import ${package.Query}.${table.classBodyName}Query;
import com.sika.code.standard.base.pojo.bo.request.BaseStandardQueryRequestBO;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.domain.${table.classBodyName}Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!} 抽象查询请求类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class ${table.classBodyName}QueryRequestBO<ResponseBO extends BaseStandardResponseBO> extends BaseStandardQueryRequestBO<ResponseBO> implements ${table.classBodyName}Domain {

    @Data
    @Accessors(chain = true)
    @EqualsAndHashCode(callSuper = true)
    public static class ${table.classBodyName}QueryRequest extends ${table.classBodyName}Query {

    }
}