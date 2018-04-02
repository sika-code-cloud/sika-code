package com.dq.easy.cloud.module.mq.amqp.base.dto;

import java.util.List;

import com.dq.easy.cloud.module.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.mq.amqp.constant.DqAmqpQueueName.DqAmqpQueueNameEnum;

/**
 * amqp的消息 数据传输对象
 * @author daiqi
 *
 */
public class DqAmqpBaseDTO {
	/**消息发送到队列的名称*/
	private String queueName;
	/**发送的消息*/
	private String msg;
	/**交换机名称*/
	private String exchange;
	
	public DqAmqpBaseDTO() {
		super();
	}
	/**
	 * 使用枚举参数作为构造参数--使用默认的交换机
	 * @param dqAmqpQueueNameEnum
	 * @param msg
	 */
	public DqAmqpBaseDTO(DqAmqpQueueNameEnum dqAmqpQueueNameEnum, Object msg){
		buildQueueName(dqAmqpQueueNameEnum.getQueueName()).buildMsg(msg);
	}
	
	/**
	 * 使用枚举参数作为构造参数
	 * @param dqAmqpQueueNameEnum
	 * @param msg
	 */
	public DqAmqpBaseDTO(String exchange, DqAmqpQueueNameEnum dqAmqpQueueNameEnum, Object msg){
		buildExchange(exchange).buildQueueName(dqAmqpQueueNameEnum.getQueueName()).buildMsg(msg);
	}
	
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getQueueName() {
		return queueName;
	}
	
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	/**
	 * 
	 * <p>构建消息</p>
	 *
	 * @param msg
	 * @return
	 * @author daiqi
	 * @date 2017年12月20日 下午4:30:08
	 */
	public DqAmqpBaseDTO buildMsg(Object msg){
		this.msg = DqJSONUtils.parseObject(msg, String.class);
		return this;
	}
	/**
	 * 
	 * <p>构建交换机名称</p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @param exchange
	 * @return
	 * @author daiqi
	 * @date 2017年12月21日 下午5:51:55
	 */
	public DqAmqpBaseDTO buildExchange(String exchange){
		this.exchange = exchange;
		return this;
	}
	
	/**
	 * <p>构建队列名称</p>
	 *
	 * @param queueName
	 * @return
	 * @author daiqi
	 * @date 2017年12月20日 下午4:29:53
	 */
	public DqAmqpBaseDTO buildQueueName(String queueName){
		this.queueName = queueName;
		return this;
	}
	
	/**
	 * 
	 * <p>获取泛型class对应的对象</p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @param clazz
	 * @return 对象
	 * @author daiqi
	 * @date 2017年12月20日 下午4:23:02
	 */
	public <T> T getTObj(Class<T> clazz){
		if(DqStringUtils.isEmpty(msg)){
			return null;
		}
		return DqJSONUtils.parseObject(msg, clazz);
	}
	/**
	 * 
	 * <p>获取msg为列表json字符串时的泛型列表</p>
	 *
	 * <pre>
	 * </pre>
	 *
	 * @param clazz
	 * @return List
	 * @author daiqi
	 * @date 2017年12月20日 下午4:24:47
	 */
	public <T> List<T> getTList(Class<T> clazz){
		if(DqStringUtils.isEmpty(msg)){
			return null;
		}
		return DqJSONUtils.parseArray(msg, clazz);
	}
}
