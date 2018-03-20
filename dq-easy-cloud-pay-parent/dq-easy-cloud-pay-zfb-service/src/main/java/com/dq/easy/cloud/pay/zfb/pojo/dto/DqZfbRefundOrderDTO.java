package com.dq.easy.cloud.pay.zfb.pojo.dto;

import com.dq.easy.cloud.model.common.number.bigdecimal.utils.DqBigDecimalUtils;
import com.dq.easy.cloud.pay.model.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.payment.constant.DqZfbPayConstant.DqZfbPayKey;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderAbstractDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.zfb.common.utils.DqZfbPayUtils.DqZfbSignUtils;

/**
 * 支付宝退款订单数据传输对象
 * @author daiqi
 * @date 2018年3月18日 上午1:56:44
 */
public class DqZfbRefundOrderDTO extends DqRefundOrderAbstractDTO{
	
	@Override
	public void buildSignatureParameters(DqPayConfigStorageInf dqPayConfigStorage, DqTransactionType dqTransactionType) {
		setSignatureParameters(DqZfbSignUtils.buildSignatureParameters(contentSignatureMap, dqPayConfigStorage, dqTransactionType));
	}
	
	@Override
	protected void putRefundNoSignData() {
		putContentSignatureData(DqZfbPayKey.OUT__REQUEST__NO_KEY, this.getRefundNo());
	}

	@Override
	protected void putTradeNoSignData() {
		putContentSignatureData(DqZfbPayKey.TRADE__NO_KEY, this.getTradeNo());
	}

	@Override
	protected void putOutTradeNoSignData() {
		putContentSignatureData(DqZfbPayKey.OUT__TRADE__NO_KEY, this.getOutTradeNo());
	}

	@Override
	protected void putRefundAmountSignData() {
		putContentSignatureData(DqZfbPayKey.REFUND__AMOUNT_KEY, DqBigDecimalUtils.formatTwoScale(this.getRefundAmount()));
	}

	@Override
	protected void putTotalAmountSignData() {
		putContentSignatureData(DqZfbPayKey.TOTAL__AMOUNT_KEY, DqBigDecimalUtils.formatTwoScale(this.getTotalAmount()));
	}
	
	@Override
	public String toString() {
		return "DqZfbRefundOrderDTO [contentSignatureMap=" + contentSignatureMap + "]";
	}
	
}
