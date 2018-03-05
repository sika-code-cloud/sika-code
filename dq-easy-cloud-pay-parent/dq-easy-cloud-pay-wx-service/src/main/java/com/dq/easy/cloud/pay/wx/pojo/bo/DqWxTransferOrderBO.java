package com.dq.easy.cloud.pay.wx.pojo.bo;

import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.transaction.pojo.bo.DqTransferOrderBO;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;
import com.dq.easy.cloud.pay.wx.common.utils.DqWxPayUtils.DqWxOrderNoGenerator;

/**
 * 
 * <p>
 * 微信转账业务逻辑对象
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月26日 下午8:06:12
 */
public class DqWxTransferOrderBO extends DqTransferOrderBO{

	
	public DqWxTransferOrderBO() {
		super();
	}

	public DqWxTransferOrderBO(DqTransferOrderDTO dqTransferOrderDTO) {
		super(dqTransferOrderDTO);
	}

	
	@Override
	public DqTransferOrderBO initBank() {
		super.dqTransferOrderDTO.setBank(DqWxBank.valueOf(DqStringUtils.upperCase(dqTransferOrderDTO.getBankStr())));
		return this;
	}

	@Override
	protected DqTransferOrderBO initOutNoData() {
		if (DqStringUtils.isEmpty(super.dqTransferOrderDTO.getOutNo())) {
			super.dqTransferOrderDTO.setOutNo(DqWxOrderNoGenerator.generateWxOrderNO(DqWxTransactionType.BANK));
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
