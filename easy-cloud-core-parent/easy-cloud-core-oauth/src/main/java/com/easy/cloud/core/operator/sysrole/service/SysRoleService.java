package com.easy.cloud.core.operator.sysrole.service;

import java.util.List;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.pojo.entity.SysRoleEntity;

/**
 * 描述：服务接口
 * 
 * @author THINK
 * @date 2018-05-30 16:24:25
 */
public interface SysRoleService {
	public EcBaseServiceResult save(SysRoleDTO roleDTO);

	public List<SysRoleDTO> findByUserId(Long userId);
}
