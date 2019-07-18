package com.sika.code.standard.footer.demo.common.mq.constant.queue;

import com.sika.code.mq.rabbit.common.constant.MqQueueConfigEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.sika.code.standard.footer.demo.common.mq.constant.queue.DemoMqQueueConfigEnum.DemoQueueName.*;


/**
 * 队列枚举类
 *
 * @author daiqi
 * @create 2019-07-06 15:38
 */
@AllArgsConstructor
@Getter
@ToString
public enum DemoMqQueueConfigEnum implements MqQueueConfigEnum {
    /**
     * 队列枚举类
     */
    QUEUE(1, QUEUE_NAME, "测试队列"),
    QUEUE2(2, QUEUE_NAME2, true, "测试队列"),
    QUEUE3(3, QUEUE_NAME3, true, "测试队列"),
    QUEUE4(4, QUEUE_NAME4, true, "测试队列"),
    QUEUE5(5, QUEUE_NAME5, true, "测试队列"),
    ;

    DemoMqQueueConfigEnum(Integer type, String name, String desc) {
        this.type = type;
        this.name = name;
        this.desc = desc;
    }

    DemoMqQueueConfigEnum(Integer type, String name, boolean durable, String desc) {
        this.type = type;
        this.name = name;
        this.durable = durable;
        this.desc = desc;
    }

    private Integer type;
    private String name;
    private boolean durable;
    private boolean exclusive;
    private boolean autoDelete;

    private String desc;


    public static class DemoQueueName {

        public static final String QUEUE_NAME = "queue";
        public static final String QUEUE_NAME2 = "queue2";
        public static final String QUEUE_NAME3 = "api.core";
        public static final String QUEUE_NAME4 = "api.core.user";
        public static final String QUEUE_NAME5 = "api.core.user.query";
    }

}
