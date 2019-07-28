package com.sika.code.mq.rabbit.producer.sender;

import com.sika.code.mq.rabbit.producer.sender.dto.RabbitSenderDTO;

/**
 * rabbit发送者
 *
 * @author daiqi
 * @create 2019-07-06 19:01
 */
public interface RabbitSender {
    /**
     * <p>
     * 转换并发送
     * </p>
     *
     * <pre>
     *       名称 : 注释 : 必须 : 备注
     *       exchangeConfigEnum : 交换机配置枚举 : 否
     *       queueConfigEnum : 队列配置枚举 ：否
     *       amqpTemplate : 模板对象：否
     *       data : 传输的数据：否
     *       dataType : 传输的数据类型 ：否
     * </pre>
     *
     * @param rabbitSenderDTO : 封装发送者数据传输对象
     * @return void
     * @author daiqi
     * @date 2019/7/7 19:39
     */
    void convertAndSend(RabbitSenderDTO rabbitSenderDTO);

    /**
     * <p>
     * 转换发送并且获取响应结果、效率底下不建议使用
     * </p>
     *
     * @param rabbitSenderDTO
     * @return java.lang.Object
     * @author daiqi
     * @date 2019/7/7 21:15
     */
    @Deprecated
    Object convertSendAndReceive(RabbitSenderDTO rabbitSenderDTO);
}