package com.dq.easy.cloud.pay.common.payment.pojo.bo;

import com.dq.easy.cloud.module.basic.constant.error.DqBaseErrorCodeEnum;
import com.dq.easy.cloud.module.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.module.exception.bo.DqBaseBusinessException;
import com.dq.easy.cloud.pay.common.payment.constant.DqPayErrorCodeEnum;
import com.dq.easy.cloud.pay.common.payment.exception.DqPayException;
import com.dq.easy.cloud.pay.common.refund.dto.DqRefundOrderAbstractDTO;

/**
 * 
 * <p>
 * 退款订单逻辑对象
 * </p>
 *
 * <pre>
 *  说明：所有支付渠道退款订单逻辑对象的基础父类
 *  约定：必须继承此类
 *  命名规范：DqxxRefundOrderBO
 *  使用示例：DqZfbRefundOrderBO
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月1日 下午4:01:32
 */
public abstract class DqRefundOrderBO {
	/** 退款订单数据传输对象 */
	protected DqRefundOrderAbstractDTO dqRefundOrderDTO;
	
	
	public DqRefundOrderBO(DqRefundOrderAbstractDTO dqRefundOrderDTO) {
		super();
		this.dqRefundOrderDTO = dqRefundOrderDTO;
	}

	public DqRefundOrderBO initDqRefundOrderDTO() {
		if (DqBaseUtils.isNull(this.dqRefundOrderDTO)) {
			throw DqPayException.newInstance(DqBaseErrorCodeEnum.DTO_OBJ_CANT_NULL);
		}
		// 初始化订单信息
		initRefundNo();
		return this;
	}
	
	/** 校验商户支付订单号 */
	protected DqRefundOrderBO verifyOutTradeNoAdnTradeNo() {
		if (DqStringUtils.isEmpty(this.dqRefundOrderDTO.getOutTradeNo()) && DqStringUtils.isEmpty(this.dqRefundOrderDTO.getTradeNo())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCodeEnum.TRADE_NO_AND_OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验退款数据 */
	public abstract DqRefundOrderBO verifyRefundData();
	/** 初始化退款订单号 */
	protected abstract DqRefundOrderBO initRefundNo();
	
	protected DqRefundOrderBO verifyCommonData() {
		return verifyOutTradeNoAdnTradeNo().verifyRefundAmount().verifyRefundNo();
	}

	/** 校验总金额 */
	protected DqRefundOrderBO verifyTotalAmount() {
		if (DqBaseUtils.isNull(this.dqRefundOrderDTO.getTotalAmount())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCodeEnum.TOTAL_AMOUNT_CANT_NULL);
		}
		return this;
	}

	/** 校验退款订单号 */
	protected DqRefundOrderBO verifyRefundNo() {
		if (DqStringUtils.isEmpty(this.dqRefundOrderDTO.getRefundNo())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCodeEnum.REFUND_ORDER_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验商户支付订单号 */
	protected DqRefundOrderBO verifyOutTradeNo() {
		if (DqStringUtils.isEmpty(this.dqRefundOrderDTO.getOutTradeNo())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCodeEnum.OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验支付平台订单号 */
	protected DqRefundOrderBO verifyTradeNo() {
		if (DqStringUtils.isEmpty(this.dqRefundOrderDTO.getTradeNo())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCodeEnum.TRADE_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验退款金额 */
	protected DqRefundOrderBO verifyRefundAmount() {
		if (DqBaseUtils.isNull(this.dqRefundOrderDTO.getRefundAmount())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCodeEnum.REFUND_AMOUNT_CANT_NULL);
		}
		return this;
	}

}
