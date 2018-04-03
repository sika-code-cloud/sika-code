package com.easy.cloud.core.config.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import com.easy.cloud.core.conditional.amqp.EcAmqpConditional;
import com.easy.cloud.core.mq.amqp.constant.EcAmqpQueueName;
import com.easy.cloud.core.mq.amqp.constant.EcAmqpQueueName.EcAmqpQueueNameEnum;
import com.easy.cloud.core.mq.amqp.utils.EcAmqpUtils;


@Configuration
@Conditional(EcAmqpConditional.class)
public class EcAmqpConfig {
	@Bean
    public Queue queueTest() {
        return EcAmqpUtils.createQueue(EcAmqpQueueNameEnum.QUEUE_NAME_TEST);
    }
    
    @Bean
    public Queue queueTest1() {
        return EcAmqpUtils.createQueue(EcAmqpQueueNameEnum.QUEUE_NAME_TEST1);
    }
    
    //===============以下是验证topic Exchange的队列==========
    @Bean
    public Queue queueTopicTest() {
        return EcAmqpUtils.createQueue(EcAmqpQueueNameEnum.QUEUE_NAME_TOPIC_TEST);
    }

    @Bean
    public Queue queueTopicTests() {
        return EcAmqpUtils.createQueue(EcAmqpQueueNameEnum.QUEUE_NAME_TOPIC_TESTS);
    }
    @Bean
    public Queue queueTopicsTests() {
    	return EcAmqpUtils.createQueue(EcAmqpQueueNameEnum.QUEUE_NAME_TOPICS);
    }
  //===============以上是验证topic Exchange的队列==========
    
    
    //===============以下是验证Fanout Exchange的队列==========
    @Bean
    public Queue queueFanoutA() {
        return EcAmqpUtils.createQueue(EcAmqpQueueNameEnum.QUEUE_NAME_FANOUT_TESTA);
    }

    @Bean
    public Queue queueFanoutB() {
    	return EcAmqpUtils.createQueue(EcAmqpQueueNameEnum.QUEUE_NAME_FANOUT_TESTB);
    }

    @Bean
    public Queue queueFanoutC() {
    	return EcAmqpUtils.createQueue(EcAmqpQueueNameEnum.QUEUE_NAME_FANOUT_TESTC);
    }
    //===============以上是验证Fanout Exchange的队列==========
    

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(EcAmqpQueueName.EXCHANGE_TOPIC);
    }
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(EcAmqpQueueName.EXCHANGE_FANOUT);
    }

    /**
     * 将队列topic.message与exchange绑定，binding_key为topic.message,就是完全匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeTopicTest(Queue queueTopicTest, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopicTest).to(exchange).with(EcAmqpQueueName.QUEUE_NAME_TOPIC_TEST);
    }

    /**
     * 将队列topic.messages与exchange绑定
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeTopicTests(Queue queueTopicTests, TopicExchange exchange) {
        return BindingBuilder.bind(queueTopicTests).to(exchange).with(EcAmqpQueueName.QUEUE_NAME_TOPIC_TESTS);
    }
    /**
     * 将队列topic.messages与exchange绑定，binding_key为topic.#,模糊匹配
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeTopicsTests(Queue queueTopicsTests, TopicExchange exchange) {
    	return BindingBuilder.bind(queueTopicsTests).to(exchange).with(EcAmqpQueueName.QUEUE_NAME_TOPICS);
    }
    
    @Bean
    Binding bindingExchangeA(Queue queueFanoutA,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanoutA).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue queueFanoutB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanoutB).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue queueFanoutC, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFanoutC).to(fanoutExchange);
    }
}
