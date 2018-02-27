package com.dq.easy.cloud.pay.model.payment.service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

import com.dq.easy.cloud.model.basic.constant.DqBaseConstant.DqCharset;
import com.dq.easy.cloud.model.basic.service.DqBaseService;
import com.dq.easy.cloud.model.common.http.pojo.bo.DqHttpRequestTemplateBO;
import com.dq.easy.cloud.model.common.http.pojo.dto.DqHttpConfigStorageDTO;
import com.dq.easy.cloud.model.common.sign.utils.DqSignUtils;
import com.dq.easy.cloud.pay.model.base.api.DqCallback;
import com.dq.easy.cloud.pay.model.payment.config.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;

/**
 * 支付抽象服务接口
 * 
 * @author: egan
 * 
 *          <pre>
 *      email egzosn@gmail.com
 *      date 2017/3/5 20:36
 *          </pre>
 */
public abstract class DqPayServiceAbstract extends DqBaseService implements DqPayServiceInf {

	protected DqPayConfigStorageInf payConfigStorage;

	protected DqHttpRequestTemplateBO requestTemplate;
	protected int retrySleepMillis = 1000;

	protected int maxRetryTimes = 5;

	public DqPayServiceAbstract(){
		
	}
	/**
	 * 设置支付配置
	 * 
	 * @param payConfigStorage
	 *            支付配置
	 */
	@Override
	public DqPayServiceAbstract setPayConfigStorage(DqPayConfigStorageInf payConfigStorage) {
		this.payConfigStorage = payConfigStorage;
		return this;
	}

	@Override
	public DqPayConfigStorageInf getPayConfigStorage() {
		return payConfigStorage;
	}

	@Override
	public DqHttpRequestTemplateBO getHttpRequestTemplate() {
		return requestTemplate;
	}

	/**
	 * 设置并创建请求模版， 代理请求配置这里是否合理？？，
	 * 
	 * @param configStorage
	 *            http请求配置
	 * @return 支付服务
	 */
	@Override
	public DqPayServiceAbstract setRequestTemplateConfigStorage(DqHttpConfigStorageDTO configStorage) {
		this.requestTemplate = new DqHttpRequestTemplateBO(configStorage);
		return this;
	}

	public DqPayServiceAbstract(DqPayConfigStorageInf payConfigStorage) {
		this(payConfigStorage, null);
	}

	public DqPayServiceAbstract(DqPayConfigStorageInf payConfigStorage, DqHttpConfigStorageDTO configStorage) {
		setPayConfigStorage(payConfigStorage);
		setRequestTemplateConfigStorage(configStorage);
	}

	/**
	 * 创建签名
	 *
	 * @param content
	 *            需要签名的内容
	 * @param characterEncoding
	 *            字符编码
	 * @return 签名
	 */
	@Override
	public String createSign(String content, String characterEncoding) {

		return DqSignUtils.valueOf(payConfigStorage.getSignType()).createSign(content, payConfigStorage.getKeyPrivate(),
				characterEncoding);
	}

	/**
	 * 创建签名
	 *
	 * @param content
	 *            需要签名的内容
	 * @param characterEncoding
	 *            字符编码
	 * @return 签名
	 */
	@Override
	public String createSign(Map<String, Object> content, String characterEncoding) {
		return DqSignUtils.valueOf(payConfigStorage.getSignType()).sign(content, payConfigStorage.getKeyPrivate(),
				characterEncoding);
	}

