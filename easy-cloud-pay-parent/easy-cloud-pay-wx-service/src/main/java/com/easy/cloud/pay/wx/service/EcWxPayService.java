package com.easy.cloud.pay.wx.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyStore;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TimeZone;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.http.constant.EcHttpConstant.EcMethodType;
import com.easy.cloud.core.common.http.pojo.dto.EcHttpConfigStorageDTO;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.qrcode.utils.EcQrCodeUtil;
import com.easy.cloud.core.common.sign.utils.EcSignUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.common.xml.utils.EcXMLUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.pay.core.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.core.payment.constant.EcPayErrorCodeEnum;
import com.easy.cloud.pay.core.payment.constant.EcWxPayConstant.EcWxPayKey;
import com.easy.cloud.pay.core.payment.constant.EcWxPayConstant.EcWxPayValue;
import com.easy.cloud.pay.core.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.core.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.core.payment.service.EcPayServiceAbstract;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayMessageDTO;
import com.easy.cloud.pay.core.paymessage.pojo.dto.EcPayOutMessageDTO;
import com.easy.cloud.pay.core.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.core.transaction.pojo.dto.EcTransferOrderDTO;
import com.easy.cloud.pay.wx.pojo.bo.EcWxTransactionType;
import com.easy.cloud.pay.wx.pojo.query.EcWxOrderQuery;

/**
 * 微信支付服务
 */
public class EcWxPayService extends EcPayServiceAbstract {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	/** 提供默认构造--只是为了反射用 */
	public EcWxPayService() {
		
	}
	/**
	 * 创建支付服务
	 * 
	 * @param payConfigStorage
	 *            微信对应的支付配置
	 */
	public EcWxPayService(EcPayConfigStorageInf payConfigStorage) {
		super(payConfigStorage);
	}

	/**
	 * 创建支付服务
	 * 
	 * @param payConfigStorage
	 *            微信对应的支付配置
	 * @param configStorage
	 *            微信对应的网络配置，包含代理配置、ssl证书配置
	 */
	public EcWxPayService(EcPayConfigStorageInf payConfigStorage, EcHttpConfigStorageDTO configStorage) {
		super(payConfigStorage, configStorage);
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
//		校验是否成功
		if (EcWxPayValue.isNotSUCCESS(params.get(EcWxPayKey.RETURN__CODE_KEY))){
			EcLogUtils.debug(String.format("微信支付异常：return_code=%s,参数集=%s", params.get(EcWxPayKey.RETURN__CODE_KEY)), params, LOG);
			return false;
		}
//		校验签名信息是否为空
		if (EcBaseUtils.isNull(params.get(EcWxPayKey.SIGN_KEY))) {
			EcLogUtils.debug("微信支付异常：签名为空！out_trade_no=" + params.get("out_trade_no"), params, LOG);
			return false;
		}
		try {
			return signVerify(params, EcMapUtils.getString(params, EcWxPayKey.SIGN_KEY)) && verifySource(EcMapUtils.getString(params, EcWxPayKey.OUT__TRADE__NO_KEY));
		} catch (Exception e) {
			EcLogUtils.error("签名校验异常", e, LOG);
		}
		return false;
	}

