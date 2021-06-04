package com.sika.code.standard.footer.demo.common.mq.receiver;

import java.lang.reflect.Method;
import java.util.Arrays;

class Child1 extends Parent<String> {
    @Override
    public void setValue(String value) {
        System.out.println("Child1.setValue called");
        super.setValue(value);
    }
    public static void main(String[] args) {
        Child1 child1 = new  Child1();
        Method [] methods = child1.getClass().getDeclaredMethods();
        Arrays.stream(methods)
                .filter(method -> method.getName().equals("setValue") && !method.isBridge())
                .forEach(method -> {
                    try {
                        method.invoke(child1, "test");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(child1.toString());
    }
}