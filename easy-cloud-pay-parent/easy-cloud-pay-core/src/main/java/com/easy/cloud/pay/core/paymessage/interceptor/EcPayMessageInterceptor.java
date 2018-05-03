package com.easy.cloud.pay.core.paymessage.interceptor;

import java.util.Map;

import com.easy.cloud.pay.core.payment.service.EcPayServiceInf;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayMessageDTO;

/**
 * 支付消息拦截器，可以用来做验证
 * 
 * @author egan
 * 
 *         <pre>
 *     email egzosn@gmail.com
 *     date 2016-6-1 11:40:30
 *
 *
 *     source Daniel Qian
 *         </pre>
 */
public interface EcPayMessageInterceptor {

	/**
	 * 拦截微信消息
	 *
	 * @param payMessage
	 *            支付消息
	 * @param context
	 *            上下文，如果handler或interceptor之间有信息要传递，可以用这个
	 * @param payService
	 *            支付服务
	 * @return true代表OK，false代表不OK
	 */
	boolean intercept(EcPayMessageDTO payMessage, Map<String, Object> context, EcPayServiceInf payService);

}