	/**
	 * 微信是否也需要再次校验来源，进行订单查询
	 *
	 * @param id
	 *            商户单号
	 * @return true通过
	 */
	@Override
	public boolean verifySource(String id) {
		return true;
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
	@Override
	public boolean signVerify(Map<String, Object> params, String sign) {
		return EcSignUtils.valueOf(payConfigStorage.getSignType()).verify(params, sign,
				"&key=" + payConfigStorage.getKeyPrivate(), payConfigStorage.getInputCharset());
	}

	/**
	 * 微信统一下单接口
	 *
	 * @param order
	 *            支付订单集
	 * @return 下单结果
	 */
	public Map<String, Object> unifiedOrder(EcPayOrderDTO order) {
		//// 统一下单
		Map<String, Object> parameters = getPublicParameters();
		parameters.put(EcWxPayKey.BODY_KEY, order.getSubject());// 购买支付信息
		parameters.put(EcWxPayKey.OUT__TRADE__NO_KEY, order.getOutTradeNo());// 订单号
		String spbillCreateIp = EcStringUtils.isEmpty(order.getSpbillCreateIp()) ? EcWxPayValue.SPBILL_CREATE_IP_DEFAULT : order.getSpbillCreateIp();
		parameters.put(EcWxPayKey.SPBILL__CREATE__IP_KEY, spbillCreateIp);
		parameters.put(EcWxPayKey.TOTAL__FEE_KEY, order.getPriceOfCent());// 总金额单位为分
		parameters.put(EcWxPayKey.ATTACH_KEY, order.getBody());
		parameters.put(EcWxPayKey.NOTIFY__URL_KEY, payConfigStorage.getNotifyUrl());
		parameters.put(EcWxPayKey.TRADE__TYPE_KEY, order.getTransactionType().getType());
		((EcWxTransactionType) order.getTransactionType()).setAttribute(parameters, order);

		String sign = createSign(EcSignUtils.parameterText(parameters), payConfigStorage.getInputCharset());
		parameters.put(EcWxPayKey.SIGN_KEY, sign);

		String requestXML = EcXMLUtils.getXmlStrFromMap(parameters);
		EcLogUtils.debug("请求的requestXML", requestXML, LOG);
		// 调起支付的参数列表
		@SuppressWarnings("unchecked")
		Map<String, Object> result = requestTemplate.postForObject(getUrl(order.getTransactionType()), requestXML, HashMap.class);
		
//		不成功抛出异常
		if (EcWxPayValue.isNotSUCCESS(result.get(EcWxPayKey.RETURN__CODE_KEY))) {
			EcLogUtils.error("订单创建失败", result, LOG);
			throw EcBaseBusinessException.newInstance(EcMapUtils.getString(result, EcWxPayKey.RETURN__CODE_KEY),
					EcMapUtils.getString(result, EcWxPayKey.RETURN__MSG_KEY));
		}
		return result;
	}

	/**
	 * 
	 * <p>
	 * 微信公众号支付
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 是
	 *     dqPayOrderDTO.EcWxTransactionType : EcWxTransactionType.JSAPI : 支付价格 : 交易类型
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return EcBaseServiceResult
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午2:19:55
	 */
	@Override
	public Map<String, Object> orderInfo(EcPayOrderDTO order) {

		//// 统一下单
		Map<String, Object> result = unifiedOrder(order);

		EcTransactionType transactionType = order.getTransactionType();
		// 如果是扫码支付或者刷卡付无需处理，直接返回
		if (EcWxTransactionType.isNATIVE(transactionType)
				|| EcWxTransactionType.isMICROPAY(transactionType)
				|| EcWxTransactionType.isMWEB(transactionType)) {
			return result;
		}

		SortedMap<String, Object> params = new TreeMap<String, Object>();

		if (EcWxTransactionType.isJSAPI(transactionType)) {
			params.put(EcWxPayKey.SIGN_TYPE_KEY, payConfigStorage.getSignType());
			params.put(EcWxPayKey.APP_ID_KEY, payConfigStorage.getAppid());
			// 此处必须为String类型 否则调用jsapi时会提示找不到timeStamp
			params.put(EcWxPayKey.TIME_STAMP_KEY, EcDateUtils.getCurrentTimeSecStr());
			params.put(EcWxPayKey.NONCE_STR_KEY, result.get(EcWxPayKey.NONCE__STR_KEY));
			params.put(EcWxPayKey.PACKAGE_KEY, EcWxPayKey.PREPAY__ID_KEY + "=" + result.get(EcWxPayKey.PREPAY__ID_KEY));
		} else if (EcWxTransactionType.isAPP(transactionType)) {
			params.put(EcWxPayKey.PARTNERID_KEY, payConfigStorage.getPid());
			params.put(EcWxPayKey.APPID_KEY, payConfigStorage.getAppid());
			params.put(EcWxPayKey.PREPAYID_KEY, result.get(EcWxPayKey.PREPAY__ID_KEY));
			params.put(EcWxPayKey.TIMESTAMP_KEY, EcDateUtils.getCurrentTimeSecStr());
			params.put(EcWxPayKey.NONCE_STR_KEY, result.get(EcWxPayKey.NONCE__STR_KEY));
			params.put(EcWxPayKey.PACKAGE_KEY, EcWxPayValue.APP_PACKAGE_VALUE);
		}
		String paySign = createSign(EcSignUtils.parameterText(params), payConfigStorage.getInputCharset());
		params.put(EcWxPayKey.SIGN_KEY, paySign);
		return params;

	}

	/**
	 * 签名
	 *
	 * @param content
	 *            需要签名的内容 不包含key
	 * @param characterEncoding
	 *            字符编码
	 * @return 签名结果
	 */
	@Override
	public String createSign(String content, String characterEncoding) {
		
		return EcSignUtils.valueOf(EcStringUtils.upperCase(payConfigStorage.getSignType()))
				.createSign(content, "&key=" + payConfigStorage.getKeyPrivate(), characterEncoding).toUpperCase();
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
		TreeMap<String, Object> map = new TreeMap<>();
		try {
			return EcXMLUtils.getMapFromInputStream(is, map);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
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
		return EcPayOutMessageDTO.XML().code(code.toUpperCase()).content(message).build();
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
		return EcPayOutMessageDTO.XML().code(EcWxPayValue.SUCCESS).content("成功").build();
	}

	/**
	 * 获取输出消息，用户返回给支付端, 针对于web端
	 *
	 * @param orderInfo
	 *            发起支付的订单信息
	 * @param method
	 *            请求方式 "post" "get",
	 * @return 获取输出消息，用户返回给支付端, 针对于web端
	 * @see EcMethodType 请求类型
	 */
	@SuppressWarnings("deprecation")
	@Override
	public String buildRequest(Map<String, Object> orderInfo, EcMethodType method) {
		if (EcWxPayValue.isNotSUCCESS(orderInfo.get(EcWxPayKey.RETURN__CODE_KEY))) {
			throw EcBaseBusinessException.newInstance(EcMapUtils.getString(orderInfo, EcWxPayKey.RETURN__CODE_KEY),EcMapUtils.getString(orderInfo, EcWxPayKey.RETURN__MSG_KEY));
		}
		if (EcWxTransactionType.isMWEB(EcMapUtils.getString(orderInfo, EcWxPayKey.TRADE__TYPE_KEY))) {
//			需要被格式化得字符串
			String needBeFormatStr = "<script type=\"text/javascript\">location.href=\"%s%s\"</script>";
			String formatReturnUrl = EcStringUtils.EMPTY;
			
			if (EcStringUtils.isNotEmpty(payConfigStorage.getReturnUrl())){
				formatReturnUrl = "&redirect_url=" + URLEncoder.encode(payConfigStorage.getReturnUrl());
			}
			return String.format(needBeFormatStr, orderInfo.get(EcWxPayKey.MWEB__URL_KEY), formatReturnUrl);
		}
		throw new UnsupportedOperationException();

	}

	/**
	 * 获取输出二维码，用户返回给支付端,
	 *
	 * @param order
	 *            发起支付的订单信息
	 * @return 返回图片信息，支付时需要的
	 */
	@Override
	public BufferedImage generatePayQrCode(EcPayOrderDTO order) {
		Map<String, Object> orderInfo = orderInfo(order);
		EcLogUtils.info("二维码对象", orderInfo, LOG);
		// 获取对应的支付账户操作工具（可根据账户id）
		if (EcWxPayValue.isNotSUCCESS(orderInfo.get(EcWxPayKey.RESULT__CODE_KEY))) {
			throw EcBaseBusinessException.newInstance(EcMapUtils.getString(orderInfo, EcWxPayKey.RESULT__CODE_KEY),
					EcMapUtils.getString(orderInfo, EcWxPayKey.ERR__CODE_KEY)).buildExceptionDetail(orderInfo);
		}
		return EcQrCodeUtil.writeInfoToJpgBuff(EcMapUtils.getString(orderInfo, EcWxPayKey.CODE__URL_KEY));
	}

	/**
	 * 刷卡付,pos主动扫码付款
	 *
	 * @param order
	 *            发起支付的订单信息
	 * @return 返回支付结果
	 */
	@Override
	public Map<String, Object> microPay(EcPayOrderDTO order) {
		return orderInfo(order);
	}

	/**
	 * 交易查询接口
	 *
	 * @param transactionId
	 *            微信支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 返回查询回来的结果集，支付方原值返回
	 */
	@Override
	public Map<String, Object> queryPayResult(String transactionId, String outTradeNo) {
		return secondaryInterface(transactionId, outTradeNo, EcWxTransactionType.QUERY);
	}

	/**
	 * 交易关闭接口
	 *
	 * @param transactionId
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 返回支付方交易关闭后的结果
	 */
	@Override
	public Map<String, Object> close(String transactionId, String outTradeNo) {

		return secondaryInterface(transactionId, outTradeNo, EcWxTransactionType.CLOSE);
	}

	/**
	 * 申请退款接口
	 *
	 * @param refundOrder
	 *            退款订单信息
	 * @return 返回支付方申请退款后的结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> refund(EcRefundOrderAbstractDTO refundOrder) {
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters();
		if (EcStringUtils.isNotEmpty(refundOrder.getTradeNo())) {
			parameters.put(EcWxPayKey.TRANSACTION__ID_KEY, refundOrder.getTradeNo());
		} else {
			parameters.put(EcWxPayKey.OUT__TRADE__NO_KEY, refundOrder.getOutTradeNo());
		}
		parameters.put(EcWxPayKey.OUT__REFUND__NO_KEY, refundOrder.getRefundNo());
		parameters.put(EcWxPayKey.TOTAL__FEE_KEY, refundOrder.getTotalAmountOfCent());
		parameters.put(EcWxPayKey.REFUND__FEE_KEY, refundOrder.getRefundAmountOfCent());
		parameters.put(EcWxPayKey.OP__USER__ID_KEY, payConfigStorage.getPid());
		parameters.put(EcWxPayKey.NONCE__STR_KEY, String.valueOf(System.currentTimeMillis()));
		// 设置签名
		setSign(parameters);
		Map<String, Object> retMap = requestTemplate.postForObject(getUrl(EcWxTransactionType.REFUND),
				EcXMLUtils.getXmlStrFromMap(parameters), HashMap.class);
		if (EcMapUtils.isEmpty(retMap)) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.PAY_FAILURE).buildExceptionDetail(retMap);
		}
//		Map<String, Object> retMap = EcJSONUtils.parseObject(wxPayRefund(refundOrder), HashMap.class);
		return retMap;
	}
	/**
     * @Author: HONGLINCHEN
     * @Description:微信退款   注意：：微信金额的单位是分 所以这里要X100 转成int是因为 退款的时候不能有小数点
     * @param merchantNumber 商户这边的订单号
     * @param wxTransactionNumber 微信那边的交易单号
     * @param totalFee 订单的金额
     * @Date: 2017-9-12 11:18
     */
    @SuppressWarnings("deprecation")
	public Object wxPayRefund(EcRefundOrderAbstractDTO refundOrder) {
        try{
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File("E:/tools/wx_pay/cert/apiclient_cert.p12"));
            try {
                keyStore.load(instream, payConfigStorage.getPid().toCharArray());
            }finally {
                instream.close();
            }
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, payConfigStorage.getPid().toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    sslcontext, new String[] { "TLSv1" }, null,
                    SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf).build();
            // HttpGet httpget = new
            // HttpGet("https://api.mch.weixin.qq.com/secapi/pay/refund");
            HttpPost httppost = new HttpPost("https://api.mch.weixin.qq.com/secapi/pay/refund");
            //微信金额的单位是分 所以这里要X100 转成int是因为 退款的时候不能有小数点
//            String xml = WXPayUtil.wxPayRefund(merchantNumber,wxTransactionNumber,String.valueOf((int)(totalFee*100)));
         // 获取公共参数
    		Map<String, Object> parameters = getPublicParameters();
    		if (EcStringUtils.isNotEmpty(refundOrder.getTradeNo())) {
    			parameters.put(EcWxPayKey.TRANSACTION__ID_KEY, refundOrder.getTradeNo());
    		} else {
    			parameters.put(EcWxPayKey.OUT__TRADE__NO_KEY, refundOrder.getOutTradeNo());
    		}
    		parameters.put(EcWxPayKey.OUT__REFUND__NO_KEY, refundOrder.getRefundNo());
    		parameters.put(EcWxPayKey.TOTAL__FEE_KEY, refundOrder.getTotalAmountOfCent());
    		parameters.put(EcWxPayKey.REFUND__FEE_KEY, refundOrder.getRefundAmount());
    		parameters.put(EcWxPayKey.OP__USER__ID_KEY, payConfigStorage.getPid());
    		parameters.put(EcWxPayKey.NONCE__STR_KEY, String.valueOf(System.currentTimeMillis()));
    		// 设置签名
    		setSign(parameters);
            String xml = EcXMLUtils.getXmlStrFromMap(parameters);
            try {
                StringEntity se = new StringEntity(xml);
                httppost.setEntity(se);
                System.out.println("executing request" + httppost.getRequestLine());
                CloseableHttpResponse responseEntry = httpclient.execute(httppost);
                try {
                    HttpEntity entity = responseEntry.getEntity();
                    System.out.println(responseEntry.getStatusLine());
                    if (entity != null) {
                    	return EcXMLUtils.getMapFromInputStream(entity.getContent());
                    }
                    EntityUtils.consume(entity);
                }
                finally {
                    responseEntry.close();
                }
            }
            finally {
                httpclient.close();
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            JSONObject result = new JSONObject();
            result.put("status","error");
            result.put("msg",e.getMessage());
            return result;
        }
    }

	/**
	 * 查询退款
	 *
	 * @param transactionId
	 *            支付平台订单号
	 * @param outTradeNo
	 *            商户单号
	 * @return 返回支付方查询退款后的结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryRefundResult(EcOrderAbstractQuery dqOrderQuery) {
		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters();
		if (EcStringUtils.isNotEmpty(dqOrderQuery.getOutTradeNo())) {
			parameters.put(EcWxPayKey.OUT__TRADE__NO_KEY, dqOrderQuery.getOutTradeNo());
		} else {
			parameters.put(EcWxPayKey.TRANSACTION__ID_KEY, dqOrderQuery.getTradeNo());
		}
		parameters.put(EcWxPayKey.OUT__REFUND__NO_KEY, dqOrderQuery.getRefundTradeNo());
		// 设置签名
		setSign(parameters);
		return requestTemplate.postForObject(getUrl(EcWxTransactionType.REFUNDQUERY), EcXMLUtils.getXmlStrFromMap(parameters),
				HashMap.class);
	}

	/**
	 * 目前只支持日账单
	 *
	 * @param billDate
	 *             账单时间：日账单格式为yyyy-MM-dd，月账单格式为yyyy-MM。
	 * @param billType
	 * ALL，返回当日所有订单信息，默认值
	 * SUCCESS，返回当日成功支付的订单
	 * REFUND，返回当日退款订单
	 * RECHARGE_REFUND，返回当日充值退款订单
	 *           
	 * @return 返回支付方下载对账单的结果
	 */
	@Override
	public Map<String, Object> downLoadBill(EcOrderAbstractQuery dqOrderQuery) {

		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters();

		parameters.put(EcWxPayKey.BILL__TYPE_KEY, dqOrderQuery.getBillType());
		// 目前只支持日账单
		String billDateStr = EcDateFormatUtils.format(dqOrderQuery.getBillDate(), EcDateFormatUtils.FORMAT_SHORT, TimeZone.getTimeZone("GMT+8"));
		parameters.put(EcWxPayKey.BILL__DATE_KEY, billDateStr);

		// 设置签名
		setSign(parameters);
		String respStr = requestTemplate.postForObject(getUrl(EcWxTransactionType.DOWNLOADBILL),
				EcXMLUtils.getXmlStrFromMap(parameters), String.class);
		if (respStr.indexOf(EcSymbol.LESS_THAN) == 0) {
			Map<String, Object> errorMap = EcXMLUtils.getMapFromXmlStr(respStr);
			if (EcBaseUtils.notEquals(errorMap.get(EcWxPayKey.RETURN__CODE_KEY), EcWxPayValue.SUCCESS)) {
				throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.PAY_FAILURE).buildExceptionDetail(errorMap);
			}
		}
		
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put(EcWxPayKey.RETURN__CODE_KEY, EcWxPayValue.SUCCESS);
		retMap.put(EcWxPayKey.RETURN__MSG_KEY, EcWxPayValue.OK);
		retMap.put(EcWxPayKey.DATA_KEY, respStr);
		return retMap;
	}

