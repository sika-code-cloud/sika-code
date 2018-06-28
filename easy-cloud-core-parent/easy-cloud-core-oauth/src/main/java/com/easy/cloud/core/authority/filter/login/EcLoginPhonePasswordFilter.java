package com.easy.cloud.core.authority.filter.login;

import com.easy.cloud.core.operator.sysuser.service.SysUserService;
import org.crazycake.shiro.AuthCachePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author daiqi
 * @create 2018-06-27 9:04
 */
@Component
public class EcLoginPhonePasswordFilter extends EcBaseLoginFilter {
    @Autowired
    protected SysUserService sysUserService;
    @Override
    protected AuthCachePrincipal getAuthCachePrincipal(String loginName) {
        return sysUserService.findByPhone(loginName);
    }
}
