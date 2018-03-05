package com.dq.easy.cloud.model.mq.amqp.constant;

/**
 * 
 * @ClassName : DqAmqpQueueName 
 * @Description : 队列名称
 * @author daiqi
 * @date 2017年12月21日 下午3:47:12 
 *
 */
public class DqAmqpQueueName {
	/** --------------------exchange名称begin---------------- */
	public static final String EXCHANGE_TOPIC = "exchange";
	public static final String EXCHANGE_FANOUT = "fanoutExchange";
	/** --------------------exchange名称end---------------- */
	
	
	/** --------------------使用默认的交换机的队列名称 begin---------------- */
	/** --------------------使用默认的交换机的队列名称 begin---------------- */
	public static final String QUEUE_NAME_TEST = "testqueue";
	public static final String QUEUE_NAME_TEST1 = "testqueue1";
	/** --------------------使用默认的交换机的队列名称 end------------------ */

	/** --------------------使用top机制的队列名称 begin----------------*/
	
	public static final String QUEUE_NAME_TOPIC_TEST = "topic.test";
	public static final String QUEUE_NAME_TOPIC_TESTS = "topic.tests";
	public static final String QUEUE_NAME_TOPICS = "topic.#";
	/** --------------------使用top机制的队列名称 end----------------*/

	/**--------------------使用Fanout Exchange机制的队列名称 begin----------------*/
	public static final String QUEUE_NAME_FANOUT_TESTA = "fanout.testA";
	public static final String QUEUE_NAME_FANOUT_TESTB = "fanout.testB";
	public static final String QUEUE_NAME_FANOUT_TESTC = "fanout.testC";
	/** --------------------验证Fanout Exchange机制的队列名称 end--------------*/
	
	/**
	 * 队列名称枚举类--统一管理所有的队列信息
	 * @author daiqi
	 * 创建日期 2017年12月20日 下午11:37:17
	 */
	public static enum DqAmqpQueueNameEnum {
		// --------------------使用默认的交换机的队列名称 begin----------------
		QUEUE_NAME_TEST(DqAmqpQueueName.QUEUE_NAME_TEST), 
		QUEUE_NAME_TEST1(DqAmqpQueueName.QUEUE_NAME_TEST1),
		// --------------------使用默认的交换机的队列名称 end------------------

		// --------------------使用top机制的队列名称 begin----------------
		QUEUE_NAME_TOPIC_TEST(DqAmqpQueueName.QUEUE_NAME_TOPIC_TEST), 
		QUEUE_NAME_TOPIC_TESTS(DqAmqpQueueName.QUEUE_NAME_TOPIC_TESTS), 
		QUEUE_NAME_TOPICS(DqAmqpQueueName.QUEUE_NAME_TOPICS),
		// --------------------使用top机制的队列名称 end----------------

		// --------------------使用Fanout Exchange机制的队列名称 begin----------------
		QUEUE_NAME_FANOUT_TESTA(DqAmqpQueueName.QUEUE_NAME_FANOUT_TESTA), 
		QUEUE_NAME_FANOUT_TESTB(DqAmqpQueueName.QUEUE_NAME_FANOUT_TESTB), 
		QUEUE_NAME_FANOUT_TESTC(DqAmqpQueueName.QUEUE_NAME_FANOUT_TESTC),
		// --------------------验证Fanout Exchange机制的队列名称 end--------------
		;

		private DqAmqpQueueNameEnum(String queueName) {
			this.queueName = queueName;
		}

		private String queueName;

		public String getQueueName() {
			return queueName;
		}

		public void setQueueName(String queueName) {
			this.queueName = queueName;
		}
	}
}
