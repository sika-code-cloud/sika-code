package com.easy.cloud.core.operator.sysresource.dao;

import com.easy.cloud.core.operator.sysresource.pojo.entity.SysResourceEntity;
import com.easy.cloud.core.operator.sysresource.pojo.query.SysResourceQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;

/**
 * 描述：数据处理接口
 * 
 * @author THINK
 * @date 2018-05-30 16:24:17
 */
public interface SysResourceDAO extends EcBaseDAO<SysResourceEntity> {
	List<SysResourceEntity> listRoleResourceByQuery(@Param(value = "query") SysResourceQuery query);
	List<SysResourceEntity> listByQuery(@Param(value = "query") SysResourceQuery query);
}
