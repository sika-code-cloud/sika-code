package com.sika.code.monitor.core.threadpool.enums;

import com.sika.code.monitor.core.common.enums.BaseMetricsTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ThreadPoolTypeName
 *
 * @author : daiqi
 * @date : 2023-06-15
 */
@AllArgsConstructor
@Getter
public enum ThreadPoolTypeEnum implements BaseMetricsTypeEnum {
    DUBBO("dubbo", "dubbo", "dubbo类型线程池"),
    MQ("mq", "mq", "MQ线程池"),
    TOMCAT("tomcat", "tomcat", "tomcat线程池"),
    BUSINESS("business", "business.customer", "业务线程池"),
    BUSINESS_HIPPO4J("businessHippo4j", "business.hippo4j", "hippo4j业务线程池"),
    BUSINESS_DYNAMICTP("businessDynamicTp", "business.dynamictp", "DynamicTp业务线程池"),
    ;
    private final String type;
    private final String name;
    private final String desc;

}
