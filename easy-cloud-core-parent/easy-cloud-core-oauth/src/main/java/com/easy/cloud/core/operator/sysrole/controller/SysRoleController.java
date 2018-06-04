package com.easy.cloud.core.operator.sysrole.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.authority.realm.EcAuthorityRealm;
import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;

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
	@Autowired
	private SysResourceService sysResourceService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private EcAuthorityRealm authorizationInfo;
	@RequestMapping(value = "saveRole")
	public EcBaseServiceResult saveRole(@RequestBody SysRoleDTO roleDTO) {
		return sysRoleService.save(roleDTO);
	}
	
	@RequiresRoles(value = "guest")
	@RequestMapping(value = "getCurrentUserRole")
	public EcBaseServiceResult getCurrentUserRole() {
		Subject subject = SecurityUtils.getSubject();
		SysUserDTO sysUserDTO = (SysUserDTO) subject.getPrincipal();
		return EcBaseServiceResult.newInstanceOfSucResult(sysRoleService.findByUserId(sysUserDTO.getId()));
	}
	
	@RequestMapping(value = "getCurrentUserPermission")
	public EcBaseServiceResult getCurrentUserPermission() {
		Subject subject = SecurityUtils.getSubject();
		SysUserDTO sysUserDTO = (SysUserDTO) subject.getPrincipal();
		List<SysRoleDTO> roles = sysRoleService.findByUserId(sysUserDTO.getId());
		List<Integer> roleNos = new ArrayList<>();
		for (SysRoleDTO roleDTO : roles) {
			roleNos.add(roleDTO.getRoleNo());
		}
		return EcBaseServiceResult.newInstanceOfSucResult(sysResourceService.findByRoleNos(roleNos));
	}
	
	@RequestMapping(value = "clearCachedAuthorizationInfo")
	public EcBaseServiceResult clearCachedAuthorizationInfo(@RequestBody SysUserDTO sysUserDTO) {
        Subject subject = SecurityUtils.getSubject();   
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();   
        SysUserDTO sysUserDTO1 = sysUserService.findByUsername(sysUserDTO.getUsername());
        //第一个参数为用户名,第二个参数为realmName,test想要操作权限的用户   
        SimplePrincipalCollection principals = new SimplePrincipalCollection(sysUserDTO1,realmName);   
        subject.runAs(principals);   
        System.out.println(EcJSONUtils.toJSONString(subject.getPrincipals()));
        authorizationInfo.getAuthorizationCache().remove(subject.getPrincipals());   
        authorizationInfo.getAuthorizationCache().remove(sysUserDTO.getUsername());   
        subject.releaseRunAs();  
		return EcBaseServiceResult.newInstanceOfSucResult(subject.getPrincipals());
	}
	
}
