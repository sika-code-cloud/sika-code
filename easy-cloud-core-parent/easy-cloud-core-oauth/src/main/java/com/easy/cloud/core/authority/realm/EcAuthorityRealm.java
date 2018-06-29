package com.easy.cloud.core.authority.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.easy.cloud.core.authority.token.EcOAuth2Token;
import com.easy.cloud.core.common.collections.utils.EcCollectionsUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
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
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.beans.factory.annotation.Autowired;

import com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;

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
        if (token instanceof EcOAuth2Token) {
            return doGetAuthenticationInfoOauth2Token(token);
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
        SysUserDTO sysUserDTO = sysUserService.findByUsername(username);
        // 账户冻结
        if (sysUserDTO.getLocked() == 1) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUserDTO,
                sysUserDTO.getPassword(),
                ByteSource.Util.bytes(sysUserDTO.getSalt()),
                sysUserDTO.getAuthCacheKey()
        );
        return authenticationInfo;
    }

    /**
     * <p>
     * 使用oauth2.0token授权模式登陆
     * </p>
     *
     * @param token
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author daiqi
     * @date 2018/6/29 17:49
     */
    protected AuthenticationInfo doGetAuthenticationInfoOauth2Token(AuthenticationToken token) {
        EcOAuth2Token oAuth2Token = (EcOAuth2Token) token;
        String code = oAuth2Token.getAuthCode();
        SysUserDTO sysUserDTO = extractUsername(code);

        SimpleAuthenticationInfo authenticationInfo =
                new SimpleAuthenticationInfo(sysUserDTO, code, getName());
        return authenticationInfo;
    }

    private SysUserDTO extractUsername(String code) {
        String accessTokenUrl = "";
        String clientId = "";
        String clientSecret = "";
        String redirectUrl = "";
        String userInfoUrl = "";
        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(accessTokenUrl)
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(clientId)
                    .setClientSecret(clientSecret)
                    .setCode(code)
                    .setRedirectURI(redirectUrl)
                    .buildQueryMessage();

            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);

            String accessToken = oAuthResponse.getAccessToken();
            Long expiresIn = oAuthResponse.getExpiresIn();

            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl)
                    .setAccessToken(accessToken).buildQueryMessage();

            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
            return EcJSONUtils.parseObject(resourceResponse.getBody(), SysUserDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException(e);
        }
    }
}
