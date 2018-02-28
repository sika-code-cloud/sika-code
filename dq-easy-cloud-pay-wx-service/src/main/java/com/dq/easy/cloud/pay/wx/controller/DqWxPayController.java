
package com.dq.easy.cloud.pay.wx.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dq.easy.cloud.model.basic.controller.DqBaseController;
import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.payment.pojo.query.DqOrderQuery;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;
import com.dq.easy.cloud.pay.wx.logic.DqWxPayLogic;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxTransactionType;

/**
 * 微信Controller
 */
@RestController
@RequestMapping("wx")
public class DqWxPayController extends DqBaseController {
	@Autowired
	private DqWxPayLogic dqWxPayLogic;

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
	 *     dqPayOrderDTO.openid : eraweea343 : 用户openid : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return DqBaseServiceResult
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping(value = "jsapiPay")
	public DqBaseServiceResult jsapiPay(DqPayOrderDTO dDqPayOrderDTO) {
		return dqWxPayLogic.jsapiPay(dDqPayOrderDTO, DqWxTransactionType.JSAPI);
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
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return DqBaseServiceResult
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping(value = "generatePayQrCode", produces = "image/jpeg;charset=UTF-8")
	public byte[] generatePayQrCode(DqPayOrderDTO dqPayOrderDTO) throws IOException {
		return dqWxPayLogic.generatePayQrCode(dqPayOrderDTO, DqWxTransactionType.NATIVE);
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
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return 跳到支付页面
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping(value = "mWebPay", produces = "text/html;charset=UTF-8")
	public String mWebPay(DqPayOrderDTO dqPayOrderDTO, HttpServletRequest request) {
		return dqWxPayLogic.mWebPay(dqPayOrderDTO, request, DqWxTransactionType.MWEB);
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
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return 支付预订单信息
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping("appPay")
	public DqBaseServiceResult appPay(DqPayOrderDTO dqPayOrderDTO) {
		return dqWxPayLogic.appPay(dqPayOrderDTO, DqWxTransactionType.APP);
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
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.authCode : ewae : 授权码，条码等 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return DqBaseServiceResult : 处理结果
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	@RequestMapping(value = "microPay")
	public DqBaseServiceResult microPay(DqPayOrderDTO dqPayOrderDTO) throws IOException {
		return dqWxPayLogic.microPay(dqPayOrderDTO, DqWxTransactionType.MICROPAY);
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
		return dqWxPayLogic.payCallBack(request);
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
	 *     dqPayOrderDTO.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     dqPayOrderDTO.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 查询回来的结果集，支付方原值返回
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("queryPayResult")
	public DqBaseServiceResult queryPayResult(DqOrderQuery dqOrderQuery) {
		return dqWxPayLogic.queryPayResult(dqOrderQuery);
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
	 *     dqPayOrderDTO.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     dqPayOrderDTO.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 支付方交易关闭后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("close")
	public DqBaseServiceResult close(DqOrderQuery dqOrderQuery) {
		return dqWxPayLogic.close(dqOrderQuery);
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
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回支付方申请退款后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("refund")
	public DqBaseServiceResult refund(DqRefundOrderDTO order) {
		return dqWxPayLogic.refund(order);
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
	 *     dqOrderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     dqOrderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回支付方查询退款后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("queryRefundResult")
	public DqBaseServiceResult queryRefundResult(DqOrderQuery dqOrderQuery) {
		return dqWxPayLogic.queryRefundResult(dqOrderQuery);
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
	 *     dqOrderQuery.billDate : 2017-10-12 : 具体请查看对应支付平台 : 是
	 *     dqOrderQuery.billType : 1 : 账单类型、具体请查看对应支付平台: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回支付方下载对账单的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("downLoadBill")
	public Object downLoadBill(DqOrderQuery dqOrderQuery) {
		return dqWxPayLogic.downLoadBill(dqOrderQuery);
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
	 *     dqOrderQuery.transactionType : APP : 交易类型字符串 : 是
	 *     dqOrderQuery.tradeNoOrBillDate : 2017-10-12 : 支付平台订单号或者账单日期  : 是
	 *     dqOrderQuery.outTradeNoBillType : 1 : 商户单号或者 账单类型 : 是
	 * </pre>
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回支付方下载对账单的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("secondaryInterface")
	public DqBaseServiceResult secondaryInterface(DqOrderQuery dqOrderQuery) {
		return dqWxPayLogic.secondaryInterface(dqOrderQuery);
	}

	/**
	 * 
	 * <p>
	 * 转账
	 * </p>
	 * <p>
	 *  采用标准RSA算法，公钥由微信侧提供,将公钥信息配置在DqPayConfigStorage#setKeyPublic(String)
	 * </p>
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqTransferOrderDTO.payeeAccount : 468555xx : enc_bank_no，收款方银行卡号 : 是
	 *     dqTransferOrderDTO.payeeName : 张三 : 收款方用户名  : 是
	 *     dqTransferOrderDTO.bankStr : ACBC : 收款方开户行枚举字符串 : 是
	 *     dqTransferOrderDTO.amount : 0.01 : 转账金额 : 是
	 *     dqTransferOrderDTO.outNo : WXTON2014578... : partner_trade_no,商户转账订单号 : 否
	 *     dqTransferOrderDTO.remark : 1 : 转账备注, 非必填 : 是
	 * </pre>
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回对应的转账结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("transfer")
	public DqBaseServiceResult transfer(DqTransferOrderDTO dqTransferOrderDTO) {
		return dqWxPayLogic.transfer(dqTransferOrderDTO);
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
	 *     dqOrderQuery.tradeNo : ert43543rete : 支付平台转账订单号 : 是
	 *     dqOrderQuery.outTradeNo : CNG20154987957 : 商户转账订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回对应的转账订单
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	@RequestMapping("queryTransferResult")
	public DqBaseServiceResult queryTransferResult(DqOrderQuery dqOrderQuery) {
		return dqWxPayLogic.queryTransferResult(dqOrderQuery);
	}
}
