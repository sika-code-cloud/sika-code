package com.sika.code.mq.rabbit.common.constant;

import com.sika.code.basic.constant.TypeEnumInf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.amqp.core.ExchangeTypes;

/**
 * @author daiqi
 * @create 2019-07-06 23:25
 */
@Getter
@AllArgsConstructor
public enum MqExchangeType implements TypeEnumInf {
    /**
     * 交换器类型枚举
     */
    DIRECT(1, ExchangeTypes.DIRECT, "直接交换机"),
    TOPIC(2, ExchangeTypes.TOPIC, "主题交换机"),
    FANOUT(3, ExchangeTypes.FANOUT, "扇形交换机"),
    HEADERS(4, ExchangeTypes.HEADERS, "头交换机"),
    SYSTEM(5, ExchangeTypes.SYSTEM, "系统交换机"),
    ;
    private Integer type;
    private String typeName;
    private String desc;
}
