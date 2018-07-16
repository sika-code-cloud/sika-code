package com.easy.cloud.core.authority.realm;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.authority.utils.EcAuthorityUtils;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseOauthUserDTO;
import com.easy.cloud.core.oauth.authorize.base.request.builder.EcBaseResourceRequestBuilder;
import com.easy.cloud.core.oauth.authorize.base.request.builder.EcBaseTokenRequestBuilder;
import com.easy.cloud.core.oauth.authorize.base.response.resource.EcBaseOauthTokenResponse;
import com.easy.cloud.core.oauth.authorize.base.response.token.EcBaseOauthResourceResponse;
import com.easy.cloud.core.oauth.authorize.base.token.EcBaseOauthToken;
import com.easy.cloud.core.oauth.authorize.service.EcOauthService;
import com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;
import com.easy.cloud.core.operator.sysrole.pojo.dto.SysRoleDTO;
import com.easy.cloud.core.operator.sysrole.service.SysRoleService;
import com.easy.cloud.core.operator.sysuser.pojo.dto.SysUserDTO;
import com.easy.cloud.core.operator.sysuser.service.SysUserService;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.common.OAuth;
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
    private EcOauthService oauthService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return super.supports(token) || token instanceof EcBaseOauthToken;
    }

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
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @author daiqi
     * @date 2018/6/29 17:49
     */
    protected AuthenticationInfo doGetAuthenticationInfoOauth2Token(EcBaseOauthToken oauth2Token) {
        EcBaseOauthUserDTO oauthUserDTO = getOauthResourceDTO(oauth2Token);
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

    /**
     * <p>
     * 获取授权资源数据传输对象
     * </p>
     *
     * @param oauth2Token
     * @return com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseOauthUserDTO
     * @author daiqi
     * @date 2018/7/16 16:07
     */
    private EcBaseOauthUserDTO getOauthResourceDTO(EcBaseOauthToken oauth2Token) {
        try {
            // 授权请求客户端
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            // 获取授权token
            Map<String, Object> oAuthResponseParam = getAccessToken(oAuthClient, oauth2Token);
            // 获取资源请求构建者
            EcBaseResourceRequestBuilder resourceRequestBuilder = oauth2Token.getResourceRequestBuilder();
            // 构建客户端请求参数
            OAuthClientRequest resourceRequest = resourceRequestBuilder.buildClientRequest(oAuthResponseParam);
            // 执行请求资源
            EcBaseOauthResourceResponse resourceResponse = oAuthClient.resource(resourceRequest, OAuth.HttpMethod.GET, resourceRequestBuilder.getResourceResponseClass());
            // 从资源响应对象中获取资源数据传输对象
            EcBaseOauthUserDTO oauthUserDTO = resourceResponse.getResourceObj(resourceResponse.getResourceDTOClass());
            System.out.println("=======用户数据====" + JSONObject.toJSONString(oauthUserDTO));
            return oauthUserDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException(e);
        }
    }

    private Map<String, Object> getAccessToken(OAuthClient oAuthClient, EcBaseOauthToken oauth2Token) throws Exception {
        Map<String, Object> accessToken = new HashMap<>(oauth2Token.getResourceRequestParam());
        EcBaseAccessTokenDTO localAccessToken = oauthService.getAccessToken(oauth2Token.getAccessTokenChannel());
        if (EcBaseUtils.isNotNull(localAccessToken) && localAccessToken.available()) {
            accessToken.putAll(localAccessToken.getAccessTokenParam());
        } else {
            // 获取token请求构建者
            EcBaseTokenRequestBuilder tokenRequestBuilder = oauth2Token.getTokenRequestBuilder();
            // 构建客户端请求数据
            OAuthClientRequest accessTokenRequest = tokenRequestBuilder.buildClientRequest(oauth2Token.getTokenRequestParam());
            // 返回accessToken响应对象
            EcBaseOauthTokenResponse response = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST, tokenRequestBuilder.getTokenResponseClass());
            // 将accessToken放入map中
            accessToken.putAll(response.getParameters());
            // 保存accessToken
            oauthService.saveAccessToken(oauth2Token.getAccessTokenChannel(), oauth2Token.getAccessTokenDTO(accessToken));
        }
        return accessToken;
    }

}
