package ${package.QueryResponseBO};

import ${package.DTO}.${table.classBodyName}DTO;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.domain.${table.classBodyName}Domain;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>
 * ${table.comment!} 普通查询响应类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@Accessors(chain = true)
public class ${table.classBodyName}QueryResponseBO implements BaseStandardResponseBO<${table.classBodyName}DTO>, ${table.classBodyName}Domain {
    /**
     * 返回给页面的响应对象
     */
    private ${table.classBodyName}QueryResponse ${table.classBodyName?uncap_first};

    @Override
    public void build(${table.classBodyName}DTO ${table.classBodyName?uncap_first}) {
        this.${table.classBodyName?uncap_first} = convert().convertToQueryResponse(${table.classBodyName?uncap_first});
    }

    /**
     * <p>
     * 响应类 : 封装响应数据
     * </p>
     *
     * @author ${author}
     * @date ${cfg.date}
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class ${table.classBodyName}QueryResponse extends ${table.classBodyName}DTO {

    }
}