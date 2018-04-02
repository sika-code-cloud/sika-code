package com.easy.cloud.pay.zfb.service;

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
import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.http.constant.EcHttpConstant.EcMethodType;
import com.easy.cloud.core.common.http.pojo.dto.EcHttpConfigStorageDTO;
import com.easy.cloud.core.common.http.utils.EcUriVariables;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.qrcode.utils.EcQrCodeUtil;
import com.easy.cloud.core.common.sign.utils.EcSignUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.pay.common.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.common.payment.constant.EcPayConstant.EcPayKey;
import com.easy.cloud.pay.common.payment.constant.EcPayErrorCodeEnum;
import com.easy.cloud.pay.common.payment.constant.EcZfbPayConstant.EcZfbPayKey;
import com.easy.cloud.pay.common.payment.constant.EcZfbPayConstant.EcZfbPayValue;
import com.easy.cloud.pay.common.payment.constant.EcZfbPayConstant.EcZfbProductCode;
import com.easy.cloud.pay.common.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.common.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.common.payment.service.EcPayServiceAbstract;
import com.easy.cloud.pay.common.paymessage.pojo.dto.EcPayMessageDTO;
import com.easy.cloud.pay.common.paymessage.pojo.dto.EcPayOutMessageDTO;
import com.easy.cloud.pay.common.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.common.transaction.pojo.dto.EcTransferOrderDTO;
import com.easy.cloud.pay.zfb.pojo.bo.EcZfbTransactionType;
import com.easy.cloud.pay.zfb.pojo.query.EcZfbOrderQuery;

/**
 * 
 * <p>
 * 支付宝支付服务类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月27日 上午10:35:46
 */
public class EcZfbPayService extends EcPayServiceAbstract {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/**
	 * 正式测试环境
	 */
	private final static String httpsReqUrl = EcZfbPayValue.HTTPS_REQ_URL;
	/**
	 * 沙箱测试环境账号
	 */
	private final static String devReqUrl = EcZfbPayValue.DEV_REQ_URL;

	/**
	 * 获取对应的请求地址
	 * 
	 * @return 请求地址
	 */
	public String getReqUrl() {
		return payConfigStorage.isTest() ? devReqUrl : httpsReqUrl;
	}

	public EcZfbPayService(EcPayConfigStorageInf payConfigStorage, EcHttpConfigStorageDTO configStorage) {
		super(payConfigStorage, configStorage);
	}

