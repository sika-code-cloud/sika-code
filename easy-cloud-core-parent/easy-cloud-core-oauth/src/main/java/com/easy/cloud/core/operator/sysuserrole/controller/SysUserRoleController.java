package com.easy.cloud.core.operator.sysuserrole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysuserrole.pojo.dto.SysUserRoleDTO;
import com.easy.cloud.core.operator.sysuserrole.service.SysUserRoleService;

/**
 * 描述：控制转发类
 * 
 * @author THINK
 * @date 2018-05-30 16:23:59
 */
@RestController(value = "sysUserRoleController")
@RequestMapping(value = "sysUserRole")
public class SysUserRoleController extends EcBaseController {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@RequestMapping(value = "saveUserRole")
	public EcBaseServiceResult saveUserRole(@RequestBody SysUserRoleDTO sysUserRoleDTO) {
		return sysUserRoleService.save(sysUserRoleDTO);
	}
}
