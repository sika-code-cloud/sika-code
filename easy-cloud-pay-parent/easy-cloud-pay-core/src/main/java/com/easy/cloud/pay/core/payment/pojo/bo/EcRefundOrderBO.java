package com.easy.cloud.pay.core.payment.pojo.bo;

import com.easy.cloud.core.basic.constant.error.EcBaseErrorCodeEnum;
import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.pay.core.payment.constant.EcPayErrorCodeEnum;
import com.easy.cloud.pay.core.payment.exception.EcPayException;
import com.easy.cloud.pay.core.refund.dto.EcRefundOrderAbstractDTO;

/**
 * 
 * <p>
 * 退款订单逻辑对象
 * </p>
 *
 * <pre>
 *  说明：所有支付渠道退款订单逻辑对象的基础父类
 *  约定：必须继承此类
 *  命名规范：EcxxRefundOrderBO
 *  使用示例：EcZfbRefundOrderBO
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月1日 下午4:01:32
 */
public abstract class EcRefundOrderBO {
	/** 退款订单数据传输对象 */
	protected EcRefundOrderAbstractDTO refundOrderDTO;
	
	
	public EcRefundOrderBO(EcRefundOrderAbstractDTO EcRefundOrderDTO) {
		super();
		this.refundOrderDTO = EcRefundOrderDTO;
	}

	public EcRefundOrderBO initEcRefundOrderDTO() {
		if (EcBaseUtils.isNull(this.refundOrderDTO)) {
			throw EcPayException.newInstance(EcBaseErrorCodeEnum.DTO_OBJ_CANT_NULL);
		}
		// 初始化订单信息
		initRefundNo();
		return this;
	}
	
	/** 校验商户支付订单号 */
	protected EcRefundOrderBO verifyOutTradeNoAdnTradeNo() {
		if (EcStringUtils.isEmpty(this.refundOrderDTO.getOutTradeNo()) && EcStringUtils.isEmpty(this.refundOrderDTO.getTradeNo())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.TRADE_NO_AND_OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验退款数据 */
	public abstract EcRefundOrderBO verifyRefundData();
	/** 初始化退款订单号 */
	protected abstract EcRefundOrderBO initRefundNo();
	
	protected EcRefundOrderBO verifyCommonData() {
		return verifyOutTradeNoAdnTradeNo().verifyRefundAmount().verifyRefundNo();
	}

	/** 校验总金额 */
	protected EcRefundOrderBO verifyTotalAmount() {
		if (EcBaseUtils.isNull(this.refundOrderDTO.getTotalAmount())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.TOTAL_AMOUNT_CANT_NULL);
		}
		return this;
	}

	/** 校验退款订单号 */
	protected EcRefundOrderBO verifyRefundNo() {
		if (EcStringUtils.isEmpty(this.refundOrderDTO.getRefundNo())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.REFUND_ORDER_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验商户支付订单号 */
	protected EcRefundOrderBO verifyOutTradeNo() {
		if (EcStringUtils.isEmpty(this.refundOrderDTO.getOutTradeNo())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验支付平台订单号 */
	protected EcRefundOrderBO verifyTradeNo() {
		if (EcStringUtils.isEmpty(this.refundOrderDTO.getTradeNo())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.TRADE_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验退款金额 */
	protected EcRefundOrderBO verifyRefundAmount() {
		if (EcBaseUtils.isNull(this.refundOrderDTO.getRefundAmount())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.REFUND_AMOUNT_CANT_NULL);
		}
		return this;
	}

}
