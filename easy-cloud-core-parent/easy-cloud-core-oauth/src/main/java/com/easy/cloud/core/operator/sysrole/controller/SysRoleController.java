package com.easy.cloud.core.operator.sysrole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;

/**
 * 描述：控制转发类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:25
 */
@RestController(value = "sysRoleController")
public class SysRoleController extends EcBaseController {
	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(value = "saveRole")
	public EcBaseServiceResult saveRole(@RequestBody SysRoleDTO roleDTO) {
		return sysRoleService.save(roleDTO);
	}
}
