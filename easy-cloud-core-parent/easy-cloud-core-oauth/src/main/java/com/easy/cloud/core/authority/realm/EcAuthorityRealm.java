package com.easy.cloud.core.authority.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;

public class EcAuthorityRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourceService sysResourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        SysUserDTO sysUserDTO = (SysUserDTO) principals.getPrimaryPrincipal();
        List<SysRoleDTO> roleList = sysRoleService.findByUserId(sysUserDTO.getId());
        Set<Integer> roleNos = new HashSet<>();
        for (SysRoleDTO role : roleList) {
            authorizationInfo.addRole(role.getName());
            roleNos.add(role.getRoleNo());
        }
        List<SysResourceDTO> roleResourceDTOs = sysResourceService.findByRoleNos(new ArrayList<>(roleNos));
        for (SysResourceDTO resourceDTO : roleResourceDTOs) {
            authorizationInfo.addStringPermission(resourceDTO.getPermission());
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        SysUserDTO sysUserDTO = sysUserService.findByUsername(username);
        // 账户冻结
        if (sysUserDTO.getLocked() == 1) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUserDTO,
                sysUserDTO.getPassword(),
                ByteSource.Util.bytes(sysUserDTO.getSalt()),
                getName()
        );
        return authenticationInfo;
    }
}
