package com.sika.code.mq.rabbit.common.generator;

import com.sika.code.mq.rabbit.common.constant.MqBindingConfigEnum;
import com.sika.code.mq.rabbit.common.constant.MqExchangeConfigEnum;
import com.sika.code.mq.rabbit.common.constant.MqQueueConfigEnum;
import org.springframework.amqp.core.*;

import java.util.Map;

/**
 * 消息生成者
 *
 * @author daiqi
 * @create 2019-07-06 17:07
 */
public class MqGenerator {
    /**
     * <p>
     * 根据类型创建queue
     * </p>
     *
     * @param mqQueueConfigEnum
     * @return org.springframework.amqp.core.Queue
     * @author daiqi
     * @date 2019/7/6 17:12
     */
    public static Queue generateQueue(MqQueueConfigEnum mqQueueConfigEnum) {
        return generateQueue(mqQueueConfigEnum, null);
    }

    /**
     * <p>
     * 根据队列枚举类型和参数创建队列
     * </p>
     *
     * @param mqQueueConfigEnum
     * @param arguments
     * @return org.springframework.amqp.core.Queue
     * @author daiqi
     * @date 2019/7/6 17:12
     */
    public static Queue generateQueue(MqQueueConfigEnum mqQueueConfigEnum, Map<String, Object> arguments) {
        return new Queue(
                mqQueueConfigEnum.getName(),
                mqQueueConfigEnum.isDurable(),
                mqQueueConfigEnum.isExclusive(),
                mqQueueConfigEnum.isAutoDelete(),
                arguments
        );
    }

    /**
     * <p>
     * 根据交换机配置创建交换机对象
     * </p>
     *
     * @param exchangeConfig
     * @return org.springframework.amqp.core.Exchange
     * @author daiqi
     * @date 2019/7/6 23:41
     */
    public static Exchange generateExchange(MqExchangeConfigEnum exchangeConfig) {
        return generateExchange(exchangeConfig, null);
    }

    /**
     * <p>
     * 根据交换机配置创建交换机对象
     * </p>
     *
     * @param exchangeConfig
     * @param arguments
     * @return org.springframework.amqp.core.Exchange
     * @author daiqi
     * @date 2019/7/6 23:41
     */
    public static Exchange generateExchange(MqExchangeConfigEnum exchangeConfig, Map<String, Object> arguments) {
        Exchange exchange;
        switch (exchangeConfig.getExchangeType()) {
            case DIRECT:
                exchange = new DirectExchange(exchangeConfig.getName(), exchangeConfig.isDurable(), exchangeConfig.isAutoDelete(), arguments);
                break;
            case TOPIC:
                exchange = new TopicExchange(exchangeConfig.getName(), exchangeConfig.isDurable(), exchangeConfig.isAutoDelete(), arguments);
                break;
            case FANOUT:
                exchange = new FanoutExchange(exchangeConfig.getName(), exchangeConfig.isDurable(), exchangeConfig.isAutoDelete(), arguments);
                break;
            case HEADERS:
                exchange = new HeadersExchange(exchangeConfig.getName(), exchangeConfig.isDurable(), exchangeConfig.isAutoDelete(), arguments);
                break;
            default:
                exchange = new DirectExchange(exchangeConfig.getName(), exchangeConfig.isDurable(), exchangeConfig.isAutoDelete(), arguments);

        }
        return exchange;
    }
    /**  
     * <p>
     * 生成FanoutExchange的绑定对象
     * </p>
     *   
     * @param exchange
     * @param queue
     * @return org.springframework.amqp.core.Binding
     * @author daiqi 
     * @date 2019/7/7 13:25
     */  
    public static Binding generateBinding(FanoutExchange exchange, Queue queue) {
        return generateBinding(exchange, queue, null);
    }
    /**
     * <p>
     * 生成绑定对象
     * </p>
     *
     * @param exchange          : 交换机
     * @param queue             : 队列
     * @param bindingConfigEnum : 绑定配置枚举
     * @return org.springframework.amqp.core.Binding
     * @author daiqi
     * @date 2019/7/7 12:06
     */
    public static Binding generateBinding(Exchange exchange, Queue queue, MqBindingConfigEnum bindingConfigEnum) {
        Binding binding = null;
        BindingBuilder.DestinationConfigurer destinationConfigurer = BindingBuilder.bind(queue);
        if (exchange instanceof DirectExchange) {
            binding = destinationConfigurer.to((DirectExchange) exchange).with(bindingConfigEnum.getRoutingKey());
        } else if (exchange instanceof TopicExchange) {
            binding = destinationConfigurer.to((TopicExchange) exchange).with(bindingConfigEnum.getRoutingKey());
        } else if (exchange instanceof FanoutExchange) {
            binding = destinationConfigurer.to((FanoutExchange) exchange);
        }
        return binding;
    }
}
