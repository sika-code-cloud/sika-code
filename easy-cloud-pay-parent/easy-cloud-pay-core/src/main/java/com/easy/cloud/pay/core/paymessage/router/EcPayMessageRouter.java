package com.easy.cloud.pay.core.paymessage.router;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.easy.cloud.pay.core.payment.service.EcPayServiceInf;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayMessageDTO;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayOutMessageDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <pre>
 * 支付消息路由器，通过代码化的配置，把来自支付的消息交给handler处理
 * 
 * 说明：
 * 1. 配置路由规则时要按照从细到粗的原则，否则可能消息可能会被提前处理
 * 2. 默认情况下消息只会被处理一次，除非使用 {@link EcPayMessageRouterRule#next()}
 * 3. 规则的结束必须用{@link EcPayMessageRouterRule#end()}或者{@link EcPayMessageRouterRule#next()}，否则不会生效
 *
 * 使用方法：
 * PayMessageRouter router = new PayMessageRouter();
 * router
 *   .executor()
 *       .msgType("MSG_TYPE").event("EVENT").eventKey("EVENT_KEY").content("CONTENT")
 *       .interceptor(interceptor, ...).handler(handler, ...)
 *   .end()
 *   .executor()
 *       // 另外一个匹配规则
 *   .end()
 * ;
 *
 * // 将PayMessage交给消息路由器
 * router.route(message);
 *  source chanjarster/weixin-java-tools  Daniel Qian
 * </pre>
 * 
 * @author egan
 *
 */
public class EcPayMessageRouter {

	protected final Log LOG = LogFactory.getLog(EcPayMessageRouter.class);
	/**
	 * 异步线程大小
	 */
	private static final int DEFAULT_THREAD_POOL_SIZE = 100;
	/**
	 * 规则集
	 */
	private final List<EcPayMessageRouterRule> rules = new ArrayList<EcPayMessageRouterRule>();
	/**
	 * 支付服务
	 */
	private final EcPayServiceInf payService;
	/**
	 * 异步线程处理器
	 */
	private ExecutorService executorService;

	/**
	 * 根据支付服务创建路由
	 * 
	 * @param payService
	 *            支付服务
	 */
	public EcPayMessageRouter(EcPayServiceInf payService) {
		this.payService = payService;
		this.executorService = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
	}

	/**
	 * <pre>
	 * 设置自定义的 {@link ExecutorService}
	 * 如果不调用用该方法，默认使 Executors.newFixedThreadPool(100)
	 * </pre>
	 * 
	 * @param executorService
	 *            异步线程处理器
	 */
	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	/**
	 * 获取所有的规则
	 * 
	 * @return 规则
	 */
	List<EcPayMessageRouterRule> getRules() {
		return this.rules;
	}

	/**
	 * 开始一个新的Route规则
	 * 
	 * @return 新的Route规则
	 */
	public EcPayMessageRouterRule rule() {
		return new EcPayMessageRouterRule(this);
	}

	/**
	 * 处理支付消息
	 * 
	 * @param payMessage
	 *            支付消息
	 * @return 支付输出结果
	 */
	@SuppressWarnings("rawtypes")
	public EcPayOutMessageDTO route(final EcPayMessageDTO payMessage) {

		final List<EcPayMessageRouterRule> matchRules = new ArrayList<EcPayMessageRouterRule>();
		// 收集匹配的规则
		for (final EcPayMessageRouterRule rule : rules) {
			if (rule.test(payMessage)) {
				matchRules.add(rule);
				if (!rule.isReEnter()) {
					break;
				}
			}
		}

		if (matchRules.size() == 0) {
			return null;
		}

		EcPayOutMessageDTO res = null;
		final List<Future> futures = new ArrayList<Future>();
		for (final EcPayMessageRouterRule rule : matchRules) {
			// 返回最后一个非异步的rule的执行结果
			if (rule.isAsync()) {
				futures.add(executorService.submit(new Runnable() {
					@Override
					public void run() {
						rule.service(payMessage, payService);
					}
				}));
			} else {
				res = rule.service(payMessage, payService);
				// 在同步操作结束，session访问结束
				LOG.debug("End session access: async=false, fromPay=" + payMessage.getFromPay());
			}
		}

		if (futures.size() > 0) {
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					for (Future<?> future : futures) {
						try {
							future.get();
							LOG.debug("End session access: async=true, fromPay=" + payMessage.getFromPay());

						} catch (InterruptedException e) {
							LOG.error("Error happened when wait worker finish", e);
						} catch (ExecutionException e) {
							LOG.error("Error happened when wait worker finish", e);
						}
					}
				}
			});
		}
		return res;
	}

}
