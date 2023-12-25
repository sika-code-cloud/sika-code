package com.sika.code.demo;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class TimingInterceptor {
    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable) {
        long start = System.currentTimeMillis();
        try {
            return callable.call();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println(method + " took " + (System.currentTimeMillis() - start));
        }
        return null;
    }
}

//
//        new AgentBuilder.Default()
//                .ignore(nameStartsWith("org.aspectj."))
//                .or(nameStartsWith("org.groovy."))
//                .or(nameStartsWith("com.sun."))
//                .or(nameStartsWith("sun."))
//                .or(nameStartsWith("jdk."))
//                .or(nameStartsWith("org.springframework.asm."))
//                .or(nameStartsWith("com.p6spy."))
//                .or(nameStartsWith("net.bytebuddy."))
//                .type(nameStartsWith("com.sika")) //以com.开头的包会注入
//                .transform((builder, type, classLoader, module, domain) ->
//        builder.method(ElementMatchers.any())
//        .intercept(MethodDelegation.to(TimingInterceptor.class))
//        ).installOn(instrumentation);