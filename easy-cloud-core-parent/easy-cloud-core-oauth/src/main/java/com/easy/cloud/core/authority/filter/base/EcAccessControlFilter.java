package com.easy.cloud.core.authority.filter.base;

import com.easy.cloud.core.common.http.wrapper.WrappedHttpServletRequest;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author daiqi
 * @create 2018-06-27 14:15
 */
public abstract class EcAccessControlFilter extends AccessControlFilter{
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void printToJson(ServletRequest request, ServletResponse response, Object obj) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter wirte = null;
        String jsonStr = EcJSONUtils.toJSONString(obj);
        try {
            wirte = response.getWriter();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }finally{
            wirte.print(jsonStr);
            wirte.flush();
            wirte.close();
        }

    }
}
