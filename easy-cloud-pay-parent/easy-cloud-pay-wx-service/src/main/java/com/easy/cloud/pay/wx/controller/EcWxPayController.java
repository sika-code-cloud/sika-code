
package com.easy.cloud.pay.wx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.pay.common.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.common.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.common.transaction.pojo.dto.EcTransferOrderDTO;
import com.easy.cloud.pay.wx.logic.EcWxPayLogic;
import com.easy.cloud.pay.wx.pojo.bo.EcWxTransactionType;
import com.easy.cloud.pay.wx.pojo.dto.EcWxRefundOrderDTO;

/**
 * 微信Controller
 */
@RestController
@RequestMapping("wx")
public class EcWxPayController extends EcBaseController {
	@Autowired
	private EcWxPayLogic ecWxPayLogic;

	/**
	 * 
	 * <p>
	 * 微信公众号支付
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     ecPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     ecPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     ecPayOrderDTO.openid : eraweea343 : 用户openid : 是
	 *     ecPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param ecPayOrderDTO
	 * @return EcBaseServiceResult
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping(value = "jsapiPay")
	public EcBaseServiceResult jsapiPay(EcPayOrderDTO ecPayOrderDTO) {
		return ecWxPayLogic.jsapiPay(ecPayOrderDTO, EcWxTransactionType.JSAPI);
	}

	/**
	 * 
	 * <p>
	 * 生成微信支付二维码
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     ecPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     ecPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     ecPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param ecPayOrderDTO
	 * @return EcBaseServiceResult
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping(value = "generatePayQrCode", produces = "image/jpeg;charset=UTF-8")
	public byte[] generatePayQrCode(EcPayOrderDTO ecPayOrderDTO) throws IOException {
		return ecWxPayLogic.generatePayQrCode(ecPayOrderDTO, EcWxTransactionType.NATIVE);
	}

	/**
	 * 
	 * <p>
	 * 跳到支付页面 针对H5支付
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     ecPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     ecPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     ecPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param ecPayOrderDTO
	 * @return 跳到支付页面
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping(value = "mWebPay", produces = "text/html;charset=UTF-8")
	public String mWebPay(EcPayOrderDTO ecPayOrderDTO, HttpServletRequest request) {
		return ecWxPayLogic.mWebPay(ecPayOrderDTO, request, EcWxTransactionType.MWEB);
	}

	/**
	 * 
	 * <p>
	 * 获取支付预订单信息--app支付
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     ecPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     ecPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     ecPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param ecPayOrderDTO
	 * @return 支付预订单信息
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping("appPay")
	public EcBaseServiceResult appPay(EcPayOrderDTO ecPayOrderDTO) {
		return ecWxPayLogic.appPay(ecPayOrderDTO, EcWxTransactionType.APP);
	}

	/**
	 * 
	 * <p>
	 * 微信刷卡支付--刷卡付,pos主动扫码付款(条码付)
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     ecPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     ecPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     ecPayOrderDTO.authCode : ewae : 授权码，条码等 : 是
	 *     ecPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param ecPayOrderDTO
	 * @return EcBaseServiceResult : 处理结果
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping(value = "microPay")
	public EcBaseServiceResult microPay(EcPayOrderDTO ecPayOrderDTO) throws IOException {
		return ecWxPayLogic.microPay(ecPayOrderDTO, EcWxTransactionType.MICROPAY);
	}

	/**
	 * 支付回调地址
	 *
	 * @param request
	 *
	 * @return
	 */
	@RequestMapping(value = "payCallBack")
	public String payCallBack(HttpServletRequest request) throws IOException {
		return ecWxPayLogic.payCallBack(request);
	}

