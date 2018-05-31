package com.easy.cloud.core.authority.realm;

import java.util.List;

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

import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;

public class EcAuthorityRealm extends AuthorizingRealm {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		SysUserDTO sysUserDTO = (SysUserDTO) principals.getPrimaryPrincipal();
		List<SysRoleDTO> roleList = sysRoleService.findByUserId(sysUserDTO.getId());
		for (SysRoleDTO role : roleList) {
			authorizationInfo.addRole(role.getName());
		}
		return authorizationInfo;
	}

	/* 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取用户的输入的账号.
		String username = (String) token.getPrincipal();
		SysUserDTO sysUserDTO = sysUserService.findByUsername(username);
		if (sysUserDTO.getLocked() == 1) { // 账户冻结
			throw new LockedAccountException();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUserDTO, // 用户名
				sysUserDTO.getPassword(), // 密码
				ByteSource.Util.bytes(sysUserDTO.getSalt()), 
				getName() // realm name
		);
		return authenticationInfo;
	}
}