	/**
	 * @param transactionIdOrBillDate
	 *            支付平台订单号或者账单类型， 具体请 类型为{@link String }或者 {@link Date }
	 *            ，类型须强制限制，类型不对应则抛出异常{@link PayErrorException}
	 * @param outTradeNoBillType
	 *            商户单号或者 账单类型
	 * @param transactionType
	 *            交易类型
	 * @return 返回支付方对应接口的结果
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> secondaryInterface(Object transactionIdOrBillDate, String outTradeNoBillType,
			EcTransactionType transactionType) {

		if (transactionType == EcWxTransactionType.REFUND) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.PAY_GENERAL_INTERFACE_NOT_SUPPORT);
		}

		if (transactionType == EcWxTransactionType.DOWNLOADBILL) {
			if (transactionIdOrBillDate instanceof Date) {
				EcWxOrderQuery ecWxOrderQuery = new EcWxOrderQuery();
				ecWxOrderQuery.setTradeNoOrBillDate(transactionIdOrBillDate);
				ecWxOrderQuery.setOutTradeNoBillType(outTradeNoBillType);
				return downLoadBill(ecWxOrderQuery);
			}
			throw EcBaseBusinessException.newInstance(EcBaseErrorCodeEnum.ILLICIT_TYPE_EXCEPTION);
		}

		if (!(null == transactionIdOrBillDate || transactionIdOrBillDate instanceof String)) {
			throw EcBaseBusinessException.newInstance(EcBaseErrorCodeEnum.ILLICIT_TYPE_EXCEPTION);
		}

		// 获取公共参数
		Map<String, Object> parameters = getPublicParameters();
		if (EcStringUtils.isEmpty((String) transactionIdOrBillDate)) {
			parameters.put(EcWxPayKey.OUT__TRADE__NO_KEY, outTradeNoBillType);
		} else {
			parameters.put(EcWxPayKey.TRANSACTION__ID_KEY, transactionIdOrBillDate);
		}
		// 设置签名
		setSign(parameters);
		return requestTemplate.postForObject(getUrl(transactionType), EcXMLUtils.getXmlStrFromMap(parameters),
				HashMap.class);
	}

	/**
	 * 
	 * <p>
	 * 转账
	 * </p>
	 * <p>
	 *  采用标准RSA算法，公钥由微信侧提供,将公钥信息配置在EcPayConfigStorage#setKeyPublic(String)
	 * </p>
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqTransferOrderDTO.payeeAccount : 468555xx : enc_bank_no，收款方银行卡号 : 是
	 *     dqTransferOrderDTO.payeeName : 张三 : 收款方用户名  : 是
	 *     dqTransferOrderDTO.bankStr : ACBC : 收款方开户行枚举字符串 : 是
	 *     dqTransferOrderDTO.amount : 0.01 : 转账金额 : 是
	 *     dqTransferOrderDTO.outNo : WXTON2014578... : partner_trade_no,商户转账订单号 : 是
	 *     dqTransferOrderDTO.remark : 1 : 转账备注, 非必填 : 是
	 * </pre>
	 *
	 * @param dqOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回对应的转账结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> transfer(EcTransferOrderDTO order) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		// 转账到余额
		// parameters.put("mch_appid", payConfigStorage.getAppid());
		parameters.put(EcWxPayKey.MCH__ID_KEY, payConfigStorage.getPid());
		parameters.put(EcWxPayKey.PARTNER__TRADE__NO_KEY, order.getOutNo());
		parameters.put(EcWxPayKey.NONCE__STR_KEY, EcSignUtils.randomStr());
		parameters.put(EcWxPayKey.ENC__BANK__NO_KEY, keyPublic(order.getPayeeAccount()));
		parameters.put(EcWxPayKey.ENC__TRUE__NAME_KEY, keyPublic(order.getPayeeName()));
		parameters.put(EcWxPayKey.BANK__CODE_KEY, order.getBank().getCode());
		parameters.put(EcWxPayKey.AMOUNT_KEY, order.getAmountOfCent()); // 以分为单位
		if (!EcStringUtils.isEmpty(order.getRemark())) {
			parameters.put(EcWxPayKey.DESC_KEY, order.getRemark());
		}
		parameters.put(EcWxPayKey.SIGN_KEY, EcSignUtils.valueOf(payConfigStorage.getSignType()).sign(parameters,
				payConfigStorage.getKeyPrivate(), payConfigStorage.getInputCharset()));
		return getHttpRequestTemplate().postForObject(getUrl(EcWxTransactionType.BANK), parameters, HashMap.class);
	}

	/**
	 * 转账
	 *
	 * @param outNo
	 *            商户转账订单号
	 * @param tradeNo
	 *            支付平台转账订单号
	 *
	 * @return 对应的转账订单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryTransferResult(String outNo, String tradeNo) {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put(EcWxPayKey.MCH__ID_KEY, payConfigStorage.getPid());
		parameters.put(EcWxPayKey.PARTNER__TRADE__NO_KEY, EcStringUtils.isEmpty(outNo) ? tradeNo : outNo);
		parameters.put(EcWxPayKey.NONCE__STR_KEY, EcSignUtils.randomStr());
		parameters.put(EcWxPayKey.SIGN_KEY, EcSignUtils.valueOf(payConfigStorage.getSignType()).sign(parameters,
				payConfigStorage.getKeyPrivate(), payConfigStorage.getInputCharset()));
		return getHttpRequestTemplate().postForObject(getUrl(EcWxTransactionType.QUERY_BANK), parameters,
				HashMap.class);
	}

	public String keyPublic(String content) {
		return EcSignUtils.RSA.createSign(content, payConfigStorage.getKeyPublic(), payConfigStorage.getInputCharset());
	}

	/**
	 * 根据交易类型获取url
	 *
	 * @param transactionType
	 *            交易类型
	 *
	 * @return 请求url
	 */
	private String getUrl(EcTransactionType transactionType) {

		return EcWxPayValue.URI + (payConfigStorage.isTest() ? EcWxPayValue.SANDBOXNEW : "") + transactionType.getMethod();
	}

	/**
	 * 获取公共参数
	 *
	 * @return 公共参数
	 */
	private Map<String, Object> getPublicParameters() {
		Map<String, Object> parameters = new TreeMap<String, Object>();
		parameters.put(EcWxPayKey.APPID_KEY, payConfigStorage.getAppid());
		parameters.put(EcWxPayKey.MCH__ID_KEY, payConfigStorage.getPid());
		parameters.put(EcWxPayKey.NONCE__STR_KEY, EcSignUtils.randomStr());
		return parameters;
	}

	/**
	 * 生成并设置签名
	 *
	 * @param parameters
	 *            请求参数
	 * @return 请求参数
	 */
	private Map<String, Object> setSign(Map<String, Object> parameters) {
		parameters.put(EcWxPayKey.SIGN__TYPE_KEY, payConfigStorage.getSignType());
		String sign = createSign(EcSignUtils.parameterText(parameters, EcSymbol.SINGLE_AND, EcWxPayKey.SIGN_KEY, EcWxPayKey.APP_ID_KEY),
				payConfigStorage.getInputCharset());
		parameters.put(EcWxPayKey.SIGN_KEY, sign);
		return parameters;
	}

}
