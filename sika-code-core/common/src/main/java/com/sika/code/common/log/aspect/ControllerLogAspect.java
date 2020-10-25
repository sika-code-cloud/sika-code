package com.sika.code.common.log.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * controller日志记录类
 * </p>
 *
 * @author daiqi
 * @date 2019/5/24 23:13
 * @return
 */
@Aspect
@Order(value = 1)
@Slf4j
public class ControllerLogAspect {

    @Pointcut("@annotation(com.sika.code.common.log.annotation.ControllerLog)")
    public void controllerLogMethodCut() {
    }

    @Pointcut("@within(com.sika.code.common.log.annotation.ControllerLog)")
    public void controllerLogCut() {
    }

    @Around("controllerLogMethodCut()||controllerLogCut()")
    public Object controllerLogRequest(ProceedingJoinPoint pjp) throws Throwable {

        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        String url = request.getServletPath().toString();
        if ("".equals(url)) {
            url = request.getPathInfo();
        }
        if ("".equals(url)) {
            url = request.getRequestURI();
        }
        log.info("\r\n");
        log.info("**********************************************  Request To Controller (" + url + ") **********************************************");
        log.info("------------------------  Request Parameters  ------------------------");
        Object[] os = pjp.getArgs();
        Object result = null;
        try {
            for (Object o : os) {
                if (o instanceof ServletResponse) {
                    continue;
                } else if (o instanceof Model) {
                    log.info("org.springframework.ui.Model  ---------   " + JSON.toJSONString(o));
                } else if (o instanceof ServletRequest) {
                    log.info("ServletRequest ParameterMap  ---------   " + JSON.toJSONString(((ServletRequest) o).getParameterMap()));
                } else if (!(o instanceof BindingResult)) {
                    log.info(JSON.toJSONString(o));
                }
            }
            log.info("\r\n");
        } catch (Error e) {
            log.info("Controller Log Error ---------   " + e.getMessage());
        } finally {
            result = pjp.proceed();
        }
        return result;
    }

    @AfterReturning(value = "controllerLogMethodCut()||controllerLogCut()", returning = "obj")
    public Object controllerLogResponse(Object obj) throws Throwable {
        log.info("------------------------  Response Result  ------------------------");
        log.info(JSON.toJSONString(obj));
        log.info("**********************************************  Request End **********************************************");
        return obj;
    }
}
