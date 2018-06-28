package com.easy.cloud.core.operator.sysroleresource.dao;

import com.easy.cloud.core.operator.sysroleresource.pojo.entity.SysRoleResourceEntity;
import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;
import com.easy.cloud.core.operator.sysroleresource.pojo.query.SysRoleResourceQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：数据处理接口
 *
 * @author THINK
 * @date 2018-05-30 16:24:33
 */
public interface SysRoleResourceDAO extends EcBaseDAO<SysRoleResourceEntity> {
    List<SysRoleResourceEntity> listByQuery(@Param(value = "query") SysRoleResourceQuery roleResourceQuery);
}
