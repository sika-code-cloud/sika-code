package com.easy.cloud.pay.core.paymessage.handler;

import java.util.Map;

import com.easy.cloud.pay.core.payment.service.EcPayServiceInf;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayMessageDTO;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayOutMessageDTO;

/**
 * 
 * <p>
 * 处理支付回调消息的处理器接口
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午3:46:03
 */
public interface EcPayMessageHandler {

	/**
	 * @param payMessage
	 *            支付消息
	 * @param context
	 *            上下文，如果handler或interceptor之间有信息要传递，可以用这个
	 * @param payService
	 *            支付服务
	 * @return xml,text格式的消息，如果在异步规则里处理的话，可以返回null
	 */
	EcPayOutMessageDTO handle(EcPayMessageDTO payMessage, Map<String, Object> context, EcPayServiceInf payService);

}
