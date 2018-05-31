package com.easy.cloud.core.authority.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.authority.dao.SysPermissionDAO1;
import com.easy.cloud.core.authority.pojo.SysPermission;
import com.easy.cloud.core.authority.pojo.UserInfo;
import com.easy.cloud.core.authority.service.SysPermissionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author huitu123
 * @since 2018-01-23
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {
	@Autowired
	private SysPermissionDAO1 sysPermissionDAO1;
	
    @Override
    public List<SysPermission> selectPermByUser(UserInfo userInfo) throws Exception {
        return null;
    }
}
