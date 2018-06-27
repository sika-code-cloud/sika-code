package com.easy.cloud.core.authority.filter;

import com.easy.cloud.core.common.log.utils.EcLogUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author daiqi
 * @create 2018-06-27 9:04
 */
public class EcCustomFilter2 extends AccessControlFilter{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        EcLogUtils.info("isAccessAllowed", "访问控制2" , logger);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        EcLogUtils.info("onAccessDenied", "访问控制2" , logger);
        return true;
    }
}