	/**
	 * 
	 * <p>
	 * 查询 支付订单结果信息
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecPayOrderDTO.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     ecPayOrderDTO.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param ecOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 查询回来的结果集，支付方原值返回
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("queryPayResult")
	public EcBaseServiceResult queryPayResult(EcOrderAbstractQuery ecOrderQuery) {
		return ecWxPayLogic.queryPayResult(ecOrderQuery);
	}
	/**
	 * 
	 * <p>
	 * 交易关闭接口
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecPayOrderDTO.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     ecPayOrderDTO.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param ecOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 支付方交易关闭后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("close")
	public EcBaseServiceResult close(EcOrderAbstractQuery ecOrderQuery) {
		return ecWxPayLogic.close(ecOrderQuery);
	}

	/**
	 * 
	 * <p>
	 * 申请退款接口
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param refundOrderDTO : EcWxRefundOrderDTO : 订单查询对象
	 * @return EcBaseServiceResult : 返回支付方申请退款后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("refund")
	public EcBaseServiceResult refund(EcWxRefundOrderDTO refundOrderDTO) {
		return ecWxPayLogic.refund(refundOrderDTO);
	}

	/**
	 * 
	 * <p>
	 * 查询退款结果
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecOrderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     ecOrderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param ecOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回支付方查询退款后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("queryRefundResult")
	public EcBaseServiceResult queryRefundResult(EcOrderAbstractQuery ecOrderQuery) {
		return ecWxPayLogic.queryRefundResult(ecOrderQuery);
	}

	/**
	 * 
	 * <p>
	 * 下载对账单
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecOrderQuery.billDate : 2017-10-12 : 具体请查看对应支付平台 : 是
	 *     ecOrderQuery.billType : 1 : 账单类型、具体请查看对应支付平台: 是
	 * </pre>
	 *
	 * @param ecOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回支付方下载对账单的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("downLoadBill")
	public Object downLoadBill(EcOrderAbstractQuery ecOrderQuery) {
		return ecWxPayLogic.downLoadBill(ecOrderQuery);
	}

	/**
	 * 
	 * <p>
	 * 通用查询接口，根据 WxTransactionType 类型进行实现,此接口不包括退款
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecOrderQuery.transactionType : APP : 交易类型字符串 : 是
	 *     ecOrderQuery.tradeNoOrBillDate : 2017-10-12 : 支付平台订单号或者账单日期  : 是
	 *     ecOrderQuery.outTradeNoBillType : 1 : 商户单号或者 账单类型 : 是
	 * </pre>
	 *
	 * @param ecOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回支付方下载对账单的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("secondaryInterface")
	public EcBaseServiceResult secondaryInterface(EcOrderAbstractQuery ecOrderQuery) {
		return ecWxPayLogic.secondaryInterface(ecOrderQuery);
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
	 *     ecTransferOrderDTO.payeeAccount : 468555xx : enc_bank_no，收款方银行卡号 : 是
	 *     ecTransferOrderDTO.payeeName : 张三 : 收款方用户名  : 是
	 *     ecTransferOrderDTO.bankStr : ACBC : 收款方开户行枚举字符串 : 是
	 *     ecTransferOrderDTO.amount : 0.01 : 转账金额 : 是
	 *     ecTransferOrderDTO.outNo : WXTON2014578... : partner_trade_no,商户转账订单号 : 否
	 *     ecTransferOrderDTO.remark : 1 : 转账备注, 非必填 : 是
	 * </pre>
	 *
	 * @param ecOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回对应的转账结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("transfer")
	public EcBaseServiceResult transfer(EcTransferOrderDTO ecTransferOrderDTO) {
		return ecWxPayLogic.transfer(ecTransferOrderDTO);
	}

	/**
	 * 
	 * <p>
	 * 查询转账结果
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     ecOrderQuery.tradeNo : ert43543rete : 支付平台转账订单号 : 是
	 *     ecOrderQuery.outTradeNo : CNG20154987957 : 商户转账订单号: 是
	 * </pre>
	 *
	 * @param ecOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回对应的转账订单
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("queryTransferResult")
	public EcBaseServiceResult queryTransferResult(EcOrderAbstractQuery ecOrderQuery) {
		return ecWxPayLogic.queryTransferResult(ecOrderQuery);
	}
}
