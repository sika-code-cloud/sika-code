package com.easy.cloud.pay.common.transaction.pojo.bo;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.exception.bo.EcBaseBusinessException;
import com.easy.cloud.pay.common.payment.constant.EcPayErrorCodeEnum;
import com.easy.cloud.pay.common.transaction.pojo.dto.EcTransferOrderDTO;

/**
 * 
 * <p>
 * 转账订单业务逻辑对象
 * </p>
 *
 * <pre>
 *  说明：所有渠道转账业务逻辑对象的父类
 *  约定：渠道转账业务逻辑对象必须继承此类
 *  命名规范：如EcWxTransferOrderBO
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月26日 下午8:06:32
 */
public abstract class EcTransferOrderBO {
	protected EcTransferOrderDTO ecTransferOrderDTO;

	public EcTransferOrderBO() {

	}

	public EcTransferOrderBO(EcTransferOrderDTO ecTransferOrderDTO) {
		this.ecTransferOrderDTO = ecTransferOrderDTO;
	}

	public EcTransferOrderBO initEcTransferOrderDTO() {
		initOutNoData();
		return this;
	}

	public abstract EcTransferOrderBO initBank();
	
	protected abstract EcTransferOrderBO initOutNoData();

	public abstract EcTransferOrderBO verifyTransferData();

	/** 校验收款开户行 */
	protected EcTransferOrderBO verifyBank() {
		if (EcBaseUtils.isNull(this.ecTransferOrderDTO.getBank())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.BANK_CANT_NULL);
		}
		return this;
	}

	/** 校验转账订单号 */
	protected EcTransferOrderBO verifyOutNo() {
		if (EcBaseUtils.isNull(this.ecTransferOrderDTO.getOutNo())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.OUT_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验收款方账户 */
	protected EcTransferOrderBO verifyPayeeAccount() {
		if (EcBaseUtils.isNull(this.ecTransferOrderDTO.getPayeeAccount())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.PAYEE_ACCOUNT_CANT_EMPTY);
		}
		return this;
	}

	/** 校验收款方账户 */
	protected EcTransferOrderBO verifyPayeeName() {
		if (EcBaseUtils.isNull(this.ecTransferOrderDTO.getPayeeName())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.PAYEE_NAME_CANT_EMPTY);
		}
		return this;
	}

	/** 校验转账金额 */
	protected EcTransferOrderBO verifyAmount() {
		if (EcBaseUtils.isNull(this.ecTransferOrderDTO.getAmount())) {
			throw EcBaseBusinessException.newInstance(EcPayErrorCodeEnum.AMOUNT_CANT_NULL);
		}
		return this;
	}

	public EcTransferOrderDTO getEcTransferOrderDTO() {
		return ecTransferOrderDTO;
	}

	public void setEcTransferOrderDTO(EcTransferOrderDTO ecTransferOrderDTO) {
		this.ecTransferOrderDTO = ecTransferOrderDTO;
	}

}
