package com.dq.easy.cloud.pay.zfb.pojo.bo;

import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.transaction.pojo.bo.DqTransferOrderBO;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;
import com.dq.easy.cloud.pay.zfb.common.utils.DqZfbPayUtils.DqZfbOrderNoGenerator;

/**
 * 
 * <p>
 * 支付宝转账业务逻辑对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月26日 下午8:06:12
 */
public class DqZfbTransferOrderBO extends DqTransferOrderBO{

	
	public DqZfbTransferOrderBO() {
		super();
	}

	public DqZfbTransferOrderBO(DqTransferOrderDTO dqTransferOrderDTO) {
		super(dqTransferOrderDTO);
	}

	
	@Override
	public DqTransferOrderBO initBank() {
		super.dqTransferOrderDTO.setBank(DqZfbBank.valueOf(dqTransferOrderDTO.getBankStrUpper()));
		return this;
	}

	@Override
	protected DqTransferOrderBO initOutNoData() {
		if (DqStringUtils.isEmpty(super.dqTransferOrderDTO.getOutNo())) {
			super.dqTransferOrderDTO.setOutNo(DqZfbOrderNoGenerator.generateZfbOrderNO(DqZfbTransactionType.TRANS));
		}
		return this;
	}

	@Override
	public DqTransferOrderBO verifyTransferData() {
		super.verifyAmount();
		super.verifyOutNo();
		super.verifyPayeeAccount();
		super.verifyPayeeName();
		super.verifyBank();
		return this;
	}

}
