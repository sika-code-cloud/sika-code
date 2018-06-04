package com.easy.cloud.core.operator.sysuser.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;

/**
 * 描述：控制转发类
 * 
 * @author THINK
 * @date 2018-05-30 16:23:53
 */
@RestController(value = "sysUserController")
@RequestMapping(value = "sysUser")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false)
public class SysUserController extends EcBaseController {
	@Autowired
	private SysUserService sysUserService;

	@RequestMapping(value = "register")
	public EcBaseServiceResult register(@RequestBody SysUserDTO sysUserDTO) {
		return sysUserService.register(sysUserDTO);
	}

	@RequestMapping(value = "login")
	public EcBaseServiceResult login(@RequestBody SysUserDTO sysUserDTO) {
		return sysUserService.login(sysUserDTO);
	}

	@RequestMapping(value = "getCurrentUser")
	public EcBaseServiceResult getCurrentUser() {
		Subject subject = SecurityUtils.getSubject();
		return EcBaseServiceResult.newInstanceOfSucResult(subject.getPrincipal());
	}
}
