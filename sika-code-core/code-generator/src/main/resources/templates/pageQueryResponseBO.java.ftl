package ${package.PageQueryResponseBO};

import com.sika.code.database.common.Page;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.domain.${table.classBodyName}Domain;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.dto.${table.classBodyName}DTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!} 分页查询响应类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@Accessors(chain = true)
public class ${table.classBodyName}PageQueryResponseBO implements BaseStandardResponseBO<Page<${table.classBodyName}DTO>>, ${table.classBodyName}Domain {

    /**
    * 分页响应对象
    */
    private Page<${table.classBodyName}PageQueryResponse> page;

    @Override
    public void build(Page<${table.classBodyName}DTO> page) {
        this.page = new Page<>(page, convert().convertToPageQueryResponses(page.getList()));
    }

    /**
    * <p>
        * 响应类 封装响应数据
        * </p>
    *
    * @author ${author}
    * @date ${cfg.date}
    */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class ${table.classBodyName}PageQueryResponse extends ${table.classBodyName}DTO {

    }
}