package com.easy.cloud.core.authority.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.easy.cloud.core.authority.dao.SysRoleDAO1;
import com.easy.cloud.core.authority.pojo.SysRole;
import com.easy.cloud.core.authority.pojo.UserInfo;
import com.easy.cloud.core.authority.service.SysRoleService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {
	private SysRoleDAO1 sysRoleDAO1;
    @Override
    public List<SysRole> selectRoleByUser(UserInfo userInfo) throws Exception{
        return null;
    }
}
