package com.easy.cloud.core.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.easy.cloud.EcCoreApplication;
import com.easy.cloud.core.mq.amqp.base.dto.EcAmqpBaseDTO;
import com.easy.cloud.core.mq.amqp.constant.EcAmqpQueueName.EcAmqpQueueNameEnum;
import com.easy.cloud.core.mq.amqp.handler.EcAmqpTemplateHandler;
import com.easy.cloud.core.user.entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EcCoreApplication.class) // 指定spring-boot的启动类
public class AmqpTemplateTest {
	@Test
	public void testSend(){
		EcAmqpBaseDTO baseVo = new EcAmqpBaseDTO(EcAmqpQueueNameEnum.QUEUE_NAME_TEST, new UserEntity("usernem","rewa",12));
		EcAmqpTemplateHandler.send(baseVo);
	}
}
