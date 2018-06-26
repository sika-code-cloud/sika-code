package com.easy.cloud.core.operator.sysuser.controller;

import com.easy.cloud.core.authority.manager.EcAuthorityManager;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;
import com.easy.cloud.core.operator.sysuser.pojo.query.SysUserQuery;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
 * @author daiqi
 * @date 2018-05-30 16:23:53
 */
@RestController(value = "sysUserController")
@RequestMapping(value = "sysUser")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false)
public class SysUserController extends EcBaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private EcAuthorityManager authorityManager;

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

    @RequiresPermissions(value = "user:update")
    @RequestMapping(value = "updateAppointUser")
    public EcBaseServiceResult updateAppointUser(@RequestBody SysUserDTO sysUserDTO) {
        return sysUserService.updateAppointUser(sysUserDTO);
    }

    @RequiresPermissions(value = "user:view")
    @RequestMapping(value = "detailByUsername")
    public EcBaseServiceResult detailByUsername(@RequestBody SysUserQuery sysUserQuery) {
        return sysUserService.detailByUsername(sysUserQuery);
    }

    /**
     * <p>
     * 获取当前用户的权限列表信息
     * </p>
     *
     * @param
     * @return com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult
     * @author daiqi
     * @date 2018/6/25 15:39
     */
    @RequestMapping(value = "listPermissionOfCurrentUser")
    public EcBaseServiceResult listPermissionOfCurrentUser() {
        return sysResourceService.listPermissionOfCurrentUser();
    }

    @RequestMapping(value = "clearCachedAuthorizationInfo")
    public EcBaseServiceResult clearCachedAuthorizationInfo(@RequestBody SysUserDTO sysUserDTO) {
        SysUserDTO sysUserDTOFromTable = sysUserService.findByUsername(sysUserDTO.getUsername());
        authorityManager.clearAuthorizationInfo(sysUserDTOFromTable);
        return EcBaseServiceResult.newInstanceOfSucResult(sysUserDTOFromTable);
    }
}
