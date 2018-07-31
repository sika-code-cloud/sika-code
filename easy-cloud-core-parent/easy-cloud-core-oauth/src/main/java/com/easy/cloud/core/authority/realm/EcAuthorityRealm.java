package com.easy.cloud.core.authority.realm;

import com.easy.cloud.core.authority.pojo.dto.EcAuthorityUserDTO;
import com.easy.cloud.core.authority.utils.EcAuthorityUtils;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthResourceDTO;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthUserDTO;
import com.easy.cloud.core.oauth.client.base.token.EcBaseOauthToken;
import com.easy.cloud.core.oauth.manager.EcOauthManager;
import com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * <p>
 * 授权realm
 * </p>
 *
 * @author daiqi
 * @date 2018/6/28 15:18
 */
public class EcAuthorityRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysResourceService sysResourceService;
    @Autowired
    private EcOauthManager oauthManager;

    @Override
    public boolean supports(AuthenticationToken token) {
        return super.supports(token) || token instanceof EcBaseOauthToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        EcAuthorityUserDTO<Long> authorityUserDTO = (EcAuthorityUserDTO) principals.getPrimaryPrincipal();

        List<SysRoleDTO> roleList = sysRoleService.findByUserId(authorityUserDTO.getAuthorityUserId());
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
        if (token instanceof EcBaseOauthToken) {
            return doGetAuthenticationInfoOauth2Token((EcBaseOauthToken) token);
        } else {
            return doGetAuthenticationInfoPassword(token);
        }
    }

    /**
     * <p>
     * 使用账号密码的方式登录
     * </p>
     *
     * @param token
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author daiqi
     * @date 2018/6/29 17:50
     */
    protected AuthenticationInfo doGetAuthenticationInfoPassword(AuthenticationToken token) {
// 获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        EcAuthorityUserDTO authorityUserDTO = sysUserService.findByUsername(username);
        // 账户冻结
        if (authorityUserDTO.getLocked() == 1) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(authorityUserDTO,
                authorityUserDTO.getPassword(),
                ByteSource.Util.bytes(authorityUserDTO.getSalt()),
                authorityUserDTO.getAuthCacheKey()
        );
        return authenticationInfo;
    }

    /**
     * <p>
     * 使用oauth2.0token授权模式登陆
     * </p>
     *
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author daiqi
     * @date 2018/6/29 17:49
     */
    protected AuthenticationInfo doGetAuthenticationInfoOauth2Token(EcBaseOauthToken oauth2Token) {
        EcBaseOauthResourceDTO<Object> oauthResourceDTO = oauthManager.getOauthResourceDTO(oauth2Token);
        EcAssert.verifyObjNull(oauthResourceDTO, "oauthResourceDTO");
        EcBaseOauthUserDTO oauthUserDTO = oauthResourceDTO.getTObj();
        SysUserDTO sysUserDTO = new SysUserDTO();
        sysUserDTO.setUsername(oauthUserDTO.getOpenid());
        sysUserDTO.setPassword(EcAuthorityUtils.encryptOfMD5(oauth2Token.getAuthCode(), oauthUserDTO.getOpenid()));
        sysUserDTO.setSalt(oauthUserDTO.getOpenid());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUserDTO,
                sysUserDTO.getPassword(),
                ByteSource.Util.bytes(sysUserDTO.getSalt()),
                sysUserDTO.getAuthCacheKey()
        );
        return authenticationInfo;
    }
}
