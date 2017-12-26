package com.dq.easy.cloud.model.amqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dq.easy.cloud.DqEasyCloudModelApplication;
import com.dq.easy.cloud.model.mq.amqp.base.vo.DqAmqpBaseVo;
import com.dq.easy.cloud.model.mq.amqp.constant.DqAmqpQueueName.DqAmqpQueueNameEnum;
import com.dq.easy.cloud.model.mq.amqp.handler.DqAmqpTemplateHandler;
import com.dq.easy.cloud.model.user.entity.UserEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DqEasyCloudModelApplication.class) // 指定spring-boot的启动类
public class AmqpTemplateTest {
	@Test
	public void testSend(){
		DqAmqpBaseVo baseVo = new DqAmqpBaseVo(DqAmqpQueueNameEnum.QUEUE_NAME_TEST, new UserEntity("usernem","rewa",12));
		DqAmqpTemplateHandler.send(baseVo);
	}
}
