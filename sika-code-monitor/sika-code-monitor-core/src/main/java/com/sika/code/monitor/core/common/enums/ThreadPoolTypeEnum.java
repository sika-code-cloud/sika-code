package com.sika.code.monitor.core.common.enums;


/**
 * ThreadPoolTypeName
 *
 * @author : daiqi
 * @date : 2023-06-15
 */
public enum ThreadPoolTypeEnum {
    DUBBO("dubbo", "dubbo类型线程池"),
    MQ("mq", "MQ线程池"),
    TOMCAT("tomcat", "tomcat线程池"),
    BUSINESS("business", "业务线程池"),
    ;
    private final String name;
    private final String desc;

    ThreadPoolTypeEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}