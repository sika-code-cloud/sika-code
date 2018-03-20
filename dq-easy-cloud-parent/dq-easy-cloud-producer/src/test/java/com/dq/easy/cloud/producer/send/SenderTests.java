package com.dq.easy.cloud.producer.send;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dq.easy.cloud.DqEasyCloudProducerApplication;
import com.dq.easy.cloud.model.mq.amqp.base.dto.DqAmqpBaseDTO;
import com.dq.easy.cloud.model.mq.amqp.constant.DqAmqpQueueName.DqAmqpQueueNameEnum;
import com.dq.easy.cloud.model.mq.amqp.handler.DqAmqpTemplateHandler;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DqEasyCloudProducerApplication.class) // 指定spring-boot的启动类
public class SenderTests {
	@Test
	public void testSendToQueue(){
		for(int i = 0 ; i < 100 ; ++i){
			DqAmqpTemplateHandler.send(new DqAmqpBaseDTO(DqAmqpQueueNameEnum.QUEUE_NAME_TEST, "hello"+i));
//			DqAmqpTemplateHandler.sendToQueue(new DqAmqpBaseVo(DqAmqpQueueNameEnum.QUEUE_NAME_TEST1, "hello"+i));
			
		}
	}
	@Test
	public void testSendToTopic(){
		String msg1 = "I am topic.test msg======";
        System.out.println("sender1 : " + msg1);
        DqAmqpTemplateHandler.send(new DqAmqpBaseDTO("exchange", DqAmqpQueueNameEnum.QUEUE_NAME_TOPIC_TEST, msg1));
//        
        String msg2 = "I am topic.tests msg########";
        System.out.println("sender2 : " + msg2);
        DqAmqpTemplateHandler.send(new DqAmqpBaseDTO("exchange", DqAmqpQueueNameEnum.QUEUE_NAME_TOPIC_TESTS, msg2));
	}
}
