package com.sika.code.mq.rabbit.producer.config;

import com.sika.code.basic.constant.BaseConstant;
import com.sika.code.basic.constant.PropertyConstant;
import com.sika.code.mq.common.generator.MqMsgGenerator;
import com.sika.code.mq.rabbit.producer.sender.RabbitSender;
import com.sika.code.mq.rabbit.producer.sender.impl.RabbitSenderImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>
 * RabbitMq的生产者配置
 * </p>
 *
 * @author daiqi
 * @date 2019/6/26 21:44
 */
@Configuration
public class RabbitProducerConfig {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MqMsgGenerator msgGenerator;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConditionalOnMissingBean
    @ConditionalOnProperty(value = PropertyConstant.RABBIT_SENDER_ACK, havingValue = "true")
    public RabbitProducerAckConfig rabbitProducerAckConfig() {
        return new RabbitProducerAckConfig();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    @ConditionalOnMissingBean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory, @Autowired(required = false) RabbitProducerAckConfig rabbitProducerAckConfig) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setEncoding(BaseConstant.Charset.UTF_8);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        rabbitTemplate.setConfirmCallback(rabbitProducerAckConfig);
        rabbitTemplate.setReturnCallback(rabbitProducerAckConfig);
        return rabbitTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitSender rabbitSender(RabbitTemplate rabbitTemplate) {
        return new RabbitSenderImpl(rabbitTemplate, msgGenerator);
    }

}