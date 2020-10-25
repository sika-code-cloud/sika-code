package com.sika.code.mq.rabbit.producer.config;

import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.log.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 生产者确认配置类
 *
 * @author daiqi
 * @create 2019-07-07 22:28
 */
@Slf4j
public class RabbitProducerAckConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private Jackson2JsonMessageConverter jackson2JsonMessageConverter;

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {

        } else {
            logConfirmFail(correlationData, cause);
            doConfirmFail(correlationData, cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logReturnedMessage(message, replyCode, replyText, exchange, routingKey);
        doReturnedMessage(message, replyCode, replyText, exchange, routingKey);
    }

    /**
     * <p>
     * 处理确认失败的方法
     * </p>
     *
     * @param correlationData
     * @param cause
     * @return void
     * @author daiqi
     * @date 2019/7/7 22:43
     */
    protected void doConfirmFail(CorrelationData correlationData, String cause) {

    }

    /**
     * <p>
     * 执行从交换机到队列失败的逻辑
     * </p>
     *
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     * @return void
     * @author daiqi
     * @date 2019/7/7 22:44
     */
    protected void doReturnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {

    }

    protected void logConfirmFail(CorrelationData correlationData, String cause) {
        String correlationDataId = null;
        if (BaseUtil.isNotNull(correlationData)) {
            correlationDataId = correlationData.getId();
        }
        String logTitle = "发送到交换机失败";
        log.error(LogUtil.startFormat(), LogUtil.arguments(logTitle));
        log.error("消息：{}发送失败，原因: {}", correlationDataId, cause);
        log.error(LogUtil.endFormat(), LogUtil.arguments(logTitle));
    }

    protected void logReturnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        String correlationId = message.getMessageProperties().getCorrelationId();
        if (!log.isInfoEnabled()) {
            return;
        }
        Object data = jackson2JsonMessageConverter.fromMessage(message);
        String logTitle = "发送到队列失败";
        log.error(LogUtil.startFormat(), LogUtil.arguments(logTitle));
        log.error("消息id：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        log.error("消息详情:{}", LogUtil.buildLogDetail(data));
        log.error(LogUtil.endFormat(), LogUtil.arguments(logTitle));
    }
}
