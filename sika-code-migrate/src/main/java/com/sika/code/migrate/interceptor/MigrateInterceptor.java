package com.sika.code.migrate.interceptor;

import com.alibaba.fastjson.JSON;
import com.sika.code.migrate.executor.MigrateRequestExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 *  迁移拦截器
 * </pre>
 *
 * @author daiqi
 * @version 1.0
 * @since 2022/8/8 12:50
 */
@Component
@Slf4j
public class MigrateInterceptor implements HandlerInterceptor {
    @Autowired
    private MigrateRequestExecutor requestExecutor;

    /**
     * preHandle:预先处理请求的方法 。相当于总开关 参数：Object handler：被拦截的控制器对象（Mycontroller）
     * 返回值：Boolean   当为真时，请求正确，可以被Controller处理，程序正常执行。
     * 当为假时，请求不能被处理，控制器方法不会执行。请求到此截止。
     * 特点：1.预处理方法的执行时间：在控制器方法之前先执行的. 2.可以对请求做处理，可以做登陆检查，全限的判断，统计数据等等
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            log.info("进入拦截器{}", MigrateInterceptor.class.getName());
            log.info("进入拦截器的request为:{},请求方式为：{}，response:{}", request.getClass(), request.getMethod(), response.getClass());
            log.info("进入拦截器的requesthead为:{},请求方式为：{}，response:{}", request.getHeaderNames(), request.getMethod(), response.getClass());
            log.info("进入拦截器的getParameterMap为:{}", JSON.toJSONString(request.getParameterMap()));
            return requestExecutor.execute(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
        return true;
    }
}
