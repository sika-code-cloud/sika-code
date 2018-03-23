package com.dq.easy.cloud.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.module.mq.amqp.constant.DqAmqpQueueName;

@Component
public class HelloReceive {

    @RabbitListener(queues=DqAmqpQueueName.QUEUE_NAME_TEST)    //监听器监听指定的Queue
    public void process1(String str) {
        System.out.println("Receive:---------process1-------------"+str);
    }
    
    @RabbitListener(queues=DqAmqpQueueName.QUEUE_NAME_TEST)    //监听器监听指定的Queue
    public void process2(String str) {
        System.out.println("Receive:-------process2---------------"+str);
    }
    
    @RabbitListener(queues=DqAmqpQueueName.QUEUE_NAME_TOPIC_TEST)    //监听器监听指定的Queue
    public void processTopic1(String str) {
        System.out.println("Receive:-------processTopic1---------------"+str);
    }
    
    @RabbitListener(queues=DqAmqpQueueName.QUEUE_NAME_TOPIC_TESTS)    //监听器监听指定的Queue
    public void processTopic2(String str) {
        System.out.println("Receive:-------processTopic2---------------"+str);
    }
    @RabbitListener(queues=DqAmqpQueueName.QUEUE_NAME_TOPICS)    //监听器监听指定的Queue
    public void processTopics(String str) {
    	System.out.println("Receive:-------processTopics---------------"+str);
    }
}