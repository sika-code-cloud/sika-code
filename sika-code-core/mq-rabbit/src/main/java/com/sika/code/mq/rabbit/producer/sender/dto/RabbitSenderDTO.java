package com.sika.code.mq.rabbit.producer.sender.dto;

import com.sika.code.mq.common.constant.MqDataType;
import com.sika.code.mq.rabbit.common.constant.MqExchangeConfigEnum;
import com.sika.code.mq.rabbit.common.constant.MqQueueConfigEnum;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * 发送者数据传输对象
 *
 * @author daiqi
 * @create 2019-07-07 19:34
 */
@Data
@Accessors(chain = true)
public class RabbitSenderDTO {
    /**
     * 交换机配置枚举
     */
    private MqExchangeConfigEnum exchangeConfigEnum;
    /**
     * 队列配置枚举
     */
    private MqQueueConfigEnum queueConfigEnum;
    /**
     * rabbitTemplate
     */
    private RabbitTemplate rabbitTemplate;
    /**
     * 传输的数据
     */
    private Object data;
    /**
     * 数据类型
     */
    private MqDataType dataType;

}
