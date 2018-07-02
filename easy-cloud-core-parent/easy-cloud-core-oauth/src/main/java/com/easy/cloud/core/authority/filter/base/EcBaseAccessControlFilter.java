package com.easy.cloud.core.authority.filter.base;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpResponse;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author daiqi
 * @create 2018-06-27 14:15
 */
public abstract class EcBaseAccessControlFilter extends AccessControlFilter {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    protected SecurityManager securityManager;

    @Override
    protected Subject getSubject(ServletRequest request, ServletResponse response) {
        SecurityUtils.setSecurityManager(securityManager);
        return SecurityUtils.getSubject();
    }

    protected void printToJson(ServletRequest request, ServletResponse response, Object obj) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ((HttpServletResponse)response).setHeader("Access-Control-Allow-Origin", "*");
        PrintWriter wirte = null;
        String jsonStr = EcJSONUtils.toJSONString(obj);
        try {
            wirte = response.getWriter();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            wirte.print(jsonStr);
            wirte.flush();
            wirte.close();
        }

    }
}
