package ${package.CommonQueryRequestBO};

import ${package.Query}.${table.classBodyName}Query;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.${table.classBodyName}QueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}QueryResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!} 普通查询请求类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ${table.classBodyName}CommonQueryRequestBO extends ${table.classBodyName}QueryRequestBO<${table.classBodyName}QueryResponseBO> {
    /**
     * 查询请求对象
     */
    private ${table.classBodyName}CommonQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected ${table.classBodyName}QueryResponseBO doExecute() {
        return newResponseBO(this, service().find(queryRequest));
    }

    @Override
    public Class<${table.classBodyName}QueryResponseBO> responseClass() {
        return ${table.classBodyName}QueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class ${table.classBodyName}CommonQueryRequest extends ${table.classBodyName}QueryRequest {

    }
}