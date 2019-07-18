package ${package.Domain};

import com.sika.code.standard.base.pojo.domain.BaseStandardDomain;
import com.sika.code.common.spring.SpringUtil;
import ${table.modulePackage}.business.${package.ModuleName}.convert.${table.classBodyName}Convert;
import ${table.modulePackage}.business.${package.ModuleName}.service.${table.classBodyName}Service;
import ${table.modulePackage}.business.${package.ModuleName}.logic.${table.classBodyName}Logic;

/**
 * <p>
 * ${table.comment!} 领域类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
public interface ${table.classBodyName}Domain extends BaseStandardDomain {
    /**
     * <p>
     * 返回当前模块的service
     * </p>
     *
     * @return ${table.classBodyName}Service
     * @author ${author}
     * @date ${cfg.date}
     */
    default ${table.classBodyName}Service service() {
        return getBean(${table.classBodyName}Service.class);
    }

    /**
     * <p>
     * 返回当前模块的Logic
     * </p>
     *
     * @return ${table.classBodyName}Logic
     * @author ${author}
     * @date ${cfg.date}
     */
    default ${table.classBodyName}Logic logic() {
        return getBean(${table.classBodyName}Logic.class);
    }

    /**
     * <p>
     * 获取当前模块的转化器对象
     * </p>
     *
     * @return ${table.classBodyName}Convert
     * @author ${author}
     * @date ${cfg.date}
     */
    default ${table.classBodyName}Convert convert() {
        return ${table.classBodyName}Convert.INSTANCE;
    }
}
