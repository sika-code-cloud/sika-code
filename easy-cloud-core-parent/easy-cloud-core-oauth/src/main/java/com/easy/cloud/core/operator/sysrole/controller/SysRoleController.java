package com.easy.cloud.core.operator.sysrole.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;

/**
 * 描述：控制转发类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:25
 */
@RestController(value = "sysRoleController")
@RequestMapping(value = "sysRole")
public class SysRoleController extends EcBaseController {
	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(value = "saveRole")
	public EcBaseServiceResult saveRole(@RequestBody SysRoleDTO roleDTO) {
		return sysRoleService.save(roleDTO);
	}
	
	@RequiresRoles(value = "admin")
	@RequestMapping(value = "getCurrentUserRole")
	public EcBaseServiceResult getCurrentUserRole() {
		Subject subject = SecurityUtils.getSubject();
		SysUserDTO sysUserDTO = (SysUserDTO) subject.getSession().getAttribute("user");
		return EcBaseServiceResult.newInstanceOfSucResult(sysRoleService.findByUserId(sysUserDTO.getId()));
	}
}
