package com.dq.easy.cloud.pay.zfb.service;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dq.easy.cloud.model.basic.constant.error.DqBaseErrorCode;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.model.common.date.utils.DqDateUtils;
import com.dq.easy.cloud.model.common.http.constant.DqHttpConstant.DqMethodType;
import com.dq.easy.cloud.model.common.http.pojo.dto.DqHttpConfigStorageDTO;
import com.dq.easy.cloud.model.common.http.utils.DqUriVariables;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.model.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.model.common.qrcode.utils.DqQrCodeUtil;
import com.dq.easy.cloud.model.common.sign.utils.DqSignUtils;
import com.dq.easy.cloud.model.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;
import com.dq.easy.cloud.pay.model.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayConstant.DqPayKey;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayErrorCode;
import com.dq.easy.cloud.pay.model.payment.constant.DqZfbPayConstant.DqZfbPayKey;
import com.dq.easy.cloud.pay.model.payment.constant.DqZfbPayConstant.DqZfbPayValue;
import com.dq.easy.cloud.pay.model.payment.constant.DqZfbPayConstant.DqZfbProductCode;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.payment.pojo.query.DqOrderQuery;
import com.dq.easy.cloud.pay.model.payment.service.DqPayServiceAbstract;
import com.dq.easy.cloud.pay.model.paymessage.pojo.dto.DqPayMessageDTO;
import com.dq.easy.cloud.pay.model.paymessage.pojo.dto.DqPayOutMessageDTO;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;
import com.dq.easy.cloud.pay.zfb.pojo.bo.DqZfbTransactionType;

/**
 * 
 * <p>
 * 支付宝支付服务类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月27日 上午10:35:46
 */
public class DqZfbPayService extends DqPayServiceAbstract {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 正式测试环境
	 */
	private final static String httpsReqUrl = DqZfbPayValue.HTTPS_REQ_URL;
	/**
	 * 沙箱测试环境账号
	 */
	private final static String devReqUrl = DqZfbPayValue.DEV_REQ_URL;

	/**
	 * 获取对应的请求地址
	 * 
	 * @return 请求地址
	 */
	public String getReqUrl() {
		return payConfigStorage.isTest() ? devReqUrl : httpsReqUrl;
	}

	public DqZfbPayService(DqPayConfigStorageInf payConfigStorage, DqHttpConfigStorageDTO configStorage) {
		super(payConfigStorage, configStorage);
	}

	public DqZfbPayService(DqPayConfigStorageInf payConfigStorage) {
		super(payConfigStorage);
	}

	public String getHttpsVerifyUrl() {
		return getReqUrl() + "?service=notify_verify";
	}