	/**
	 * 将请求参数或者请求流转化为 Map
	 *
	 * @param parameterMap
	 *            请求参数
	 * @param is
	 *            请求流
	 * @return 获得回调的请求参数
	 */
	@Override
	public Map<String, Object> getParameterToMap(Map<String, String[]> parameterMap, InputStream is) {

		Map<String, Object> params = new TreeMap<String, Object>();
		for (Iterator<String> iter = parameterMap.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = parameterMap.get(name);
			String valueStr = "";
			for (int i = 0, len = values.length; i < len; i++) {
				valueStr += (i == len - 1) ? values[i] : values[i] + ",";
			}
			if (!valueStr.matches("\\w+")) {
				try {
					if (valueStr.equals(new String(valueStr.getBytes(DqCharset.ISO8859_1), "iso8859-1"))) {
						valueStr = new String(valueStr.getBytes(DqCharset.ISO8859_1), payConfigStorage.getInputCharset());
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			params.put(name, valueStr);
		}
		return params;
	}

	/**
	 * 交易查询接口，带处理器
	 * 
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回查询回来的结果集
	 */
	@Override
	public <T> T query(String tradeNo, String outTradeNo, DqCallback<T> callback) {

		return callback.perform(queryPayResult(tradeNo, outTradeNo));
	}

	/**
	 * 交易关闭接口
	 *
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回支付方交易关闭后的结果
	 */
	@Override
	public <T> T close(String tradeNo, String outTradeNo, DqCallback<T> callback) {
		return callback.perform(close(tradeNo, outTradeNo));
	}

	/**
	 * 退款
	 *
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @param refundAmount
	 *            退款金额
	 * @param totalAmount
	 *            总金额
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 *
	 * @return 处理过后的类型对象， 返回支付方申请退款后的结果
	 * @see #refund(RefundOrder, DqCallback)
	 */
	@Deprecated
	@Override
	public <T> T refund(String tradeNo, String outTradeNo, BigDecimal refundAmount, BigDecimal totalAmount,
			DqCallback<T> callback) {

		return callback.perform(refund(new DqRefundOrderDTO(tradeNo, outTradeNo, refundAmount, totalAmount)));
	}

	/**
	 * 申请退款接口
	 *
	 * @param refundOrder
	 *            退款订单信息
	 * @return 返回支付方申请退款后的结果
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回支付方申请退款后的结果
	 */
	@Override
	public <T> T refund(DqRefundOrderDTO refundOrder, DqCallback<T> callback) {

		return callback.perform(refund(refundOrder));
	}

	/**
	 * 查询退款
	 *
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 *
	 * @return 处理过后的类型对象，返回支付方查询退款后的结果
	 */
	@Override
	public <T> T refundQuery(String tradeNo, String outTradeNo, DqCallback<T> callback) {
		return callback.perform(queryRefundResult(tradeNo, outTradeNo));
	}

	/**
	 * 目前只支持日账单
	 *
	 * @param billDate
	 *            账单时间：具体请查看对应支付平台
	 * @param billType
	 *            账单类型，具体请查看对应支付平台
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 *
	 * @return 返回支付方下载对账单的结果
	 */
	@Override
	public <T> T downLoadBill(Date billDate, String billType, DqCallback<T> callback) {
		return callback.perform(downLoadBill(billDate, billType));
	}

	/**
	 * @param tradeNoOrBillDate
	 *            支付平台订单号或者账单类型， 具体请 类型为{@link String }或者 {@link Date }
	 *            ，类型须强制限制，类型不对应则抛出异常{@link PayErrorException}
	 * @param outTradeNoBillType
	 *            商户单号或者 账单类型
	 * @param transactionType
	 *            交易类型
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 返回支付方对应接口的结果
	 */
	@Override
	public <T> T secondaryInterface(Object tradeNoOrBillDate, String outTradeNoBillType,
			DqTransactionType transactionType, DqCallback<T> callback) {
		return callback.perform(secondaryInterface(tradeNoOrBillDate, outTradeNoBillType, transactionType));
	}

	/**
	 * 转账
	 *
	 * @param order
	 *            转账订单
	 * @param callback
	 *            处理器
	 *
	 * @return 对应的转账结果
	 */
	@Override
	public <T> T transfer(DqTransferOrderDTO order, DqCallback<T> callback) {
		return callback.perform(transfer(order));
	}

	/**
	 * 转账
	 *
	 * @param order
	 *            转账订单
	 *
	 * @return 对应的转账结果
	 */
	@Override
	public Map<String, Object> transfer(DqTransferOrderDTO order) {
		return new HashMap<>(0);
	}

	/**
	 * 转账查询
	 *
	 * @param outNo
	 *            商户转账订单号
	 * @param tradeNo
	 *            支付平台转账订单号
	 *
	 * @return 对应的转账订单
	 */
	@Override
	public Map<String, Object> queryTransferResult(String outNo, String tradeNo) {
		return new HashMap<>(0);
	}

	/**
	 * 转账查询
	 *
	 * @param outNo
	 *            商户转账订单号
	 * @param tradeNo
	 *            支付平台转账订单号
	 * @param callback
	 *            处理器
	 * @param <T>
	 *            返回类型
	 * @return 对应的转账订单
	 */
	@Override
	public <T> T transferQuery(String outNo, String tradeNo, DqCallback<T> callback) {
		return callback.perform(queryTransferResult(outNo, tradeNo));
	}
}
