package com.sika.code.standard.footer.demo.common.mq.config;

import com.sika.code.mq.rabbit.common.generator.MqGenerator;
import com.sika.code.standard.footer.demo.common.mq.constant.queue.DemoMqBindConfigEnum;
import com.sika.code.standard.footer.demo.common.mq.constant.queue.DemoMqExchangeConfigEnum;
import com.sika.code.standard.footer.demo.common.mq.constant.queue.DemoMqQueueConfigEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq配置类
 *
 * @author daiqi
 * @create 2019-07-06 15:35
 */
@Configuration
public class DemoMqConfig {
    @Bean
    public Queue queue() {
        return MqGenerator.generateQueue(DemoMqQueueConfigEnum.QUEUE);
    }

    @Bean
    public Queue queue2() {
        return MqGenerator.generateQueue(DemoMqQueueConfigEnum.QUEUE2);
    }

    @Bean
    public Queue queue3() {
        return MqGenerator.generateQueue(DemoMqQueueConfigEnum.QUEUE3);
    }

    @Bean
    public Queue queue4() {
        return MqGenerator.generateQueue(DemoMqQueueConfigEnum.QUEUE4);
    }

    @Bean
    public Queue queue5() {
        return MqGenerator.generateQueue(DemoMqQueueConfigEnum.QUEUE5);
    }

    @Bean
    public Exchange coreExchange() {
        return MqGenerator.generateExchange(DemoMqExchangeConfigEnum.EXCHANGE);
    }

    @Bean
    public Exchange coreExchange1() {
        return MqGenerator.generateExchange(DemoMqExchangeConfigEnum.EXCHANGE1);
    }

    @Bean
    public Exchange fanoutExchange() {
        return MqGenerator.generateExchange(DemoMqExchangeConfigEnum.EXCHANGE3);
    }

    @Bean
    public Exchange fanoutExchange1() {
        return MqGenerator.generateExchange(DemoMqExchangeConfigEnum.EXCHANGE31);
    }

    @Bean
    public Binding bindingCoreExchange(Queue queue4, TopicExchange coreExchange) {
        return MqGenerator.generateBinding(coreExchange, queue4, DemoMqBindConfigEnum.API_CORE);
    }

    @Bean
    public Exchange directExchange() {
        return MqGenerator.generateExchange(DemoMqExchangeConfigEnum.EXCHANGE2);
    }

    @Bean
    public Binding bindingDirectExchange(Queue queue3, DirectExchange directExchange) {
        return MqGenerator.generateBinding(directExchange, queue3, DemoMqBindConfigEnum.API_CORE);
    }

    @Bean
    public Binding bindingCoreExchange2(Queue queue5, TopicExchange coreExchange) {
        return MqGenerator.generateBinding(coreExchange, queue5, DemoMqBindConfigEnum.API_CORE_ANY);
    }

    @Bean
    public Binding bindingFanoutExchange(Queue queue4, FanoutExchange fanoutExchange1) {
        return MqGenerator.generateBinding(fanoutExchange1, queue4);
    }

    @Bean
    public Binding bindingFanoutExchange1(Queue queue5, FanoutExchange fanoutExchange1) {
        return MqGenerator.generateBinding(fanoutExchange1, queue5);
    }

    @Bean
    public Binding bindingFanoutExchange2(Queue queue3, FanoutExchange fanoutExchange1) {
        return MqGenerator.generateBinding(fanoutExchange1, queue3);
    }

    @Bean
    public Binding bindingFanoutExchange13(Queue queue2, FanoutExchange fanoutExchange1) {
        return MqGenerator.generateBinding(fanoutExchange1, queue2);
    }
}
