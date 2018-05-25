package com.easy.cloud.core.authority.service;

import java.util.List;

import com.easy.cloud.core.authority.pojo.SysRole;
import com.easy.cloud.core.authority.pojo.UserInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
public interface SysRoleService {

    List<SysRole> selectRoleByUser(UserInfo userInfo) throws Exception;
}
