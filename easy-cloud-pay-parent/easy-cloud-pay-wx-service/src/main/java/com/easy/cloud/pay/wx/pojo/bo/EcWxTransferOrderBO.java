package com.easy.cloud.pay.wx.pojo.bo;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.core.transaction.pojo.bo.EcTransferOrderBO;
import com.easy.cloud.pay.core.transaction.pojo.dto.EcTransferOrderDTO;
import com.easy.cloud.pay.wx.common.utils.EcWxPayUtils.EcWxOrderNoGenerator;

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
public class EcWxTransferOrderBO extends EcTransferOrderBO{

	
	public EcWxTransferOrderBO() {
		super();
	}

	public EcWxTransferOrderBO(EcTransferOrderDTO ecTransferOrderDTO) {
		super(ecTransferOrderDTO);
	}

	
	@Override
	public EcTransferOrderBO initBank() {
		super.ecTransferOrderDTO.setBank(EcWxBank.valueOf(EcStringUtils.upperCase(ecTransferOrderDTO.getBankStr())));
		return this;
	}

	@Override
	protected EcTransferOrderBO initOutNoData() {
		if (EcStringUtils.isEmpty(super.ecTransferOrderDTO.getOutNo())) {
			super.ecTransferOrderDTO.setOutNo(EcWxOrderNoGenerator.generateWxOrderNO(EcWxTransactionType.BANK));
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
