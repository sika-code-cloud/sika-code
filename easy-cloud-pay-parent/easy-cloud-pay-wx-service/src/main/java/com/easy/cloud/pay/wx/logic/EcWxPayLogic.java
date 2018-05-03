package com.easy.cloud.pay.wx.logic;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.pay.core.payment.constant.EcWxPayConstant.EcWxPayValue;
import com.easy.cloud.pay.core.payment.logic.EcPayLogicAbstract;
import com.easy.cloud.pay.core.payment.pojo.bo.EcPayOrderBO;
import com.easy.cloud.pay.core.payment.pojo.bo.EcRefundOrderBO;
import com.easy.cloud.pay.core.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.core.payment.service.EcPayServiceInf;
import com.easy.cloud.pay.core.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.core.transaction.pojo.bo.EcTransferOrderBO;
import com.easy.cloud.pay.core.transaction.pojo.dto.EcTransferOrderDTO;
import com.easy.cloud.pay.wx.pojo.bo.EcWxPayOrderBO;
import com.easy.cloud.pay.wx.pojo.bo.EcWxRefundOrderBO;
import com.easy.cloud.pay.wx.pojo.bo.EcWxTransactionType;
import com.easy.cloud.pay.wx.pojo.bo.EcWxTransferOrderBO;
import com.easy.cloud.pay.wx.pojo.dto.EcWxPayResultDTO;
import com.easy.cloud.pay.wx.service.EcWxPayService;

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
public class EcWxPayLogic extends EcPayLogicAbstract {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EcWxPayService ecWxPayService;

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
	 * @param ecPayOrderDTO
	 * @return EcBaseServiceResult
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	public EcBaseServiceResult jsapiPay(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) {
		// 1、创建支付订单业务逻辑对象
		EcPayOrderBO ecPayOrderBO = new EcWxPayOrderBO(ecPayOrderDTO, EcWxTransactionType.JSAPI);
		// 2、支付订单数据传输对象数据初始化
		ecPayOrderBO.initEcPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		ecPayOrderBO.verifyPubPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = ecWxPayService.orderInfo(ecPayOrderBO.getEcPayOrderDTO());
		// 5、返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcWxPayResultDTO(orderInfo));
	}

	/**
	 * 
	 * <p>
	 * 微信刷卡支付---pos主动扫码付款(条码付)业务逻辑处理
	 * </p>
	 *
	 * @param orderInfo
	 * @return
	 * @author daiqi 创建时间 2018年2月26日 下午6:46:52
	 */
	protected EcBaseServiceResult microPayLogic(Map<String, Object> orderInfo) {
		// TODO 处理pos机主动扫码付款业务逻辑
		return EcBaseServiceResult.newInstanceOfSucResult(new EcWxPayResultDTO(orderInfo));
	}

	/**
	 * 
	 * <p>
	 * 支付回调业务逻辑处理
	 * </p>
	 *
	 * @param payCallBackParams
	 *            : Map<String, Object> : 封装的支付回调参数
	 * @return 处理结果
	 * @author daiqi 创建时间 2018年2月26日 下午6:44:48
	 */
	protected String payCallBackLogic(Map<String, Object> payCallBackParams) {
		// TODO 处理支付回调业务逻辑
		return ecWxPayService.getPayOutMessage(EcWxPayValue.SUCCESS_CODE, EcWxPayValue.SUCCESS_DESC).toMessage();
	}

	@Override
	protected EcPayOrderBO getEcPayOrderBO(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) {
		return new EcWxPayOrderBO(ecPayOrderDTO, transactionType);
	}

	@Override
	protected EcPayServiceInf getEcPayService() {
		return this.ecWxPayService;
	}

	@Override
	protected EcTransferOrderBO getEcTransferOrderBO(EcTransferOrderDTO ecTransferOrderDTO) {
		return new EcWxTransferOrderBO(ecTransferOrderDTO);
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	protected EcTransactionType getEcTransactionType(String transactionTypeName) {
		return EcWxTransactionType.valueOf(transactionTypeName);
	}

	@Override
	protected EcRefundOrderBO getEcRefundOrderBO(EcRefundOrderAbstractDTO dqRefundOrderDTO) {
		return new EcWxRefundOrderBO(dqRefundOrderDTO);
	}
}
