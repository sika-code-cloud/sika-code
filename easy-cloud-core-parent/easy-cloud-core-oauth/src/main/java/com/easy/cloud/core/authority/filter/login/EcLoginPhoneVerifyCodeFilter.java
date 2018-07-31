package com.easy.cloud.core.authority.filter.login;

import org.crazycake.shiro.AuthCachePrincipal;

/**
 * @author daiqi
 * @create 2018-06-29:04
 */
public class EcLoginPhoneVerifyCodeFilter extends EcBaseLoginFilter {
    @Override
    protected AuthCachePrincipal getAuthCachePrincipal(String loginName) {
        return null;
    }
}
