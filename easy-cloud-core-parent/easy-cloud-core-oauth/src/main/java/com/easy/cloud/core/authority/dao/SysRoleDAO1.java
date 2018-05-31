package com.easy.cloud.core.authority.dao;

import java.util.List;

import com.easy.cloud.core.authority.pojo.SysRole;
import com.easy.cloud.core.authority.pojo.UserInfo;
import com.easy.cloud.core.jdbc.base.dao.EcBaseDAO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public interface SysRoleDAO1 {

    List<SysRole> selectRoleByUser(UserInfo userInfo);
}
