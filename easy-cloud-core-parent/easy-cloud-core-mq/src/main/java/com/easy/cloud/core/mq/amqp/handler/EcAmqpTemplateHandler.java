package com.easy.cloud.core.mq.amqp.handler;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.mq.amqp.base.dto.EcAmqpBaseDTO;

/**
 * 
 * @ClassName : DqAmqpTemplateHandler 
 * @Description : amqp模板助手类
 * @author daiqi
 * @date 2017年12月20日 下午4:19:35 
 *
 */
@Component
public class EcAmqpTemplateHandler {
	private static AmqpTemplate rabbitTemplate;

	@Resource
	public void setAmqpTemplate(AmqpTemplate rabbitTemplate) {
		EcAmqpTemplateHandler.rabbitTemplate = rabbitTemplate;
	}

	public static void send(EcAmqpBaseDTO baseVo){
		if(EcBaseUtils.isNull(baseVo)){
			return ;
		}
		if(EcStringUtils.isEmpty(baseVo.getExchange())){
			rabbitTemplate.convertAndSend(baseVo.getQueueName(), baseVo.getMsg());
		}else{
			rabbitTemplate.convertAndSend(baseVo.getExchange(), baseVo.getQueueName(), baseVo.getMsg());
		}
	}
}
