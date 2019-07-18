package ${package.PageQueryRequestBO};

import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.${table.classBodyName}QueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}PageQueryResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * <p>
 * ${table.comment!} 分页查询请求类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ${table.classBodyName}PageQueryRequestBO extends ${table.classBodyName}QueryRequestBO<${table.classBodyName}PageQueryResponseBO> {
    /**
     * 查询请求对象
     */
    private ${table.classBodyName}PageQueryRequest queryRequest;

    @Override
    protected void init() {

    }

    @Override
    protected void verify() {

    }

    @Override
    protected ${table.classBodyName}PageQueryResponseBO doExecute() {
        return newResponseBO(this, service().page(queryRequest));
    }

    @Override
    public Class<${table.classBodyName}PageQueryResponseBO> responseClass() {
        return ${table.classBodyName}PageQueryResponseBO.class;
    }

    @Data
    @Accessors(chain = true)
    public static class ${table.classBodyName}PageQueryRequest extends ${table.classBodyName}QueryRequest {

    }
}