package com.dq.easy.cloud.pay.wx.logic;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.pay.model.payment.constant.DqWxPayConstant.DqWxPayValue;
import com.dq.easy.cloud.pay.model.payment.logic.DqPayLogicAbstract;
import com.dq.easy.cloud.pay.model.payment.pojo.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.model.payment.pojo.bo.DqRefundOrderBO;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.payment.service.DqPayServiceInf;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.model.transaction.pojo.bo.DqTransferOrderBO;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxPayOrderBO;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxRefundOrderBO;
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
public class DqWxPayLogic extends DqPayLogicAbstract {
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
	public DqBaseServiceResult jsapiPay(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
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
	 * 微信刷卡支付---pos主动扫码付款(条码付)业务逻辑处理
	 * </p>
	 *
	 * @param orderInfo
	 * @return
	 * @author daiqi 创建时间 2018年2月26日 下午6:46:52
	 */
	protected DqBaseServiceResult microPayLogic(Map<String, Object> orderInfo) {
		// TODO 处理pos机主动扫码付款业务逻辑
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(orderInfo));
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
		return dqWxPayService.getPayOutMessage(DqWxPayValue.SUCCESS_CODE, DqWxPayValue.SUCCESS_DESC).toMessage();
	}

	@Override
	protected DqPayOrderBO getDqPayOrderBO(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		return new DqWxPayOrderBO(dqPayOrderDTO, transactionType);
	}

	@Override
	protected DqPayServiceInf getDqPayService() {
		return this.dqWxPayService;
	}

	@Override
	protected DqTransferOrderBO getDqTransferOrderBO(DqTransferOrderDTO dqTransferOrderDTO) {
		return new DqWxTransferOrderBO(dqTransferOrderDTO);
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	protected DqTransactionType getDqTransactionType(String transactionTypeName) {
		return DqWxTransactionType.valueOf(transactionTypeName);
	}

	@Override
	protected DqRefundOrderBO getDqRefundOrderBO(DqRefundOrderDTO dqRefundOrderDTO) {
		return new DqWxRefundOrderBO(dqRefundOrderDTO);
	}
}