	public EcZfbPayService(EcPayConfigStorageInf payConfigStorage) {
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

		if (EcBaseUtils.isNull(params.get(EcZfbPayKey.SIGN_KEY))) {
			LOG.debug("支付宝支付异常：params：" + params);
			return false;
		}
		String sign = EcMapUtils.getString(params, EcZfbPayKey.SIGN_KEY);
		String notify_id = EcMapUtils.getString(params, EcZfbPayKey.NOTIFY__ID_KEY);
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
				if (EcStringUtils.equals(EcZfbPayKey.SIGN_KEY, key)) {
					continue;
				}
				TreeMap response = new TreeMap((Map) params.get(key));
				LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
				linkedHashMap.put(EcZfbPayKey.CODE_KEY, response.remove(EcZfbPayKey.CODE_KEY));
				linkedHashMap.put(EcZfbPayKey.MSG_KEY, response.remove(EcZfbPayKey.MSG_KEY));
				linkedHashMap.putAll(response);
				return EcSignUtils.valueOf(payConfigStorage.getSignType()).verify(EcJSONUtils.parseObject(linkedHashMap, String.class),
						sign, payConfigStorage.getKeyPublic(), payConfigStorage.getInputCharset());
			}
		}
		return EcSignUtils.valueOf(payConfigStorage.getSignType()).verify(params, sign, payConfigStorage.getKeyPublic(),
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
	 * @see EcPayOrderDTO 支付订单信息
	 */
	@Override
	public Map<String, Object> orderInfo(EcPayOrderDTO order) {
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
	public EcPayOutMessageDTO getPayOutMessage(String code, String message) {
		return EcPayOutMessageDTO.TEXT().content(code.toLowerCase()).build();
	}

	/**
	 * 获取成功输出消息，用户返回给支付端 主要用于拦截器中返回
	 * 
	 * @param payMessage
	 *            支付回调消息
	 * @return 返回输出消息
	 */
	@Override
	public EcPayOutMessageDTO successPayOutMessage(EcPayMessageDTO payMessage) {
		return EcPayOutMessageDTO.TEXT().content(EcZfbPayValue.SUCCESS_CODE).build();
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
	public String buildRequest(Map<String, Object> orderInfo, EcMethodType method) {
		StringBuffer formHtml = new StringBuffer();
		formHtml.append("<form id=\"_alipaysubmit_\" name=\"alipaysubmit\" action=\"");
		String biz_content = (String) orderInfo.remove("biz_content");
		formHtml.append(getReqUrl()).append("?").append(EcUriVariables.getParameters(orderInfo)).append("\" method=\"")
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
	public BufferedImage generatePayQrCode(EcPayOrderDTO order) {

		Map<String, Object> orderInfo = orderInfo(order);

		// 预订单
		HashMap<String, Object> result = getHttpRequestTemplate()
				.postForObject(getReqUrl() + "?" + EcUriVariables.getParameters(orderInfo), null, HashMap.class);
		
		HashMap<String, Object> response = EcJSONUtils.parseObject(result.get(order.getTransactionType().getResponseKey()), HashMap.class) ;
		if (EcBaseUtils.equals(EcZfbPayValue.CODE_SUCCUSS, EcMapUtils.getString(response, EcZfbPayKey.CODE_KEY))) {
			return EcQrCodeUtil.writeInfoToJpgBuff(EcMapUtils.getString(response, EcZfbPayKey.QR__CODE_KEY));
		}
		throw EcBaseBusinessException.newInstance(EcMapUtils.getString(response, EcZfbPayKey.CODE_KEY), EcMapUtils.getString(response, EcZfbPayKey.MSG_KEY)).buildExceptionDetail(response);

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
	public Map<String, Object> microPay(EcPayOrderDTO order) {
		Map<String, Object> orderInfo = orderInfo(order);
		// 预订单
		HashMap<String, Object> result = getHttpRequestTemplate()
				.postForObject(getReqUrl() + "?" + EcUriVariables.getParameters(orderInfo), null, HashMap.class);
		HashMap<String, Object> response = EcJSONUtils.parseObject(result.get(order.getTransactionType().getResponseKey()), HashMap.class);
		if (EcStringUtils.notEquals(EcZfbPayValue.CODE_SUCCUSS, EcMapUtils.getString(response, EcZfbPayKey.CODE_KEY))) {
			EcLogUtils.info("收款失败", order, LOG);
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
		return secondaryInterface(tradeNo, outTradeNo, EcZfbTransactionType.QUERY);

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
		return secondaryInterface(tradeNo, outTradeNo, EcZfbTransactionType.CLOSE);
	}

	/**
	 * 申请退款接口
	 *
	 * @param refundOrder
	 *            退款订单信息
	 * @return 返回支付方申请退款后的结果
	 */
	@Override
	public Map<String, Object> refund(EcRefundOrderAbstractDTO refundOrder) {
//		构建签名参数
		refundOrder.buildSignatureParameters(payConfigStorage, EcZfbTransactionType.REFUND);
//		获取请求结果
		return getRequestResult(refundOrder.getSignatureParameters(), EcZfbTransactionType.REFUND);
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
	public Map<String, Object> queryRefundResult(EcOrderAbstractQuery dqOrderQuery) {
//		构建签名参数
		dqOrderQuery.buildSignatureParameters(payConfigStorage, EcZfbTransactionType.REFUNDQUERY);
//		获取请求结果
		return getRequestResult(dqOrderQuery.getSignatureParameters(), EcZfbTransactionType.REFUNDQUERY);
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
	public Map<String, Object> downLoadBill(EcOrderAbstractQuery dqOrderQuery) {
//		构建签名参数
		dqOrderQuery.buildSignatureParameters(payConfigStorage, EcZfbTransactionType.DOWNLOADBILL);
//		获取请求结果
		return getRequestResult(dqOrderQuery.getSignatureParameters(), EcZfbTransactionType.DOWNLOADBILL);
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
			EcTransactionType transactionType) {

		if (transactionType == EcZfbTransactionType.REFUND) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.PAY_GENERAL_INTERFACE_NOT_SUPPORT);
		}

		if (transactionType == EcZfbTransactionType.DOWNLOADBILL) {
			if (tradeNoOrBillDate instanceof Date) {
				EcOrderAbstractQuery ecOrderAbstractQuery = new EcZfbOrderQuery();
				ecOrderAbstractQuery.setTradeNoOrBillDate(tradeNoOrBillDate);
				ecOrderAbstractQuery.setOutTradeNoBillType(outTradeNoBillType);
				return downLoadBill(ecOrderAbstractQuery);
			}
			throw EcBaseBusinessException.newInstance(EcBaseErrorCodeEnum.ILLICIT_TYPE_EXCEPTION);
		}

		if (!(tradeNoOrBillDate instanceof String)) {
			throw EcBaseBusinessException.newInstance(EcBaseErrorCodeEnum.ILLICIT_TYPE_EXCEPTION);
		}
		
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters(transactionType);
		Map<String, Object> contentMap = null;
		if (transactionType == EcZfbTransactionType.REFUNDQUERY) {
			contentMap = new HashMap<>();
			contentMap.put(EcZfbPayKey.OUT__REQUEST__NO_KEY, outTradeNoBillType);
		}
		// 设置请求参数的集合
		parameters.put(EcZfbPayKey.BIZ__CONTENT_KEY, getContentToJson(tradeNoOrBillDate.toString(), outTradeNoBillType, contentMap));
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
	public Map<String, Object> transfer(EcTransferOrderDTO order) {
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters(EcZfbTransactionType.TRANS);

		Map<String, Object> bizContent = new TreeMap<String, Object>();
		bizContent.put(EcZfbPayKey.OUT__BIZ__NO_KEY, order.getOutNo());
		bizContent.put(EcZfbPayKey.PAYEE__TYPE_KEY, "ALIPAY_LOGONID");
		bizContent.put(EcZfbPayKey.PAYEE__ACCOUNT_KEY, order.getPayeeAccount());
		bizContent.put(EcZfbPayKey.AMOUNT_KEY, order.getAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
		bizContent.put(EcZfbPayKey.PAYER__SHOW__NAME_KEY, order.getPayerName());
		bizContent.put(EcZfbPayKey.PAYEE__REAL__NAME_KEY, order.getPayeeName());
		bizContent.put(EcZfbPayKey.REMARK_KEY, order.getRemark());
		// 设置请求参数的集合
		parameters.put(EcZfbPayKey.BIZ__CONTENT_KEY, JSON.toJSONString(bizContent));
		// 设置签名
		setSign(parameters);
		return getRequestResult(parameters, EcZfbTransactionType.TRANS);
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
		Map<String, Object> parameters = getPublicParameters(EcZfbTransactionType.TRANS_QUERY);

		Map<String, Object> bizContent = new TreeMap<String, Object>();
		if (EcStringUtils.isEmpty(outNo)) {
			bizContent.put(EcZfbPayKey.ORDER__ID_KEY, tradeNo);
		} else {
			bizContent.put(EcZfbPayKey.OUT__BIZ__NO_KEY, outNo);
		}
		// 设置请求参数的集合
		parameters.put(EcZfbPayKey.BIZ__CONTENT_KEY, JSON.toJSONString(bizContent));
		return getRequestResult(parameters, EcZfbTransactionType.TRANS_QUERY);
	}
	
	/**
	 * 支付宝创建订单信息 create the order info
	 *
	 * @param order
	 *            支付订单
	 * @return 返回支付宝预下单信息
	 * @see EcPayOrderDTO 支付订单信息
	 */
	private Map<String, Object> getOrder(EcPayOrderDTO order) {

		Map<String, Object> orderInfo = getPublicParameters(order.getTransactionType());

		orderInfo.put(EcZfbPayKey.NOTIFY__URL_KEY, payConfigStorage.getNotifyUrl());
		orderInfo.put(EcZfbPayKey.FORMAT_KEY, EcZfbPayValue.JSON);

		Map<String, Object> bizContent = new TreeMap<>();
		bizContent.put(EcZfbPayKey.BODY_KEY, order.getBody());
		bizContent.put(EcZfbPayKey.SELLER__ID_KEY, payConfigStorage.getSeller());
		bizContent.put(EcZfbPayKey.SUBJECT_KEY, order.getSubject());
		bizContent.put(EcZfbPayKey.OUT__TRADE__NO_KEY, order.getOutTradeNo());
		bizContent.put(EcZfbPayKey.TOTAL__AMOUNT_KEY, order.getPrice().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
		switch ((EcZfbTransactionType) order.getTransactionType()) {
		case DIRECT:
			bizContent.put(EcZfbPayKey.PRODUCT__CODE_KEY, EcZfbProductCode.FAST_INSTANT_TRADE_PAY);
			orderInfo.put(EcZfbPayKey.RETURN__URL_KEY, payConfigStorage.getReturnUrl());
			break;
		case WAP:
			bizContent.put(EcZfbPayKey.PRODUCT__CODE_KEY, EcZfbProductCode.QUICK_WAP_PAY);
			orderInfo.put(EcZfbPayKey.RETURN__URL_KEY, payConfigStorage.getReturnUrl());
			break;
		case BAR_CODE:
		case WAVE_CODE:
			bizContent.put(EcZfbPayKey.SCENE_KEY, EcStringUtils.lowerCase(order.getTransactionType().toString()));
			bizContent.put(EcZfbPayKey.PRODUCT__CODE_KEY, EcZfbProductCode.FACE_TO_FACE_PAYMENT);
			bizContent.put(EcZfbPayKey.AUTH__CODE_KEY, order.getAuthCode());
			break;
		default:
			if (order.getTransactionType() != EcZfbTransactionType.SWEEPPAY) {
				bizContent.put(EcZfbPayKey.PRODUCT__CODE_KEY, EcZfbProductCode.QUICK_MSECURITY_PAY);
			}
		}
		orderInfo.put(EcZfbPayKey.BIZ__CONTENT_KEY, EcJSONUtils.parseObject(bizContent, String.class));
		return orderInfo;
	}

	/**
	 * 获取公共请求参数
	 * 
	 * @param transactionType
	 *            交易类型
	 * @return 放回公共请求参数
	 */
	private Map<String, Object> getPublicParameters(EcTransactionType transactionType) {
		Map<String, Object> orderInfo = new TreeMap<>();
		orderInfo.put(EcZfbPayKey.APP__ID_KEY, payConfigStorage.getAppid());
		orderInfo.put(EcZfbPayKey.METHOD_KEY, transactionType.getMethod());
		orderInfo.put(EcZfbPayKey.CHARSET_KEY, payConfigStorage.getInputCharset());
		String timestampStr = EcDateFormatUtils.format(EcDateUtils.getCurrentDate(), EcDateFormatUtils.FORMAT_NORMAL, TimeZone.getTimeZone(EcDateFormatUtils.EAST_EIGHT_TIME_ZONE));
		orderInfo.put(EcZfbPayKey.TIMESTAMP_KEY, timestampStr);
		orderInfo.put(EcZfbPayKey.VERSION_KEY, "1.0");
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
			bizContent.put(EcZfbPayKey.OUT__TRADE__NO_KEY, outTradeNo);
		}
		if (null != tradeNo) {
			bizContent.put(EcZfbPayKey.TRADE__NO_KEY, tradeNo);
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
		return EcJSONUtils.parseObject(getBizContent(tradeNo, outTradeNo, contentMap), String.class);
	}
	/**
	 * 生成并设置签名
	 * 
	 * @param parameters
	 *            请求参数
	 * @return 请求参数
	 */
	private Map<String, Object> setSign(Map<String, Object> parameters) {
		parameters.put(EcZfbPayKey.SIGN__TYPE_KEY, payConfigStorage.getSignType());
		String sign = createSign(EcSignUtils.parameterText(parameters, EcSymbol.SINGLE_AND, EcZfbPayKey.SIGN_KEY),
				payConfigStorage.getInputCharset());
		parameters.put(EcZfbPayKey.SIGN_KEY, sign);
		return parameters;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> verifyResult(Map<String, Object> resultMap, EcTransactionType ecTransactionType) {
		Map<String, Object> verifyMap = new HashMap<>();
		if (EcStringUtils.isEmpty(ecTransactionType.getResponseKey())){
			verifyMap = resultMap;
		}else {
			verifyMap = EcJSONUtils.parseObject(resultMap.get(ecTransactionType.getResponseKey()), HashMap.class);
			verifyMap.put(EcPayKey.SIGN_KEY, EcMapUtils.getString(resultMap, EcPayKey.SIGN_KEY));
		}
		if (EcStringUtils.notEquals(EcZfbPayValue.CODE_SUCCUSS, EcMapUtils.getString(verifyMap, EcZfbPayKey.CODE_KEY))) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.PAY_FAILURE).buildExceptionDetail(verifyMap);
		}
		
		return verifyMap;
	}
	
	/** 获取请求结果 */
	@SuppressWarnings("unchecked")
	private Map<String, Object> getRequestResult(Map<String, Object> parameters, EcTransactionType ecTransactionType) {
		EcLogUtils.info("调用支付宝接口请求参数", parameters, LOG);
		Map<String, Object> resultMap =  getHttpRequestTemplate().postForObject(getReqUrl() + "?" + EcUriVariables.getParameters(parameters),
				null, HashMap.class);
//		结果校验
		EcLogUtils.info("获取请求结果", resultMap, LOG);
		return verifyResult(resultMap, ecTransactionType);
	}
}
