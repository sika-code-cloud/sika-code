package com.sika.code.monitor.core.threadpool.enums;

import com.sika.code.core.base.constant.BaseTypeEnum;
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
    DUBBO("dubbo", "dubbo.thread.pool", "dubbo类型线程池"),
    MQ("mq", "mq.thread.pool", "MQ线程池"),
    TOMCAT("tomcat", "tomcat.thread.pool", "tomcat线程池"),
    BUSINESS("business", "business.thread.pool", "业务线程池"),
    BUSINESS_HIPPO4J("businessHippo4j", "business.hippo4j.thread.pool", "hippo4j业务线程池"),
    BUSINESS_DYNAMICTP("businessDynamicTp", "business.dynamictp.thread.pool", "DynamicTp业务线程池"),
    ;
    private final String type;
    private final String name;
    private final String desc;

}
