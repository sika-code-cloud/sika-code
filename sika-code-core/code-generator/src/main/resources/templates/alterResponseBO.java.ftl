package ${package.AlterResponseBO};

import ${table.modulePackage}.business.${package.ModuleName}.pojo.domain.${table.classBodyName}Domain;
import com.sika.code.standard.base.pojo.bo.response.BaseStandardResponseBO;;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * ${table.comment!} 修改响应抽象类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Data
@Accessors(chain = true)
public abstract class ${table.classBodyName}AlterResponseBO implements BaseStandardResponseBO<Boolean>, ${table.classBodyName}Domain {
    /**
     * 操作成功的标志
     */
    protected Boolean success;

    @Override
    public void build(Boolean success) {
        this.success = success;
    }
}