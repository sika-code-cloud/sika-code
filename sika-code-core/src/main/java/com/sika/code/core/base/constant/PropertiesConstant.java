package com.sika.code.core.base.constant;

/**
 * ConfigurationProperties注解的prefix值
 *
 * @author daiqi
 * @create 2019-07-30 9:35
 */
public class PropertiesConstant {
    /**
     * 工作空间 --- meta.share.workspace
     */
    public static final String WORKSPACE = "meta.share.workspace";
    /**
     * 分布式锁属性前缀 --- meta.share.lock
     */
    public static final String LOCK = "meta.share.lock";
    /**
     * 异常前缀 --- meta.share.exception
     */
    public static final String EXCEPTION = "meta.share.exception";
    /**
     * sql前缀 --- meta.share.log.sql
     */
    public static final String LOG_SQL = "meta.share.log.sql";
    /**
     * rabbitMq发送者前缀 --- meta.share.rabbit.sender
     */
    public static final String RABBIT_SENDER = "meta.share.rabbit.sender";
    /**
     * restTemplate信息 --- meta.share.rest.template
     */
    public static final String REST_TEMPLATE = "meta.share.rest.template";
    /**
     * 消息队列发送者确认 --- meta.share.rabbit.sender.ack
     */
    public static final String RABBIT_SENDER_ACK = "meta.share.rabbit.sender.ack";
    /**
     * 日志controller开关 --- meta.share.log.controller.fire
     */
    public static final String LOG_CONTROLLER_FIRE = "meta.share.log.controller.fire";
    /**
     * jdbc开关 --- meta.share.jdbc.fire
     */
    public static final String JDBC_FIRE = "meta.share.jdbc.fire";
    /**
     * redis开关 --- meta.share.redis.fire
     */
    public static final String REDIS_FIRE = "meta.share.redis.fire";
}
