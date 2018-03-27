package com.dq.easy.cloud.producer.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dq.easy.cloud.module.mq.amqp.base.dto.DqAmqpBaseDTO;
import com.dq.easy.cloud.module.mq.amqp.constant.DqAmqpQueueName;
import com.dq.easy.cloud.module.mq.amqp.constant.DqAmqpQueueName.DqAmqpQueueNameEnum;
import com.dq.easy.cloud.module.mq.amqp.handler.DqAmqpTemplateHandler;

@RestController
public class TestSend {

	@RequestMapping("sendMsg")
	@ResponseBody
	public Map<String,Object> sendMsg(@RequestBody Map<String,Object> paramsMap){
		Map<String,Object> retMap = new HashMap<>();
		DqAmqpTemplateHandler.send(new DqAmqpBaseDTO(DqAmqpQueueName.EXCHANGE_TOPIC,DqAmqpQueueNameEnum.QUEUE_NAME_TOPIC_TEST, paramsMap));
		String msg1 = "I am topic.test msg======";
        System.out.println("sender1 : " + msg1);
        DqAmqpTemplateHandler.send(new DqAmqpBaseDTO("exchange", DqAmqpQueueNameEnum.QUEUE_NAME_TOPIC_TEST, msg1));
//        
        String msg2 = "I am topic.tests msg########";
        System.out.println("sender2 : " + msg2);
        DqAmqpTemplateHandler.send(new DqAmqpBaseDTO("exchange", DqAmqpQueueNameEnum.QUEUE_NAME_TOPIC_TESTS, msg2));
		retMap.put("msg", paramsMap.get("msg"));
		return retMap;
	}
}
