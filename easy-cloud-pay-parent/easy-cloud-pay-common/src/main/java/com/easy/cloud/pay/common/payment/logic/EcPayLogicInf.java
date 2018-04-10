package com.easy.cloud.pay.common.payment.logic;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.pay.common.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.common.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.common.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.common.transaction.pojo.dto.EcTransferOrderDTO;

/**
 * 
 * <p>
 * 支付逻辑接口
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月27日 上午10:19:05
 */
interface EcPayLogicInf {
	/**
	 * 
	 * <p>
	 * 网页支付
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
	 * @param ecPayOrderDTO
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	String mWebPay(EcPayOrderDTO ecPayOrderDTO, HttpServletRequest request, EcTransactionType transactionType);

	/**
	 * 
	 * <p>
	 * App支付
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
	 * @param ecPayOrderDTO
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	EcBaseServiceResult appPay(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) ;

	/**
	 * 
	 * <p>
	 * 刷卡支付---pos主动扫码付款(条码付)
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
	 * @param ecPayOrderDTO
	 * @return DqBaseServiceResult : 处理结果
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	EcBaseServiceResult microPay(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) throws IOException ;

	/**
	 * 
	 * <p>
	 * 获取支付二维码
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
	 * @param ecPayOrderDTO
	 *            : DqPayOrderDTO : 支付订单数据传输对象
	 * @return 二维码图像
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 * @throws IOException
	 */
	byte[] generatePayQrCode(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) throws IOException ;

	/**
	 * 
	 * <p>
	 * 支付回调
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param request
	 * @return 处理结果
	 * @throws IOException
	 * @author daiqi 创建时间 2018年2月26日 下午5:57:51
	 */
	String payCallBack(HttpServletRequest request) throws IOException ;
	
	/**
	 * 
	 * <p>
	 * 查询订单信息
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     orderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     orderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param orderQuery : orderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 查询回来的结果集，支付方原值返回
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	EcBaseServiceResult queryPayResult(EcOrderAbstractQuery orderQuery) ;
	
	/**
	 * 
	 * <p>
	 * 交易关闭接口
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     orderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     orderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param orderQuery : orderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 支付方交易关闭后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	EcBaseServiceResult close(EcOrderAbstractQuery orderQuery) ;
	
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
	 * @param orderQuery : orderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回支付方申请退款后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	EcBaseServiceResult refund(EcRefundOrderAbstractDTO dqRefundOrderDTO) ;
	
	/**
	 * 
	 * <p>
	 * 查询退款结果
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     orderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     orderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param orderQuery : orderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回支付方查询退款后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	EcBaseServiceResult queryRefundResult(EcOrderAbstractQuery orderQuery) ;

	/**
	 * 
	 * <p>
	 * 下载对账单
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     orderQuery.billDate : 2017-10-12 : 具体请查看对应支付平台 : 是
	 *     orderQuery.billType : 1 : 账单类型、具体请查看对应支付平台: 是
	 * </pre>
	 *
	 * @param orderQuery
	 *            : orderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回支付方下载对账单的结果
	 * @author daiqi 创建时间 2018年2月26日 下午7:04:13
	 */
	Object downLoadBill(EcOrderAbstractQuery orderQuery) ;
	
	/**
	 * 
	 * <p>
	 * 通用查询接口，根据 WxTransactionType 类型进行实现,此接口不包括退款
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     orderQuery.transactionType : APP : 交易类型字符串 : 是
	 *     orderQuery.tradeNoOrBillDate : 2017-10-12 : 支付平台订单号或者账单日期  : 是
	 *     orderQuery.outTradeNoBillType : 1 : 商户单号或者 账单类型 : 是
	 * </pre>
	 *
	 * @param orderQuery : orderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回支付方下载对账单的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	EcBaseServiceResult secondaryInterface(EcOrderAbstractQuery orderQuery) ;
	
	/**
	 * 
	 * <p>
	 * 转账
	 * </p>
	 * <p>
	 *  采用标准RSA算法，公钥由侧提供,将公钥信息配置在DqPayConfigStorage#setKeyPublic(String)
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
	 * @param orderQuery : orderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回对应的转账结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	EcBaseServiceResult transfer(EcTransferOrderDTO ecTransferOrderDTO) ;
	
	/**
	 * 
	 * <p>
	 * 查询转账结果
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     orderQuery.tradeNo : ert43543rete : 支付平台转账订单号 : 是
	 *     orderQuery.outTradeNo : CNG20154987957 : 商户转账订单号: 是
	 * </pre>
	 *
	 * @param orderQuery : orderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 返回查询的转账结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	EcBaseServiceResult queryTransferResult(EcOrderAbstractQuery orderQuery) ;
	
}
