package com.sika.code.basic.constant;

/**
 * ConditionalOnProperty注解的value
 *
 * @author daiqi
 * @create 2019-07-30 9:34
 */
public class PropertyConstant {
    /**
     * 消息队列发送者确认 --- sika.code.rabbit.sender.ack
     */
    public static final String RABBIT_SENDER_ACK = "sika.code.rabbit.sender.ack";
    /**
     * 日志controller开关 --- sika.code.log.controller.fire
     */
    public static final String LOG_CONTROLLER_FIRE = "sika.code.log.controller.fire";
    /**
     * jdbc开关 --- sika.code.jdbc.fire
     */
    public static final String JDBC_FIRE = "sika.code.jdbc.fire";
    /**
     * redis开关 --- sika.code.redis.fire
     */
    public static final String REDIS_FIRE = "sika.code.redis.fire";
}
