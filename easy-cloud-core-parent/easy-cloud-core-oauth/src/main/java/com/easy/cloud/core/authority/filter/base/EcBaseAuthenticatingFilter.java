package com.easy.cloud.core.authority.filter.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.mgt.SecurityManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author daiqi
 * @create 2018-07-02 9:42
 */
public abstract class EcBaseAuthenticatingFilter extends AuthenticatingFilter {
    @Autowired
    protected SecurityManager securityManager;

    @Override
    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        SecurityUtils.setSecurityManager(securityManager);
        return SecurityUtils.getSubject();
    }
}
