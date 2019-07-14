package ${package.Logic};

import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}CommonQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}ListQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}PageQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.save.${table.classBodyName}SaveBatchRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.save.${table.classBodyName}SaveRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.update.${table.classBodyName}UpdateByIdRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}ListQueryResponseBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}PageQueryResponseBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}QueryResponseBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.save.${table.classBodyName}SaveResponseBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.update.${table.classBodyName}UpdateResponseBO;

/**
 * <p>
 * ${table.comment!} 逻辑类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
public interface ${table.classBodyName}Logic {
    /**
     * <p>
     * 保存普通字段的数据
     * </p>
     *
     * @param requestBO : 保存请求业务对象
     * @return ${table.classBodyName}SaveResponseBO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}SaveResponseBO save(${table.classBodyName}SaveRequestBO requestBO);

    /**
     * <p>
     * 批量保存数据
     * </p>
     *
     * @param requestBO : 批量保存请求业务对象
     * @return ${table.classBodyName}SaveResponseBO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}SaveResponseBO saveBatch(${table.classBodyName}SaveBatchRequestBO requestBO);

    /**
     * <p>
     * 更新普通字段的数据
     * </p>
     *
     * @param requestBO : 更新请求业务对象
     * @return ${table.classBodyName}UpdateResponseBO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}UpdateResponseBO updateById(${table.classBodyName}UpdateByIdRequestBO requestBO);

    /**
     * <p>
     * 根据分页查询条件查询分页数据
     * </p>
     *
     * @param requestBO : 分页查询请求对象
     * @return ${table.classBodyName}PageQueryResponseBO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}PageQueryResponseBO page(${table.classBodyName}PageQueryRequestBO requestBO);

    /**
     * <p>
     * 根据查询条件获取详情数据
     * </p>
     *
     * @param requestBO : 查询请求业务对象
     * @return ${table.classBodyName}QueryResponseBO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}QueryResponseBO find(${table.classBodyName}CommonQueryRequestBO requestBO);

    /**
     * <p>
     * 根据查询条件获取列表数据
     * </p>
     *
     * @param requestBO : 查询请求业务对象
     * @return ${table.classBodyName}ListQueryResponseBO
     * @author ${author}
     * @date ${cfg.date}
     */
    ${table.classBodyName}ListQueryResponseBO list(${table.classBodyName}ListQueryRequestBO requestBO);
}
