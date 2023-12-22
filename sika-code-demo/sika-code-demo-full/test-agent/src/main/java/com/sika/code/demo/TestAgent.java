package com.sika.code.demo;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.implementation.bind.annotation.*;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.util.concurrent.Callable;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class TestAgent {

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, module) -> {
            // 增强方法{selectUser}
            return builder.method(ElementMatchers.named("route"))
                    // 设置拦截器
                    .intercept(MethodDelegation.to(Interceptor.class));
        };
        new AgentBuilder.Default()
                // 忽略掉不增强的类
                .ignore(nameStartsWith("net.bytebuddy.")
                        .or(nameStartsWith("net.bytebuddy."))
                        .or(nameStartsWith("org.slf4j."))
                        .or(nameStartsWith("org.groovy."))
                        .or(nameStartsWith("java."))
                        .or(nameStartsWith("sun."))
                        .or(nameContains("javassist"))
                        .or(nameContains(".asm."))
                        .or(nameContains(".reflectasm."))
                        .or(nameStartsWith("sun.reflect"))
                        .or(ElementMatchers.isSynthetic())
                )
                // 增强的类
                .type(ElementMatchers.named("org.apache.shardingsphere.sharding.route.engine.type.standard.ShardingStandardRoutingEngine"))
                // 增强的类需 增强的方法实现
                .transform(transformer)
                // 注册增强类监听器
                .with(new AgentBuilder.Listener() {
                    @Override
                    public void onDiscovery(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
                    }

                    @Override
                    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded, DynamicType dynamicType) {
                        String className = typeDescription.getName();
                        System.out.println("onTransformation增强类: " + className);
                    }

                    @Override
                    public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule module, boolean loaded) {
//                        System.out.println("onIgnored类: " + typeDescription);
                    }

                    @Override
                    public void onError(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded, Throwable throwable) {
                        System.out.println("onError增强类失败: " + typeName + "; 失败原因: " + throwable);
                    }

                    @Override
                    public void onComplete(String typeName, ClassLoader classLoader, JavaModule module, boolean loaded) {
                    }
                })
                // 监听类加载
                .installOn(instrumentation);

        // 增强后的类, 写入至文件, 便于观察
        instrumentation.addTransformer((loader, className, classBeingRedefined, protectionDomain, classfileBuffer) -> {
            if (className.endsWith("Controller")) {
                try {
                    String substring = className.substring(className.lastIndexOf("/"));
                    File file = new File("D:/data/bytebuddy-class/" + substring + ".class");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    Files.write(file.toPath(), classfileBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return classfileBuffer;
        });
    }

    public static class Interceptor {
        @RuntimeType
        public static Object intercept(@This Object target,    // 当前拦截的目标对象{this}
                                       @AllArguments Object[] allArguments,   // 方法入参
                                       @SuperCall Callable<?> superCall,      // 代理对象
                                       @Origin Method method                  // 当前拦截的目标方法
        ) throws Throwable {
            Object result = null;  // 方法返回结果
            long start = System.currentTimeMillis();
            try {
                System.out.println("前置拦截");
                // 执行目标方法
                result = superCall.call();
                // 返回目标方法执行结果
                return result;
            } catch (Throwable t) {
                System.out.println("异常拦截");
                throw t;
            } finally {
                System.out.println("后置拦截: 方法返回值 = " + result);
                System.out.println(method + " took " + (System.currentTimeMillis() - start));
            }
        }
    }
}