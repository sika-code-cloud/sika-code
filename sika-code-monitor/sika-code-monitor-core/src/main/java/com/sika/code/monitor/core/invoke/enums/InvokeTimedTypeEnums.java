package com.sika.code.monitor.core.invoke.enums;

import com.sika.code.core.base.constant.BaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * InvokeTimedEnums
 *
 * @author : sikadai
 * @date : 2023-09-18
 */
@Getter
@AllArgsConstructor
public enum InvokeTimedTypeEnums implements BaseTypeEnum<String> {
    MQ_CONSUME("mqConsume", "mq.consume.invoke.timed", "MQ消费处理耗时"),
    MQ_PRODUCE("mqProduce", "mq.produce.invoke.timed", "MQ生产发送耗时"),
    DB_CLIENT("dbClient", "db.client.invoke.timed", "DB客户端方法执行耗时"),
    HTTP_CLIENT("httpClient", "http.client.invoke.timed", "HTTP客户端方法执行耗时"),
    DUBBO_CLIENT("dubboClient", "dubbo.client.invoke.timed", "DUBBO客户端方法执行耗时"),
    DUBBO_SERVER("dubboServer", "dubbo.server.invoke.timed", "DUBBO服务端方法执行耗时"),
    REDIS_LETTUCE_COMPLETION("redisLettuceCompletion", "lettuce.command.completion",
        "Latency between command send and command completion (complete response received"),
    REDIS_LETTUCE_FIRST_RESPONSE("redisLettuceFirstResponse", "lettuce.command.firstresponse",
        "Latency between command send and first response (first response received)"),
    ;
    private final String type;
    private final String name;
    private final String desc;

}
