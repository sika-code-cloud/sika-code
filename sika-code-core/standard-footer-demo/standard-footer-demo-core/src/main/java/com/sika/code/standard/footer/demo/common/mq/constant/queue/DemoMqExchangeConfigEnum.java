package com.sika.code.standard.footer.demo.common.mq.constant.queue;

import com.sika.code.mq.rabbit.common.constant.MqExchangeConfigEnum;
import com.sika.code.mq.rabbit.common.constant.MqExchangeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.sika.code.standard.footer.demo.common.mq.constant.queue.DemoMqExchangeConfigEnum.DemoExchangeName.*;


/**
 * 队列枚举类
 *
 * @author daiqi
 * @create 2019-07-06 15:38
 */
@AllArgsConstructor
@Getter
@ToString
public enum DemoMqExchangeConfigEnum implements MqExchangeConfigEnum {
    /**
     * 队列枚举类
     */
    EXCHANGE(1, EXCHANGE_NAME, MqExchangeType.TOPIC, true, "TOPIC"),
    EXCHANGE1(1, EXCHANGE_NAME1, MqExchangeType.TOPIC, true, "TOPIC"),
    EXCHANGE2(2, EXCHANGE_NAME2, MqExchangeType.DIRECT, true, "DIRECT"),
    EXCHANGE3(3, EXCHANGE_NAME3, MqExchangeType.FANOUT, true, "FANOUT"),
    EXCHANGE31(3, EXCHANGE_NAME31, MqExchangeType.FANOUT, true, "FANOUT"),
    EXCHANGE4(4, EXCHANGE_NAME4, MqExchangeType.HEADERS, true, "HEADERS"),
    EXCHANGE5(5, EXCHANGE_NAME5, true, "测试队列"),
    ;

    DemoMqExchangeConfigEnum(Integer type, String name, MqExchangeType exchangeType, boolean durable, String desc) {
        this.type = type;
        this.name = name;
        this.exchangeType = exchangeType;
        this.durable = durable;
        this.desc = desc;
    }

    DemoMqExchangeConfigEnum(Integer type, String name, MqExchangeType exchangeType, String desc) {
        this.type = type;
        this.name = name;
        this.exchangeType = exchangeType;
        this.desc = desc;
    }

    DemoMqExchangeConfigEnum(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    DemoMqExchangeConfigEnum(Integer type, String name, boolean durable, String desc) {
        this.type = type;
        this.name = name;
        this.durable = durable;
        this.desc = desc;
    }

    private Integer type;
    private String name;
    private MqExchangeType exchangeType;
    private boolean durable;
    private boolean exclusive;
    private boolean autoDelete;

    private String desc;

    @Override
    public String getName() {
        return getNameFull(this.name, this.exchangeType);
    }

    public static class DemoExchangeName {

        public static final String EXCHANGE_NAME = "test";
        public static final String EXCHANGE_NAME1 = "test1";
        public static final String EXCHANGE_NAME2 = "test2";
        public static final String EXCHANGE_NAME3 = "test3";
        public static final String EXCHANGE_NAME31 = "test31";
        public static final String EXCHANGE_NAME4 = "test4";
        public static final String EXCHANGE_NAME5 = "test5";
    }

}
