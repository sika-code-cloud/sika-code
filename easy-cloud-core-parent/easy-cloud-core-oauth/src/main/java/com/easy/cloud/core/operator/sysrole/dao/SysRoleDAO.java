package com.easy.cloud.core.operator.sysrole.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;
import com.easy.cloud.core.operator.sysrole.pojo.entity.SysRoleEntity;
import com.easy.cloud.core.operator.sysrole.pojo.query.SysRoleQuery;

/**
 * 描述：数据处理接口
 * 
 * @author THINK
 * @date 2018-05-30 16:24:25
 */
public interface SysRoleDAO extends EcBaseDAO<SysRoleEntity> {
	List<SysRoleEntity> listByQuery(@Param(value = "query") SysRoleQuery query);
}
