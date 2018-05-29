package com.easy.cloud.pay.zfb.pojo.bo;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.core.transaction.pojo.bo.EcTransferOrderBO;
import com.easy.cloud.pay.core.transaction.pojo.dto.EcTransferOrderDTO;
import com.easy.cloud.pay.zfb.common.utils.EcZfbPayUtils.EcZfbOrderNoGenerator;

/**
 * 
 * <p>
 * 支付宝转账业务逻辑对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月26日 下午8:06:12
 */
public class EcZfbTransferOrderBO extends EcTransferOrderBO{

	
	public EcZfbTransferOrderBO() {
		super();
	}

	public EcZfbTransferOrderBO(EcTransferOrderDTO ecTransferOrderDTO) {
		super(ecTransferOrderDTO);
	}

	
	@Override
	public EcTransferOrderBO initBank() {
		super.ecTransferOrderDTO.setBank(EcZfbBank.valueOf(ecTransferOrderDTO.getBankStrUpper()));
		return this;
	}

	@Override
	protected EcTransferOrderBO initOutNoData() {
		if (EcStringUtils.isEmpty(super.ecTransferOrderDTO.getOutNo())) {
			super.ecTransferOrderDTO.setOutNo(EcZfbOrderNoGenerator.generateZfbOrderNO(EcZfbTransactionType.TRANS));
		}
		return this;
	}

	@Override
	public EcTransferOrderBO verifyTransferData() {
		super.verifyAmount();
		super.verifyOutNo();
		super.verifyPayeeAccount();
		super.verifyPayeeName();
		super.verifyBank();
		return this;
	}

}
