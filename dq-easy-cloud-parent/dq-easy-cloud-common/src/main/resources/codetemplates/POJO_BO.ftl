package ${package_name}.model;
import com.evada.inno.common.domain.BaseModel;
import com.evada.inno.common.listener.ICreateListenable;
import com.evada.inno.common.listener.IDeleteListenable;
import com.evada.inno.common.listener.IModifyListenable;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import java.util.Date;

/**
 * 描述：${table_annotation}模型
 * @author ${author}
 * @date ${date}
 */
@Entity
@Table(name="${table_name_small}")
@Where(clause = "status > '0'")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class ${table_name} extends BaseModel implements ICreateListenable,IModifyListenable,IDeleteListenable {

<#if model_column?exists>
    <#list model_column as model>
    /** ${model.columnComment} */
    @Column(name = "${model.columnName}",columnDefinition = "${model.columnType}")
    private ${model.columnType} ${model.changeColumnName?uncap_first};
    </#list>
</#if>

<#if model_column?exists>
	<#list model_column as model>
    public ${model.columnType} get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(${model.columnType} ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }
    
	</#list>
</#if>

}