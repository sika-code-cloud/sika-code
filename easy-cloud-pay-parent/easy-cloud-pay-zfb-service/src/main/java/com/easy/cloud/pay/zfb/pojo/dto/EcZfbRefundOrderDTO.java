package com.easy.cloud.pay.zfb.pojo.dto;

import com.easy.cloud.core.common.number.bigdecimal.utils.EcBigDecimalUtils;
import com.easy.cloud.pay.common.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.common.payment.constant.EcZfbPayConstant.EcZfbPayKey;
import com.easy.cloud.pay.common.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.zfb.common.utils.EcZfbPayUtils.EcZfbSignUtils;

/**
 * 支付宝退款订单数据传输对象
 * @author daiqi
 * @date 2018年3月18日 上午1:56:44
 */
public class EcZfbRefundOrderDTO extends EcRefundOrderAbstractDTO{
	
	@Override
	public void buildSignatureParameters(EcPayConfigStorageInf dqPayConfigStorage, EcTransactionType ecTransactionType) {
		setSignatureParameters(EcZfbSignUtils.buildSignatureParameters(contentSignatureMap, dqPayConfigStorage, ecTransactionType));
	}
	
	@Override
	protected void putRefundNoSignData() {
		putContentSignatureData(EcZfbPayKey.OUT__REQUEST__NO_KEY, this.getRefundNo());
	}

	@Override
	protected void putTradeNoSignData() {
		putContentSignatureData(EcZfbPayKey.TRADE__NO_KEY, this.getTradeNo());
	}

	@Override
	protected void putOutTradeNoSignData() {
		putContentSignatureData(EcZfbPayKey.OUT__TRADE__NO_KEY, this.getOutTradeNo());
	}

	@Override
	protected void putRefundAmountSignData() {
		putContentSignatureData(EcZfbPayKey.REFUND__AMOUNT_KEY, EcBigDecimalUtils.formatTwoScale(this.getRefundAmount()));
	}

	@Override
	protected void putTotalAmountSignData() {
		putContentSignatureData(EcZfbPayKey.TOTAL__AMOUNT_KEY, EcBigDecimalUtils.formatTwoScale(this.getTotalAmount()));
	}
	
	@Override
	public String toString() {
		return "EcZfbRefundOrderDTO [contentSignatureMap=" + contentSignatureMap + "]";
	}
	
}
