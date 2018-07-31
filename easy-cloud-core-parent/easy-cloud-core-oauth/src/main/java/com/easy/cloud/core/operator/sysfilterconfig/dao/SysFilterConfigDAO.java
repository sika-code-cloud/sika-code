package com.easy.cloud.core.operator.sysfilterconfig.dao;

import com.easy.cloud.core.operator.sysfilterconfig.pojo.entity.SysFilterConfigEntity;
import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.query.SysFilterConfigQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：数据处理接口
 *
 * @author daiqi
 * @date 2018-06-25 16:36:55
 */
public interface SysFilterConfigDAO extends EcBaseDAO<SysFilterConfigEntity> {
    /**
     * <p>
     * 根据查询条件获取列表信息
     * </p>
     *
     * @param query
     * @return java.util.List<com.easy.cloud.core.operator.sysfilterconfig.pojo.entity.SysFilterConfigEntity>
     * @author daiqi
     * @date 2018/6/29 16:43
     */
    List<SysFilterConfigEntity> listByQuery(@Param(value = "query") SysFilterConfigQuery query);
    /**
     * <p>
     * 根据查询条件删除数据列表信息---逻辑删
     * </p>
     *
     * @param query
     * @return java.util.List<com.easy.cloud.core.operator.sysfilterconfig.pojo.entity.SysFilterConfigEntity>
     * @author daiqi
     * @date 2018/6/29 16:43
     */
    int deleteByQuery(@Param(value = "query") SysFilterConfigQuery query);
}
