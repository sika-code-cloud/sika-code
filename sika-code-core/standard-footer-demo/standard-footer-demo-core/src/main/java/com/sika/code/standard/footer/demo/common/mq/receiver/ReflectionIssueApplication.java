package com.sika.code.standard.footer.demo.common.mq.receiver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionIssueApplication {
    private void age(int age) {
        System.out.println("int age = " + age);
    }
    private void age(Integer age) {
        System.out.println("Integer age = " + age);
    }

    public void testReflect() {
        try {
            getClass().getDeclaredMethod("age", Integer.class)
                    .invoke(this, Integer.valueOf(36));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ReflectionIssueApplication().testReflect();
    }
}