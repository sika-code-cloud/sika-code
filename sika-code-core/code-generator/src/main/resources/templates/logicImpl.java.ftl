package ${package.LogicImpl};


import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}CommonQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}ListQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.save.${table.classBodyName}SaveBatchRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.update.${table.classBodyName}UpdateByIdRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}ListQueryResponseBO;
import ${table.modulePackage}.business.${package.ModuleName}.service.${table.classBodyName}Service;
import ${table.modulePackage}.business.${package.ModuleName}.logic.${table.classBodyName}Logic;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.query.${table.classBodyName}PageQueryRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.request.save.${table.classBodyName}SaveRequestBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}PageQueryResponseBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.query.${table.classBodyName}QueryResponseBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.save.${table.classBodyName}SaveResponseBO;
import ${table.modulePackage}.business.${package.ModuleName}.pojo.bo.response.update.${table.classBodyName}UpdateResponseBO;
import com.sika.code.standard.base.logic.BaseStandardLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p>
 * ${table.comment!} 逻辑实现类
 * </p>
 *
 * @author ${author}
 * @since ${cfg.date}
 */
@Component(value = "${table.classBodyName?uncap_first}Logic")
public class ${table.classBodyName}LogicImpl extends BaseStandardLogic implements ${table.classBodyName}Logic {
    @Autowired
    private ${table.serviceName} ${table.serviceName?uncap_first};

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.classBodyName}SaveResponseBO save(${table.classBodyName}SaveRequestBO request) {
        return request.execute();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.classBodyName}SaveResponseBO saveBatch(${table.classBodyName}SaveBatchRequestBO requestBO) {
        return requestBO.execute();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ${table.classBodyName}UpdateResponseBO updateById(${table.classBodyName}UpdateByIdRequestBO request) {
        return request.execute();
    }

    @Override
    public ${table.classBodyName}PageQueryResponseBO page(${table.classBodyName}PageQueryRequestBO request) {
        return request.execute();
    }

    @Override
    public ${table.classBodyName}QueryResponseBO find(${table.classBodyName}CommonQueryRequestBO request) {
        return request.execute();
    }

    @Override
    public ${table.classBodyName}ListQueryResponseBO list(${table.classBodyName}ListQueryRequestBO request) {
        return request.execute();
    }
}