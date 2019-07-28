package com.sika.code.standard.footer.demo.common.mq.receiver;

import com.sika.code.basic.errorcode.BaseErrorCodeEnum;
import com.sika.code.common.json.util.JSONUtil;
import com.sika.code.exception.BusinessException;
import com.sika.code.mq.common.dto.MqMsgDTO;
import com.sika.code.standard.footer.demo.common.mq.constant.queue.DemoMqQueueConfigEnum;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class TestNotifyReceive {
    @RabbitListener(queues = DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME)
    public void receive(MqMsgDTO mqMsgDTO) {
        log.info("TEST_NAME receive message: {}", JSONUtil.toJSONString(mqMsgDTO));
    }

    @RabbitListener(queues = DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME2)
    public void receive1(MqMsgDTO mqMsgDTO) {
        log.info("TEST_NAME2 receive message: {}", JSONUtil.toJSONString(mqMsgDTO));
    }

    @RabbitListener(queues = DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME5)
    public void receive5(MqMsgDTO mqMsgDTO) {
        log.info("{} receive message: {}", DemoMqQueueConfigEnum.QUEUE5, JSONUtil.toJSONString(mqMsgDTO));
    }

    @RabbitListener(queues = DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME4)
    public void receive4(MqMsgDTO mqMsgDTO) {
        log.info("{} receive message: {}", DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME4, JSONUtil.toJSONString(mqMsgDTO));
    }

    @RabbitListener(queues = DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME4)
    public void receive41(MqMsgDTO mqMsgDTO) {
        log.info("{}1 receive message: {}", DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME4, JSONUtil.toJSONString(mqMsgDTO));
    }

    @RabbitListener(queues = DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME4)
    public void receive42(MqMsgDTO mqMsgDTO) {
        log.info("{}2 receive message: {}", DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME4, JSONUtil.toJSONString(mqMsgDTO));
    }
    @RabbitListener(queues = DemoMqQueueConfigEnum.DemoQueueName.QUEUE_NAME3)
    public void receive3(MqMsgDTO mqMsgDTO, Channel channel, Message message) throws IOException {
        try {
            // 模拟执行任务
            Thread.sleep(1000);
            log.info("{} receive message: {}", DemoMqQueueConfigEnum.QUEUE3, JSONUtil.toJSONString(mqMsgDTO));
            // 确认收到消息，false只确认当前consumer一个消息收到，true确认所有consumer获得的消息
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                System.out.println("消息已重复处理失败,拒绝再次接收！");
                // 拒绝消息，requeue=false 表示不再重新入队，如果配置了死信队列则进入死信队列
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                System.out.println("消息即将再次返回队列处理！");
                // requeue为是否重新回到队列，true重新入队
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
            e.printStackTrace();
        }
        throw new BusinessException(BaseErrorCodeEnum.SYS_EXCEPTION);
    }
}