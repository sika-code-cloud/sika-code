package com.easy.cloud.core.operator.sysuserrole.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysuserrole.pojo.dto.SysUserRoleDTO;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-05-30 16:23:59
 */
public interface SysUserRoleService {
	public EcBaseServiceResult save(SysUserRoleDTO sysUserRoleDTO);
}