	/**
	 * 回调校验
	 *
	 * @param params
	 *            回调回来的参数集
	 * @return 签名校验 true通过
	 */
	@Override
	public boolean verify(Map<String, Object> params) {

		if (DqBaseUtils.isNull(params.get(DqZfbPayKey.SIGN_KEY))) {
			LOG.debug("支付宝支付异常：params：" + params);
			return false;
		}
		String sign = DqMapUtils.getString(params, DqZfbPayKey.SIGN_KEY);
		String notify_id = DqMapUtils.getString(params, DqZfbPayKey.NOTIFY__ID_KEY);
		return signVerify(params, sign) && verifySource(notify_id);

	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @param sign
	 *            比对的签名结果
	 * @return 生成的签名结果
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean signVerify(Map<String, Object> params, String sign) {

		if (params instanceof JSONObject) {
			for (String key : params.keySet()) {
				if (DqStringUtils.equals(DqZfbPayKey.SIGN_KEY, key)) {
					continue;
				}
				TreeMap response = new TreeMap((Map) params.get(key));
				LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
				linkedHashMap.put(DqZfbPayKey.CODE_KEY, response.remove(DqZfbPayKey.CODE_KEY));
				linkedHashMap.put(DqZfbPayKey.MSG_KEY, response.remove(DqZfbPayKey.MSG_KEY));
				linkedHashMap.putAll(response);
				return DqSignUtils.valueOf(payConfigStorage.getSignType()).verify(DqJSONUtils.parseObject(linkedHashMap, String.class),
						sign, payConfigStorage.getKeyPublic(), payConfigStorage.getInputCharset());
			}
		}
		return DqSignUtils.valueOf(payConfigStorage.getSignType()).verify(params, sign, payConfigStorage.getKeyPublic(),
				payConfigStorage.getInputCharset());
	}

	/**
	 * 校验数据来源
	 *
	 * @param id
	 *            业务id, 数据的真实性.
	 * @return true通过
	 */
	@Override
	public boolean verifySource(String id) {
		return true;
	}


	/**
	 * 返回创建的订单信息
	 *
	 * @param order
	 *            支付订单
	 * @return 订单信息
	 * @see DqPayOrderDTO 支付订单信息
	 */
	@Override
	public Map<String, Object> orderInfo(DqPayOrderDTO order) {
		return setSign(getOrder(order));
	}


	/**
	 * 获取输出消息，用户返回给支付端
	 * 
	 * @param code
	 *            状态
	 * @param message
	 *            消息
	 * @return 返回输出消息
	 */
	@Override
	public DqPayOutMessageDTO getPayOutMessage(String code, String message) {
		return DqPayOutMessageDTO.TEXT().content(code.toLowerCase()).build();
	}

	/**
	 * 获取成功输出消息，用户返回给支付端 主要用于拦截器中返回
	 * 
	 * @param payMessage
	 *            支付回调消息
	 * @return 返回输出消息
	 */
	@Override
	public DqPayOutMessageDTO successPayOutMessage(DqPayMessageDTO payMessage) {
		return DqPayOutMessageDTO.TEXT().content(DqZfbPayValue.SUCCESS_CODE).build();
	}

	/**
	 *
	 * @param orderInfo
	 *            发起支付的订单信息
	 * @param method
	 *            请求方式 "post" "get",
	 * @return 获取输出消息，用户返回给支付端, 针对于web端
	 */
	@Override
	public String buildRequest(Map<String, Object> orderInfo, DqMethodType method) {
		StringBuffer formHtml = new StringBuffer();
		formHtml.append("<form id=\"_alipaysubmit_\" name=\"alipaysubmit\" action=\"");
		String biz_content = (String) orderInfo.remove("biz_content");
		formHtml.append(getReqUrl()).append("?").append(DqUriVariables.getParameters(orderInfo)).append("\" method=\"")
				.append(method.name().toLowerCase()).append("\">");
		formHtml.append("<input type=\"hidden\" name=\"biz_content\" value=\'" + biz_content + "\'/>");
		formHtml.append("</form>");
		formHtml.append("<script>document.forms['_alipaysubmit_'].submit();</script>");

		return formHtml.toString();
	}

	/**
	 * 生成二维码支付
	 * 
	 * @param order
	 *            发起支付的订单信息
	 * @return 返回图片信息，支付时需要的
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BufferedImage generatePayQrCode(DqPayOrderDTO order) {

		Map<String, Object> orderInfo = orderInfo(order);

		// 预订单
		HashMap<String, Object> result = getHttpRequestTemplate()
				.postForObject(getReqUrl() + "?" + DqUriVariables.getParameters(orderInfo), null, HashMap.class);
		
		HashMap<String, Object> response = DqJSONUtils.parseObject(result.get(order.getTransactionType().getResponseKey()), HashMap.class) ;
		if (DqBaseUtils.equals(DqZfbPayValue.CODE_SUCCUSS, DqMapUtils.getString(response, DqZfbPayKey.CODE_KEY))) {
			return DqQrCodeUtil.writeInfoToJpgBuff(DqMapUtils.getString(response, DqZfbPayKey.QR__CODE_KEY));
		}
		throw DqBaseBusinessException.newInstance(DqMapUtils.getString(response, DqZfbPayKey.CODE_KEY), DqMapUtils.getString(response, DqZfbPayKey.MSG_KEY)).buildExceptionDetail(response);

	}

	/**
	 * pos主动扫码付款(条码付)
	 * 
	 * @param order
	 *            发起支付的订单信息
	 * @return 支付结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> microPay(DqPayOrderDTO order) {
		Map<String, Object> orderInfo = orderInfo(order);
		// 预订单
		HashMap<String, Object> result = getHttpRequestTemplate()
				.postForObject(getReqUrl() + "?" + DqUriVariables.getParameters(orderInfo), null, HashMap.class);
		HashMap<String, Object> response = DqJSONUtils.parseObject(result.get(order.getTransactionType().getResponseKey()), HashMap.class);
		if (DqStringUtils.notEquals(DqZfbPayValue.CODE_SUCCUSS, DqMapUtils.getString(response, DqZfbPayKey.CODE_KEY))) {
			DqLogUtils.info("收款失败", order, LOG);
		}
		return result;
	}

	/**
	 * 交易查询接口
	 * 
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 返回查询回来的结果集，支付方原值返回
	 */
	@Override
	public Map<String, Object> queryPayResult(String tradeNo, String outTradeNo) {
		return secondaryInterface(tradeNo, outTradeNo, DqZfbTransactionType.QUERY);

	}

	/**
	 * 交易关闭接口
	 *
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 返回支付方交易关闭后的结果
	 */
	@Override
	public Map<String, Object> close(String tradeNo, String outTradeNo) {
		return secondaryInterface(tradeNo, outTradeNo, DqZfbTransactionType.CLOSE);
	}

	/**
	 * 申请退款接口 废弃
	 * 
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @param refundAmount
	 *            退款金额
	 * @param totalAmount
	 *            总金额
	 * @return 返回支付方申请退款后的结果
	 * @see #refund(RefundOrder, Callback)
	 */
	@Deprecated
	@Override
	public Map<String, Object> refund(String tradeNo, String outTradeNo, BigDecimal refundAmount,
			BigDecimal totalAmount) {
		return refund(new DqRefundOrderDTO(tradeNo, outTradeNo, refundAmount, totalAmount));
	}

	/**
	 * 申请退款接口
	 *
	 * @param refundOrder
	 *            退款订单信息
	 * @return 返回支付方申请退款后的结果
	 */
	@Override
	public Map<String, Object> refund(DqRefundOrderDTO refundOrder) {
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters(DqZfbTransactionType.REFUND);

		Map<String, Object> bizContent = getBizContent(refundOrder.getTradeNo(), refundOrder.getOutTradeNo(), null);
		if (DqStringUtils.isNotEmpty(refundOrder.getRefundNo())) {
			bizContent.put(DqZfbPayKey.OUT__REQUEST__NO_KEY, refundOrder.getRefundNo());
		}
		bizContent.put(DqZfbPayKey.REFUND__AMOUNT_KEY, refundOrder.getRefundAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
		// 设置请求参数的集合
		parameters.put(DqZfbPayKey.BIZ__CONTENT_KEY, DqJSONUtils.parseObject(bizContent, String.class));
		// 设置签名
		setSign(parameters);
		return getRequestResult(parameters, DqZfbTransactionType.REFUND);
	}

	/**
	 * 查询退款
	 *
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 返回支付方查询退款后的结果
	 */
	@Override
	public Map<String, Object> queryRefundResult(DqOrderQuery dqOrderQuery) {
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters(DqZfbTransactionType.REFUNDQUERY);
		
		Map<String, Object> contentMap = new HashMap<>();
		contentMap.put(DqZfbPayKey.OUT__REQUEST__NO_KEY, dqOrderQuery.getRefundTradeNo());
		// 设置请求参数的集合
		parameters.put(DqZfbPayKey.BIZ__CONTENT_KEY, getContentToJson(dqOrderQuery.getTradeNo(), dqOrderQuery.getOutTradeNo(), contentMap));
		// 设置签名
		setSign(parameters);
		return getRequestResult(parameters, DqZfbTransactionType.REFUNDQUERY);
	}

	/**
	 * <p>下载对账单</p>
	 * 
	 * <pre>
	 * 	这个接口是下载离线账单的，需要T+1天生成账单，不能查询当日或者是当月的账单，如果日期是当天或者是当月的会返回“参数不合法
	 *  下载对账单地址接口只有当面付接口可以下载trade类型的账单，其他支付接口只能下载signcustomer 这个类型的 
	 * </pre>
	 * @param billDate : 账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型：trade、signcustomer；
	 *            trade指商户基于支付宝交易收单的业务账单；signcustomer是指基于商户支付宝余额收入及支出等资金变动的帐务账单；
	 * @param billType : 账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
	 * @return 返回支付方下载对账单的结果
	 */
	@Override
	public Map<String, Object> downLoadBill(Date billDate, String billType) {
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters(DqZfbTransactionType.DOWNLOADBILL);

		Map<String, Object> bizContent = new TreeMap<>();
		bizContent.put(DqZfbPayKey.BILL__TYPE_KEY, billType);
		// 目前只支持日账单
		String fomatDate = DqDateFormatUtils.format(billDate, DqDateFormatUtils.FORMAT_NORMAL_DAY, TimeZone.getTimeZone(DqDateFormatUtils.EAST_EIGHT_TIME_ZONE));
		bizContent.put(DqZfbPayKey.BILL__DATE_KEY, fomatDate);
		// 设置请求参数的集合
		parameters.put(DqZfbPayKey.BIZ__CONTENT_KEY, JSON.toJSONString(bizContent));
		// 设置签名
		setSign(parameters);
		return getRequestResult(parameters, DqZfbTransactionType.DOWNLOADBILL);
	}

	/**
	 *
	 * @param tradeNoOrBillDate
	 *            支付平台订单号或者账单类型， 具体请 类型为{@link String }或者 {@link Date }
	 *            ，类型须强制限制，类型不对应则抛出异常{@link PayErrorException}
	 * @param outTradeNoBillType
	 *            商户单号或者 账单类型
	 * @param transactionType
	 *            交易类型
	 * @return 返回支付方对应接口的结果
	 */
	@Override
	public Map<String, Object> secondaryInterface(Object tradeNoOrBillDate, String outTradeNoBillType,
			DqTransactionType transactionType) {

		if (transactionType == DqZfbTransactionType.REFUND) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.PAY_GENERAL_INTERFACE_NOT_SUPPORT);
		}

		if (transactionType == DqZfbTransactionType.DOWNLOADBILL) {
			if (tradeNoOrBillDate instanceof Date) {
				return downLoadBill((Date) tradeNoOrBillDate, outTradeNoBillType);
			}
			throw DqBaseBusinessException.newInstance(DqBaseErrorCode.ILLICIT_TYPE_EXCEPTION);
		}

		if (!(tradeNoOrBillDate instanceof String)) {
			throw DqBaseBusinessException.newInstance(DqBaseErrorCode.ILLICIT_TYPE_EXCEPTION);
		}
		
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters(transactionType);
		Map<String, Object> contentMap = null;
		if (transactionType == DqZfbTransactionType.REFUNDQUERY) {
			contentMap = new HashMap<>();
			contentMap.put(DqZfbPayKey.OUT__REQUEST__NO_KEY, outTradeNoBillType);
		}
		// 设置请求参数的集合
		parameters.put(DqZfbPayKey.BIZ__CONTENT_KEY, getContentToJson(tradeNoOrBillDate.toString(), outTradeNoBillType, contentMap));
		// 设置签名
		setSign(parameters);
		return getRequestResult(parameters, transactionType);
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
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters(DqZfbTransactionType.TRANS);

		Map<String, Object> bizContent = new TreeMap<String, Object>();
		bizContent.put(DqZfbPayKey.OUT__BIZ__NO_KEY, order.getOutNo());
		bizContent.put(DqZfbPayKey.PAYEE__TYPE_KEY, "ALIPAY_LOGONID");
		bizContent.put(DqZfbPayKey.PAYEE__ACCOUNT_KEY, order.getPayeeAccount());
		bizContent.put(DqZfbPayKey.AMOUNT_KEY, order.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
		bizContent.put(DqZfbPayKey.PAYER__SHOW__NAME_KEY, order.getPayerName());
		bizContent.put(DqZfbPayKey.PAYEE__REAL__NAME_KEY, order.getPayeeName());
		bizContent.put(DqZfbPayKey.REMARK_KEY, order.getRemark());
		// 设置请求参数的集合
		parameters.put(DqZfbPayKey.BIZ__CONTENT_KEY, JSON.toJSONString(bizContent));
		// 设置签名
		setSign(parameters);
		return getRequestResult(parameters, DqZfbTransactionType.TRANS);
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
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters(DqZfbTransactionType.TRANS_QUERY);

		Map<String, Object> bizContent = new TreeMap<String, Object>();
		if (DqStringUtils.isEmpty(outNo)) {
			bizContent.put(DqZfbPayKey.ORDER__ID_KEY, tradeNo);
		} else {
			bizContent.put(DqZfbPayKey.OUT__BIZ__NO_KEY, outNo);
		}
		// 设置请求参数的集合
		parameters.put(DqZfbPayKey.BIZ__CONTENT_KEY, JSON.toJSONString(bizContent));
		return getRequestResult(parameters, DqZfbTransactionType.TRANS_QUERY);
	}
	
	/**
	 * 支付宝创建订单信息 create the order info
	 *
	 * @param order
	 *            支付订单
	 * @return 返回支付宝预下单信息
	 * @see DqPayOrderDTO 支付订单信息
	 */
	private Map<String, Object> getOrder(DqPayOrderDTO order) {

		Map<String, Object> orderInfo = getPublicParameters(order.getTransactionType());

		orderInfo.put(DqZfbPayKey.NOTIFY__URL_KEY, payConfigStorage.getNotifyUrl());
		orderInfo.put(DqZfbPayKey.FORMAT_KEY, DqZfbPayValue.JSON);

		Map<String, Object> bizContent = new TreeMap<>();
		bizContent.put(DqZfbPayKey.BODY_KEY, order.getBody());
		bizContent.put(DqZfbPayKey.SELLER__ID_KEY, payConfigStorage.getSeller());
		bizContent.put(DqZfbPayKey.SUBJECT_KEY, order.getSubject());
		bizContent.put(DqZfbPayKey.OUT__TRADE__NO_KEY, order.getOutTradeNo());
		bizContent.put(DqZfbPayKey.TOTAL__AMOUNT_KEY, order.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		switch ((DqZfbTransactionType) order.getTransactionType()) {
		case DIRECT:
			bizContent.put(DqZfbPayKey.PRODUCT__CODE_KEY, DqZfbProductCode.FAST_INSTANT_TRADE_PAY);
			orderInfo.put(DqZfbPayKey.RETURN__URL_KEY, payConfigStorage.getReturnUrl());
			break;
		case WAP:
			bizContent.put(DqZfbPayKey.PRODUCT__CODE_KEY, DqZfbProductCode.QUICK_WAP_PAY);
			orderInfo.put(DqZfbPayKey.RETURN__URL_KEY, payConfigStorage.getReturnUrl());
			break;
		case BAR_CODE:
		case WAVE_CODE:
			bizContent.put(DqZfbPayKey.SCENE_KEY, DqStringUtils.lowerCase(order.getTransactionType().toString()));
			bizContent.put(DqZfbPayKey.PRODUCT__CODE_KEY, DqZfbProductCode.FACE_TO_FACE_PAYMENT);
			bizContent.put(DqZfbPayKey.AUTH__CODE_KEY, order.getAuthCode());
			break;
		default:
			if (order.getTransactionType() != DqZfbTransactionType.SWEEPPAY) {
				bizContent.put(DqZfbPayKey.PRODUCT__CODE_KEY, DqZfbProductCode.QUICK_MSECURITY_PAY);
			}
		}
		orderInfo.put(DqZfbPayKey.BIZ__CONTENT_KEY, DqJSONUtils.parseObject(bizContent, String.class));
		return orderInfo;
	}

	/**
	 * 获取公共请求参数
	 * 
	 * @param transactionType
	 *            交易类型
	 * @return 放回公共请求参数
	 */
	private Map<String, Object> getPublicParameters(DqTransactionType transactionType) {
		Map<String, Object> orderInfo = new TreeMap<>();
		orderInfo.put(DqZfbPayKey.APP__ID_KEY, payConfigStorage.getAppid());
		orderInfo.put(DqZfbPayKey.METHOD_KEY, transactionType.getMethod());
		orderInfo.put(DqZfbPayKey.CHARSET_KEY, payConfigStorage.getInputCharset());
		String timestampStr = DqDateFormatUtils.format(DqDateUtils.getCurrentDate(), DqDateFormatUtils.FORMAT_NORMAL, TimeZone.getTimeZone(DqDateFormatUtils.EAST_EIGHT_TIME_ZONE));
		orderInfo.put(DqZfbPayKey.TIMESTAMP_KEY, timestampStr);
		orderInfo.put(DqZfbPayKey.VERSION_KEY, "1.0");
		return orderInfo;
	}

	/**
	 * 获取biz_content。请求参数的集合 不包含下载账单
	 * 
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @param bizContent
	 *            请求参数的集合
	 * @return 请求参数的集合 不包含下载账单
	 */
	private Map<String, Object> getBizContent(String tradeNo, String outTradeNo, Map<String, Object> bizContent) {
		if (null == bizContent) {
			bizContent = new TreeMap<>();
		}
		if (null != outTradeNo) {
			bizContent.put(DqZfbPayKey.OUT__TRADE__NO_KEY, outTradeNo);
		}
		if (null != tradeNo) {
			bizContent.put(DqZfbPayKey.TRADE__NO_KEY, tradeNo);
		}
		return bizContent;
	}

	/**
	 * 获取biz_content。不包含下载账单
	 * 
	 * @param tradeNo
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 获取biz_content。不包含下载账单
	 */
	private String getContentToJson(String tradeNo, String outTradeNo, Map<String, Object> contentMap) {
		return DqJSONUtils.parseObject(getBizContent(tradeNo, outTradeNo, contentMap), String.class);
	}
	/**
	 * 生成并设置签名
	 * 
	 * @param parameters
	 *            请求参数
	 * @return 请求参数
	 */
	private Map<String, Object> setSign(Map<String, Object> parameters) {
		parameters.put(DqZfbPayKey.SIGN__TYPE_KEY, payConfigStorage.getSignType());
		String sign = createSign(DqSignUtils.parameterText(parameters, DqSymbol.SINGLE_AND, DqZfbPayKey.SIGN_KEY),
				payConfigStorage.getInputCharset());
		parameters.put(DqZfbPayKey.SIGN_KEY, sign);
		return parameters;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> verifyResult(Map<String, Object> resultMap, DqTransactionType dqTransactionType) {
		Map<String, Object> verifyMap = new HashMap<>();
		if (DqStringUtils.isEmpty(dqTransactionType.getResponseKey())){
			verifyMap = resultMap;
		}else {
			verifyMap = DqJSONUtils.parseObject(resultMap.get(dqTransactionType.getResponseKey()), HashMap.class);
			verifyMap.put(DqPayKey.SIGN_KEY, DqMapUtils.getString(resultMap, DqPayKey.SIGN_KEY));
		}
		if (DqStringUtils.notEquals(DqZfbPayValue.CODE_SUCCUSS, DqMapUtils.getString(verifyMap, DqZfbPayKey.CODE_KEY))) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.PAY_FAILURE).buildExceptionDetail(verifyMap);
		}
		
		return verifyMap;
	}
	
	/** 获取请求结果 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getRequestResult(Map<String, Object> parameters, DqTransactionType dqTransactionType) {
		Map<String, Object> resultMap =  getHttpRequestTemplate().postForObject(getReqUrl() + "?" + DqUriVariables.getParameters(parameters),
				null, HashMap.class);
//		结果校验
		DqLogUtils.info("获取请求结果", resultMap, LOG);
		return verifyResult(resultMap, dqTransactionType);
	}
}
