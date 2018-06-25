package com.easy.cloud.core.operator.sysrole.controller;

import com.easy.cloud.core.authority.manager.EcAuthorityManager;
import com.easy.cloud.core.authority.utils.EcAuthorityUtils;
import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private EcAuthorityManager authorityManager;

    @RequestMapping(value = "saveRole")
    public EcBaseServiceResult saveRole(@RequestBody SysRoleDTO roleDTO) {
        return sysRoleService.save(roleDTO);
    }

    @RequestMapping(value = "getCurrentUserRole")
    public EcBaseServiceResult getCurrentUserRole() {
        Subject subject = SecurityUtils.getSubject();
        SysUserDTO sysUserDTO = (SysUserDTO) subject.getPrincipal();
        return EcBaseServiceResult.newInstanceOfSucResult(sysRoleService.findByUserId(sysUserDTO.getId()));
    }

    @RequestMapping(value = "clearCachedAuthorizationInfo")
    public EcBaseServiceResult clearCachedAuthorizationInfo(@RequestBody SysUserDTO sysUserDTO) {
        SysUserDTO sysUserDTOFromTable = sysUserService.findByUsername(sysUserDTO.getUsername());
        authorityManager.clearAuthorizationInfo(sysUserDTOFromTable);
        return EcBaseServiceResult.newInstanceOfSucResult(sysUserDTOFromTable);
    }

}
