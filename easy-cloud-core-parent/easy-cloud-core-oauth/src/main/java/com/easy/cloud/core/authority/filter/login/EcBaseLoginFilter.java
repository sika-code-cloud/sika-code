package com.easy.cloud.core.authority.filter.login;

import com.easy.cloud.core.authority.constant.EcAuthorityConstant;
import com.easy.cloud.core.authority.filter.base.EcAccessControlFilter;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import org.crazycake.shiro.AuthCachePrincipal;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author daiqi
 * @create 2018-06-27 19:10
 */
public abstract class EcBaseLoginFilter extends EcAccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        Object loginInfo =request.getAttribute(EcAuthorityConstant.LOGIN_INFO);
        if (EcBaseUtils.isNotNull(loginInfo)) {
            return true;
        }
        String loginName = request.getParameter(EcAuthorityConstant.LOGIN_NAME);
        AuthCachePrincipal authCachePrincipal = getAuthCachePrincipal(loginName);
        if (authCachePrincipal != null) {
            EcLogUtils.info("获取数据成功" ,authCachePrincipal, logger);
            request.setAttribute(EcAuthorityConstant.USERNAME, authCachePrincipal.getAuthCacheKey());
            request.setAttribute(EcAuthorityConstant.LOGIN_INFO, authCachePrincipal);
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

    protected abstract AuthCachePrincipal getAuthCachePrincipal(String loginName);

}
