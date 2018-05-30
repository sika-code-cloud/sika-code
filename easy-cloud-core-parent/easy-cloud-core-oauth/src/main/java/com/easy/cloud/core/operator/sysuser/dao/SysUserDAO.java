package com.easy.cloud.core.operator.sysuser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;
import com.easy.cloud.core.operator.sysuser.pojo.entity.SysUserEntity;
import com.easy.cloud.core.operator.sysuser.pojo.query.SysUserQuery;

/**
 * 描述：数据处理接口
 * 
 * @author THINK
 * @date 2018-05-30 16:23:53
 */
public interface SysUserDAO extends EcBaseDAO<SysUserEntity> {
	List<SysUserEntity> listByQuery(@Param(value = "query") SysUserQuery query);
}
