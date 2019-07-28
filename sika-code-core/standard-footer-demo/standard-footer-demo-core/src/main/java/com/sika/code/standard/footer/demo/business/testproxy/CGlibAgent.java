package com.sika.code.standard.footer.demo.business.testproxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author daiqi
 * @create 2019-07-21 20:06
 */
@Slf4j
public class CGlibAgent implements MethodInterceptor {

    private Object proxy;

    public Object getInstance(Object proxy) {
        this.proxy = proxy;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.proxy.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info(">>>> Before invoking");
        Object ret = methodProxy.invokeSuper(o, objects);
        log.info(">>> after invoking");
        return ret;
    }

    @Slf4j
    public static class Person {

        public void show () {
            log.info("show------------------");
        }
    }

    public static void main(String[] args) {
        CGlibAgent cGlibAgent = new CGlibAgent();
        Person person = (Person) cGlibAgent.getInstance(new Person());
        person.show();
    }
}
