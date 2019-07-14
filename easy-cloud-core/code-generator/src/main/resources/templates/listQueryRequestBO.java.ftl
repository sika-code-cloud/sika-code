package ${package.ListQueryRequestBO};

import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.${table.classBodyName}QueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}ListQueryResponseBO;
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
public class ${table.classBodyName}ListQueryRequestBO extends ${table.classBodyName}QueryRequestBO<${table.classBodyName}ListQueryResponseBO> {
    /**
    * 查询请求对象
    */
    private ${table.classBodyName}ListQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected ${table.classBodyName}ListQueryResponseBO doExecute() {
    return newResponseBO(this, service().list(queryRequest));
    }

    @Override
    public Class<${table.classBodyName}ListQueryResponseBO> responseClass() {
        return ${table.classBodyName}ListQueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class ${table.classBodyName}ListQueryRequest extends ${table.classBodyName}QueryRequest {

    }
}