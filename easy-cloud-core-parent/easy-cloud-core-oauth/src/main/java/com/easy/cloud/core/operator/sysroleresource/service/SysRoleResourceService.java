package com.easy.cloud.core.operator.sysroleresource.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysroleresource.pojo.dto.SysRoleResourceDTO;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-05-30 16:24:33
 */
public interface SysRoleResourceService {
	EcBaseServiceResult save(SysRoleResourceDTO roleDTO);
}
