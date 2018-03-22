package com.dq.easy.cloud.pay.model.transaction.pojo.bo;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessExceptionEnum;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayErrorCodeEnum;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;

/**
 * 
 * <p>
 * 转账订单业务逻辑对象
 * </p>
 *
 * <pre>
 *  说明：所有渠道转账业务逻辑对象的父类
 *  约定：渠道转账业务逻辑对象必须继承此类
 *  命名规范：如DqWxTransferOrderBO
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月26日 下午8:06:32
 */
public abstract class DqTransferOrderBO {
	protected DqTransferOrderDTO dqTransferOrderDTO;

	public DqTransferOrderBO() {

	}

	public DqTransferOrderBO(DqTransferOrderDTO dqTransferOrderDTO) {
		this.dqTransferOrderDTO = dqTransferOrderDTO;
	}

	public DqTransferOrderBO initDqTransferOrderDTO() {
		initOutNoData();
		return this;
	}

	public abstract DqTransferOrderBO initBank();
	
	protected abstract DqTransferOrderBO initOutNoData();

	public abstract DqTransferOrderBO verifyTransferData();

	/** 校验收款开户行 */
	protected DqTransferOrderBO verifyBank() {
		if (DqBaseUtils.isNull(this.dqTransferOrderDTO.getBank())) {
			throw DqBaseBusinessExceptionEnum.newInstance(DqPayErrorCodeEnum.BANK_CANT_NULL);
		}
		return this;
	}

	/** 校验转账订单号 */
	protected DqTransferOrderBO verifyOutNo() {
		if (DqBaseUtils.isNull(this.dqTransferOrderDTO.getOutNo())) {
			throw DqBaseBusinessExceptionEnum.newInstance(DqPayErrorCodeEnum.OUT_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验收款方账户 */
	protected DqTransferOrderBO verifyPayeeAccount() {
		if (DqBaseUtils.isNull(this.dqTransferOrderDTO.getPayeeAccount())) {
			throw DqBaseBusinessExceptionEnum.newInstance(DqPayErrorCodeEnum.PAYEE_ACCOUNT_CANT_EMPTY);
		}
		return this;
	}

	/** 校验收款方账户 */
	protected DqTransferOrderBO verifyPayeeName() {
		if (DqBaseUtils.isNull(this.dqTransferOrderDTO.getPayeeName())) {
			throw DqBaseBusinessExceptionEnum.newInstance(DqPayErrorCodeEnum.PAYEE_NAME_CANT_EMPTY);
		}
		return this;
	}

	/** 校验转账金额 */
	protected DqTransferOrderBO verifyAmount() {
		if (DqBaseUtils.isNull(this.dqTransferOrderDTO.getAmount())) {
			throw DqBaseBusinessExceptionEnum.newInstance(DqPayErrorCodeEnum.AMOUNT_CANT_NULL);
		}
		return this;
	}

	public DqTransferOrderDTO getDqTransferOrderDTO() {
		return dqTransferOrderDTO;
	}

	public void setDqTransferOrderDTO(DqTransferOrderDTO dqTransferOrderDTO) {
		this.dqTransferOrderDTO = dqTransferOrderDTO;
	}

}
