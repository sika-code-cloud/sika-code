package com.dq.easy.cloud.pay.wx.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dq.easy.cloud.model.basic.constant.DqBaseConstant.DqImageFormat;
import com.dq.easy.cloud.model.basic.logic.DqBaseLogic;
import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.common.http.constant.DqHttpConstant.MethodType;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.model.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.pay.model.payment.pojo.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.payment.pojo.query.DqOrderQuery;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.model.transaction.pojo.bo.DqTransferOrderBO;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;
import com.dq.easy.cloud.pay.wx.constant.DqWxPayConstant.DqWxPayValue;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxPayOrderBO;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxTransactionType;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxTransferOrderBO;
import com.dq.easy.cloud.pay.wx.pojo.dto.DqWxPayResultDTO;
import com.dq.easy.cloud.pay.wx.service.DqWxPayService;

/**
 * 
 * <p>
 * 业务逻辑处理层
 * </p>
 *
 * <pre>
 *  说明：若服务业务逻辑比较复杂则加上该层来组合各个service层之间的逻辑
 *  约定：介于controller层和service层之间
 *  命名规范：以logic结尾
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月24日 下午2:07:49
 */
@Component
public class DqWxPayLogic extends DqBaseLogic {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DqWxPayService dqWxPayService;

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
	public DqBaseServiceResult jsapiPay(DqPayOrderDTO dqPayOrderDTO) {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.JSAPI);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyPubPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = dqWxPayService.orderInfo(dqPayOrderBO.getDqPayOrderDTO());
		// 5、返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(orderInfo));
	}

	/**
	 * 
	 * <p>
	 * 微信网页支付
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
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	public String mWebPay(DqPayOrderDTO dqPayOrderDTO, HttpServletRequest request) {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.MWEB);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO().initMWebPayData(request);
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyMWebPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = dqWxPayService.orderInfo(dqPayOrderBO.getDqPayOrderDTO());
		// 5、返回结果
		return dqWxPayService.buildRequest(orderInfo, MethodType.POST);
	}

	/**
	 * 
	 * <p>
	 * 微信App支付
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
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	public DqBaseServiceResult appPay(DqPayOrderDTO dqPayOrderDTO) {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.APP);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyAppPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = dqWxPayService.orderInfo(dqPayOrderBO.getDqPayOrderDTO());
		// 5、返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(orderInfo));
	}

	/**
	 * 
	 * <p>
	 * 微信刷卡支付---pos主动扫码付款(条码付)
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
	public DqBaseServiceResult microPay(DqPayOrderDTO dqPayOrderDTO) throws IOException {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.MICROPAY);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyAppPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = dqWxPayService.microPay(dqPayOrderBO.getDqPayOrderDTO());
		// 5、签名校验
		if (dqWxPayService.verify(orderInfo)) {
//			支付校验通过后的处理
			return microPayLogic(orderInfo);
		}
		// 6、返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(orderInfo));
	}

	/**
	 * 
	 * <p>
	 * 获取微信支付二维码
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
	 *            : DqPayOrderDTO : 支付订单数据传输对象
	 * @return DqBaseServiceResult
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 * @throws IOException
	 */
	public byte[] generatePayQrCode(DqPayOrderDTO dqPayOrderDTO) throws IOException {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.NATIVE);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyGeneratePayQrCodeData();
		// 4、获取支付二维码
		BufferedImage bufferedImage = dqWxPayService.generatePayQrCode(dqPayOrderDTO);
		// 5、将bufferedImage写进ByteArrayOutputStream对象中
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, DqImageFormat.JPEG, baos);
		// 6、返回结果
		return baos.toByteArray();
	}

	/**
	 * 
	 * <p>
	 * 微信支付回调
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
	public String payCallBack(HttpServletRequest request) throws IOException {
		// 获取支付方返回的对应参数
		Map<String, Object> payCallBackParams = dqWxPayService.getParameterToMap(request.getParameterMap(),
				request.getInputStream());
		DqLogUtils.info("支付回调", payCallBackParams, LOG);
		if (DqMapUtils.isEmpty(payCallBackParams)) {
			return dqWxPayService.getPayOutMessage(DqWxPayValue.FAIL_CODE, DqWxPayValue.FAIL_DESC).toMessage();
		}
		// 参数校验
		if (dqWxPayService.verify(payCallBackParams)) {
			
			return payCallBackLogic(payCallBackParams);
		}
		return dqWxPayService.getPayOutMessage(DqWxPayValue.FAIL_CODE, DqWxPayValue.FAIL_DESC).toMessage();
	}
	
	/**
	 * 
	 * <p>
	 * 查询订单信息
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
	 * @return DqBaseServiceResult : 查询回来的结果集，支付方原值返回
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	public DqBaseServiceResult queryPayResult(DqOrderQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyTradeNo().verifyOutTradeNo();
//		获取查询结果
		Map<String, Object> queryResult = dqWxPayService.queryPayResult(dqOrderQuery.getTradeNo(), dqOrderQuery.getOutTradeNo());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(queryResult));
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
	 *     dqOrderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     dqOrderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : DqOrderQuery : 订单查询对象
	 * @return DqBaseServiceResult : 支付方交易关闭后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	public DqBaseServiceResult close(DqOrderQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyTradeNo().verifyOutTradeNo();
//		获取结果
		Map<String, Object> closeResult = dqWxPayService.close(dqOrderQuery.getTradeNo(), dqOrderQuery.getOutTradeNo());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(closeResult));
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
	public DqBaseServiceResult refund(DqRefundOrderDTO dqRefundOrderDTO) {
//		获取结果
		Map<String, Object> refundResult = dqWxPayService.refund(dqRefundOrderDTO);
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(refundResult));
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
	public DqBaseServiceResult queryRefundResult(DqOrderQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyTradeNo().verifyOutTradeNo();
//		获取结果
		Map<String, Object> queryResult = dqWxPayService.queryRefundResult(dqOrderQuery.getTradeNo(), dqOrderQuery.getOutTradeNo());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(queryResult));
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
//		数据校验
		dqOrderQuery.verifyBillDate().verifyBillType();
//		获取结果
		Map<String, Object> billResult = dqWxPayService.downLoadBill(dqOrderQuery.getBillDate(), dqOrderQuery.getBillType());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(billResult));
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
	public DqBaseServiceResult secondaryInterface(DqOrderQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyOutTradeNoBillType().verifyTradeNoOrBillDate();
//		数据转换
		DqTransactionType dqTransactionType = DqWxTransactionType.valueOf(dqOrderQuery.getTransactionType());
//		获取结果
		Map<String, Object> queryResult = dqWxPayService.secondaryInterface(dqOrderQuery.getTradeNoOrBillDate(), dqOrderQuery.getOutTradeNoBillType(), dqTransactionType);
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(queryResult));
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
	public DqBaseServiceResult transfer(DqTransferOrderDTO dqTransferOrderDTO) {
//		获取转账业务逻辑对象
		DqTransferOrderBO dqTransferOrderBO = new DqWxTransferOrderBO(dqTransferOrderDTO);
//		转账数据初始化
		dqTransferOrderBO.initDqTransferOrderDTO().initBank();
//		转账数据校验
		dqTransferOrderBO.verifyTransferData();
//		执行转账
		Map<String, Object> transferResult = dqWxPayService.transfer(dqTransferOrderBO.getDqTransferOrderDTO());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(transferResult));
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
	 * @return DqBaseServiceResult : 返回查询的转账结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
	public DqBaseServiceResult queryTransferResult(DqOrderQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyOutTradeNo().verifyTradeNo();
//		获取结果
		Map<String, Object> queryResult = dqWxPayService.queryTransferResult(dqOrderQuery.getOutTradeNo(), dqOrderQuery.getTradeNo());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(queryResult));
	}
	
	/**
	 * 
	 * <p>
	 * 微信刷卡支付---pos主动扫码付款(条码付)业务逻辑处理
	 * </p>
	 *
	 * @param orderInfo
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午6:46:52
	 */
	private DqBaseServiceResult microPayLogic(Map<String, Object> orderInfo){
//		TODO 处理pos机主动扫码付款业务逻辑
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(orderInfo));
	}
	/**
	 * 
	 * <p>
	 * 支付回调业务逻辑处理 
	 * </p>
	 *
	 * @param payCallBackParams : Map<String, Object> : 封装的支付回调参数
	 * @return 处理结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午6:44:48
	 */
	private String payCallBackLogic(Map<String, Object> payCallBackParams){
//		TODO 处理支付回调业务逻辑
		return dqWxPayService.getPayOutMessage(DqWxPayValue.SUCCESS_CODE, DqWxPayValue.SUCCESS_DESC).toMessage();
	}
}
