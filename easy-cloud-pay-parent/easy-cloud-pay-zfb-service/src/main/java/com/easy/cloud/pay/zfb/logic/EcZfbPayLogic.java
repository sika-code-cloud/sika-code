package com.easy.cloud.pay.zfb.logic;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.easy.cloud.pay.zfb.pojo.bo.EcZfbPayOrderBO;
import com.easy.cloud.pay.zfb.pojo.bo.EcZfbRefundOrderBO;
import com.easy.cloud.pay.zfb.pojo.bo.EcZfbTransactionType;
import com.easy.cloud.pay.zfb.pojo.bo.EcZfbTransferOrderBO;
import com.easy.cloud.pay.zfb.pojo.dto.EcZfbPayResultDTO;
import com.easy.cloud.pay.zfb.service.EcZfbPayService;

/**
 * 
 * <p>
 * 支付宝业务逻辑处理类
 * </p>
 *
 * @author daiqi 创建时间 2018年2月27日 上午10:37:41
 */
@Component
public class EcZfbPayLogic extends EcPayLogicAbstract {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());
	// 已经由EcZfbConfig注入到容器中
	@Autowired
	private EcZfbPayService ecZfbPayService;

	/**
	 * 
	 * <p>
	 * 直接支付实时支付
	 * </p>
	 *
	 * @param ecPayOrderDTO
	 * @param request
	 * @return
	 * @author daiqi 创建时间 2018年2月27日 下午4:43:54
	 */
	public String directPay(EcPayOrderDTO ecPayOrderDTO, HttpServletRequest request) {
		return mWebPay(ecPayOrderDTO, request, EcZfbTransactionType.DIRECT);
	}

	@Override
	public EcPayOrderBO getEcPayOrderBO(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) {
		return new EcZfbPayOrderBO(ecPayOrderDTO, transactionType);
	}

	@Override
	public EcPayServiceInf getEcPayService() {
		return this.ecZfbPayService;
	}

	@Override
	protected EcTransferOrderBO getEcTransferOrderBO(EcTransferOrderDTO ecTransferOrderDTO) {
		return new EcZfbTransferOrderBO(ecTransferOrderDTO);
	}

	@Override
	protected Logger getLogger() {
		return LOG;
	}

	@Override
	protected EcTransactionType getEcTransactionType(String transactionTypeName) {
		return EcZfbTransactionType.valueOf(transactionTypeName);
	}

	@Override
	protected EcBaseServiceResult microPayLogic(Map<String, Object> orderInfo) {
		// TODO 处理pos机主动扫码付款业务逻辑
		return EcBaseServiceResult.newInstanceOfSucResult(new EcZfbPayResultDTO(orderInfo));
	}

	@Override
	protected String payCallBackLogic(Map<String, Object> payCallBackParams) {
		// TODO 处理支付回调业务逻辑
		return ecZfbPayService.getPayOutMessage(EcWxPayValue.SUCCESS_CODE, EcWxPayValue.SUCCESS_DESC).toMessage();
	}

	@Override
	protected EcRefundOrderBO getEcRefundOrderBO(EcRefundOrderAbstractDTO refundOrderDTO) {
		return new EcZfbRefundOrderBO(refundOrderDTO);
	}
}
