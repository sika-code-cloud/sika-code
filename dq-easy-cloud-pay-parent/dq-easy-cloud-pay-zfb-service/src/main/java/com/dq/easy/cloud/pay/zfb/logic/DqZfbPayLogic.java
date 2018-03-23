package com.dq.easy.cloud.pay.zfb.logic;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.module.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.pay.module.payment.constant.DqWxPayConstant.DqWxPayValue;
import com.dq.easy.cloud.pay.module.payment.logic.DqPayLogicAbstract;
import com.dq.easy.cloud.pay.module.payment.pojo.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.module.payment.pojo.bo.DqRefundOrderBO;
import com.dq.easy.cloud.pay.module.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.module.payment.service.DqPayServiceInf;
import com.dq.easy.cloud.pay.module.refund.dto.DqRefundOrderAbstractDTO;
import com.dq.easy.cloud.pay.module.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.module.transaction.pojo.bo.DqTransferOrderBO;
import com.dq.easy.cloud.pay.module.transaction.pojo.dto.DqTransferOrderDTO;
import com.dq.easy.cloud.pay.zfb.pojo.bo.DqZfbPayOrderBO;
import com.dq.easy.cloud.pay.zfb.pojo.bo.DqZfbRefundOrderBO;
import com.dq.easy.cloud.pay.zfb.pojo.bo.DqZfbTransactionType;
import com.dq.easy.cloud.pay.zfb.pojo.bo.DqZfbTransferOrderBO;
import com.dq.easy.cloud.pay.zfb.pojo.dto.DqZfbPayResultDTO;
import com.dq.easy.cloud.pay.zfb.service.DqZfbPayService;

/**
 * 
 * <p>
 * 支付宝业务逻辑处理类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月27日 上午10:37:41
 */
@Component
public class DqZfbPayLogic extends DqPayLogicAbstract {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	// 已经由DqZfbConfig注入到容器中
	@Autowired
	private DqZfbPayService dqZfbPayService;

	/**
	 * 
	 * <p>
	 * 直接支付实时支付
	 * </p>
	 *
	 * @param dqPayOrderDTO
	 * @param request
	 * @return
	 * @author daiqi 创建时间 2018年2月27日 下午4:43:54
	 */
	public String directPay(DqPayOrderDTO dqPayOrderDTO, HttpServletRequest request) {
		return mWebPay(dqPayOrderDTO, request, DqZfbTransactionType.DIRECT);
	}

	@Override
	public DqPayOrderBO getDqPayOrderBO(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		return new DqZfbPayOrderBO(dqPayOrderDTO, transactionType);
	}

	@Override
	public DqPayServiceInf getDqPayService() {
		return this.dqZfbPayService;
	}

	@Override
	protected DqTransferOrderBO getDqTransferOrderBO(DqTransferOrderDTO dqTransferOrderDTO) {
		return new DqZfbTransferOrderBO(dqTransferOrderDTO);
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	protected DqTransactionType getDqTransactionType(String transactionTypeName) {
		return DqZfbTransactionType.valueOf(transactionTypeName);
	}

	@Override
	protected DqBaseServiceResult microPayLogic(Map<String, Object> orderInfo) {
		// TODO 处理pos机主动扫码付款业务逻辑
		return DqBaseServiceResult.newInstanceOfSucResult(new DqZfbPayResultDTO(orderInfo));
	}

	@Override
	protected String payCallBackLogic(Map<String, Object> payCallBackParams) {
		// TODO 处理支付回调业务逻辑
		return dqZfbPayService.getPayOutMessage(DqWxPayValue.SUCCESS_CODE, DqWxPayValue.SUCCESS_DESC).toMessage();
	}

	@Override
	protected DqRefundOrderBO getDqRefundOrderBO(DqRefundOrderAbstractDTO dqRefundOrderDTO) {
		return new DqZfbRefundOrderBO(dqRefundOrderDTO);
	}
}
