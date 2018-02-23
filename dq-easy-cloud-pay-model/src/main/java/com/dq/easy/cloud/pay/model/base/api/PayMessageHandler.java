package com.dq.easy.cloud.pay.model.base.api;

import java.util.Map;

import com.dq.easy.cloud.pay.model.payment.dto.DqPayMessageDTO;
import com.dq.easy.cloud.pay.model.payment.dto.DqPayOutMessageDTO;

/**
 * 
 * <p>
 * 处理支付回调消息的处理器接口
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午3:46:03
 */
public interface PayMessageHandler {

	/**
	 * @param payMessage
	 *            支付消息
	 * @param context
	 *            上下文，如果handler或interceptor之间有信息要传递，可以用这个
	 * @param payService
	 *            支付服务
	 * @return xml,text格式的消息，如果在异步规则里处理的话，可以返回null
	 */
	DqPayOutMessageDTO handle(DqPayMessageDTO payMessage, Map<String, Object> context, DqPayService payService);

}
