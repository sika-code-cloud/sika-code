package com.sika.code.standard.footer.demo.common.filter;

import com.sika.code.common.threadlocal.constant.ThreadLocalOperateType;
import com.sika.code.common.threadlocal.manager.ThreadLocalManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author daiqi
 * @create 2019-06-22 23:25
 */
@Slf4j
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put("name", "zhsangsan:"+ Thread.currentThread().getName());
        log.info("FirstFilter -------------------before");
        log.info("获取到的数据为：" + ThreadLocalManager.get("name"));
        ThreadLocalManager.set("name", "zhangsan:" );
        ThreadLocalManager.set("name1", "lisi", ThreadLocalOperateType.INHERITABLE);
        Thread thread = new Thread(new ThreadTest2());
        thread.start();
        chain.doFilter(request, response);
        log.info("FirstFilter -------------------end");
        log.info("获取到的数据为：" + ThreadLocalManager.get("name"));
        log.info("获取到的数据为：" + ThreadLocalManager.get("name1"));
        log.info("\t\n");


    }

    @Slf4j
    public static class ThreadTest2 implements Runnable {
        @Override
        public void run() {
            try {
                log.info("ThreadTest2获取到的数据为：" + ThreadLocalManager.get("name1", ThreadLocalOperateType.INHERITABLE));
                Thread thread = new Thread(new ThreadTest3());
                thread.start();
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ThreadLocalManager.removeInheritable();
            }
            System.out.println("thread 3:" + Thread.currentThread().getName());
        }
    }
    @Slf4j
    public static class ThreadTest3 implements Runnable {
        @Override
        public void run() {
            try {
                log.info("ThreadTest3获取到的数据为：" + ThreadLocalManager.get("name1", ThreadLocalOperateType.INHERITABLE));
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                ThreadLocalManager.removeInheritable();
            }
            System.out.println("thread 3:" + Thread.currentThread().getName());
        }
    }

    @Override
    public void destroy() {

    }
}
