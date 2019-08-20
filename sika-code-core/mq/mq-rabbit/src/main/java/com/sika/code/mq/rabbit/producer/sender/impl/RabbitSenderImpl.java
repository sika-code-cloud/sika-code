package com.sika.code.mq.rabbit.producer.sender.impl;

import com.sika.code.basic.constant.PropertiesConstant;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.log.util.LogUtil;
import com.sika.code.mq.common.constant.MqDataType;
import com.sika.code.mq.common.generator.MqMsgGenerator;
import com.sika.code.mq.common.dto.MqMsgDTO;
import com.sika.code.mq.rabbit.common.constant.MqExchangeConfigEnum;
import com.sika.code.mq.rabbit.common.constant.MqQueueConfigEnum;
import com.sika.code.mq.rabbit.producer.sender.RabbitSender;
import com.sika.code.mq.rabbit.producer.sender.dto.RabbitSenderDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * rabbit发送者
 *
 * @author daiqi
 * @create 2019-07-06 19:01
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = PropertiesConstant.RABBIT_SENDER)
public class RabbitSenderImpl implements RabbitSender {

    private RabbitTemplate rabbitTemplate;

    private MqMsgGenerator msgGenerator;

    private boolean logFire;

    public RabbitSenderImpl(RabbitTemplate rabbitTemplate, MqMsgGenerator msgGenerator) {
        this.rabbitTemplate = rabbitTemplate;
        this.msgGenerator = msgGenerator;
    }

    @PostConstruct
    public void init() {
        rabbitTemplate.setMandatory(true);
    }

    @Override
    public void convertAndSend(RabbitSenderDTO rabbitSenderDTO) {
        convertSendOrReceive(rabbitSenderDTO, false);
    }


    @Override
    public Object convertSendAndReceive(RabbitSenderDTO rabbitSenderDTO) {
        return convertSendOrReceive(rabbitSenderDTO, true);
    }

    protected Object convertSendOrReceive(RabbitSenderDTO rabbitSenderDTO, boolean receive) {
        // 交换机配置枚举
        MqExchangeConfigEnum exchangeConfigEnum = rabbitSenderDTO.getExchangeConfigEnum();
        // 队列配置枚举
        MqQueueConfigEnum queueConfigEnum = rabbitSenderDTO.getQueueConfigEnum();
        // amqpTemplate
        RabbitTemplate rabbitTemplate = rabbitSenderDTO.getRabbitTemplate();
        // 传输的数据
        Object data = rabbitSenderDTO.getData();
        // 数据类型
        MqDataType dataType = rabbitSenderDTO.getDataType();
        if (BaseUtil.isNull(rabbitTemplate)) {
            rabbitTemplate = this.rabbitTemplate;
        }
        // 转化为MqMsgDTO
        MqMsgDTO msgDTO = convertToMsgDTO(data, dataType);
        CorrelationData correlationData = new CorrelationData(msgDTO.getMsgNo());
        // 记录日志
        log(msgDTO, exchangeConfigEnum, queueConfigEnum);
        Object retObj = null;
        if (BaseUtil.isAllNotNull(exchangeConfigEnum, queueConfigEnum)) {
            if (receive) {
                retObj = rabbitTemplate.convertSendAndReceive(exchangeConfigEnum.getName(), queueConfigEnum.getName(), msgDTO, correlationData);
            } else {
                rabbitTemplate.convertAndSend(exchangeConfigEnum.getName(), queueConfigEnum.getName(), msgDTO, correlationData);
            }
        } else if (BaseUtil.isNull(queueConfigEnum)) {
            if (receive) {
                retObj = rabbitTemplate.convertSendAndReceive(queueConfigEnum.getName(), msgDTO, correlationData);
            } else {
                rabbitTemplate.convertAndSend(queueConfigEnum.getName(), msgDTO, correlationData);
            }
        } else {
            if (receive) {
                retObj = rabbitTemplate.convertSendAndReceive(msgDTO, correlationData);
            } else {
                rabbitTemplate.convertAndSend(msgDTO);
            }
        }
        return retObj;
    }


    /**
     * 转换为MsgDTO
     */
    protected MqMsgDTO convertToMsgDTO(Object data, MqDataType dataType) {
        MqMsgDTO msgDTO;
        if (data instanceof MqMsgDTO) {
            msgDTO = (MqMsgDTO) data;
        } else {
            msgDTO = msgGenerator.generateMqMsg(data);
            msgDTO.setDataType(dataType);
        }
        return msgDTO;
    }

    public void log(Object logDetail, MqExchangeConfigEnum exchangeConfigEnum, MqQueueConfigEnum queueConfigEnum) {
        if (!logFire || !log.isInfoEnabled()) {
            return;
        }
        String logTitle = "send mq msg";
        log.info(LogUtil.START_LINE_BREAK);
        log.info(LogUtil.startFormat(), LogUtil.arguments(logTitle));
        log.info("send MqExchange:\t{}", exchangeConfigEnum);
        log.info("send MqQueue:\t{}", queueConfigEnum);
        log.info("send msgDTO:\t{}", LogUtil.buildLogDetail(logDetail));
        log.info(LogUtil.endFormat(), LogUtil.arguments(logTitle));
        log.info(LogUtil.END_LINE_BREAK);
    }

}
