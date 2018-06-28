package com.easy.cloud.core.operator.sysroleresource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysroleresource.pojo.dto.SysRoleResourceDTO;
import com.easy.cloud.core.operator.sysroleresource.service.SysRoleResourceService;

/**
 * 描述：控制转发类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:33
 */
@RestController(value = "sysRoleResourceController")
@RequestMapping(value = "sysRoleResource")
public class SysRoleResourceController extends EcBaseController {
	@Autowired
	private SysRoleResourceService sysRoleResourceService;
	
	@RequestMapping(value = "saveRoleResource")
	public EcBaseServiceResult saveRoleResource(@RequestBody SysRoleResourceDTO sysRoleResourceDTO) {
		return sysRoleResourceService.saveSysRoleResource(sysRoleResourceDTO);
	}

	@RequestMapping(value = "updateSysRoleResource")
	public EcBaseServiceResult updateSysRoleResource(@RequestBody SysRoleResourceDTO sysRoleResourceDTO) {
		return sysRoleResourceService.updateSysRoleResource(sysRoleResourceDTO);
	}
}
