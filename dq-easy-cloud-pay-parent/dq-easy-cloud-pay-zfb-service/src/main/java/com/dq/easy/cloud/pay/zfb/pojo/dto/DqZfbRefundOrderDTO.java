package com.dq.easy.cloud.pay.zfb.pojo.dto;

import java.util.TimeZone;
import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.model.common.date.utils.DqDateUtils;
import com.dq.easy.cloud.pay.model.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.payment.constant.DqZfbPayConstant.DqZfbPayKey;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderAbstractDTO;

/**
 * 支付宝退款订单数据传输对象
 * @author daiqi
 * @date 2018年3月18日 上午1:56:44
 */
public class DqZfbRefundOrderDTO extends DqRefundOrderAbstractDTO{
	
	@Override
	public void putCommonSignData(DqPayConfigStorageInf dqPayConfigStorage) {
		putSignatureData(DqZfbPayKey.APP__ID_KEY, dqPayConfigStorage.getAppid());
		putSignatureData(DqZfbPayKey.CHARSET_KEY, dqPayConfigStorage.getInputCharset());
		putSignatureData(DqZfbPayKey.SIGN__TYPE_KEY, dqPayConfigStorage.getSignType());
		String timestampStr = DqDateFormatUtils.format(DqDateUtils.getCurrentDate(), DqDateFormatUtils.FORMAT_NORMAL, TimeZone.getTimeZone(DqDateFormatUtils.EAST_EIGHT_TIME_ZONE));
		putSignatureData(DqZfbPayKey.TIMESTAMP_KEY, timestampStr);
		putSignatureData(DqZfbPayKey.VERSION_KEY, "1.0");
		
		buildSign(dqPayConfigStorage);
	}

	@Override
	protected void putRefundNoSignData() {
		putSignatureData(DqZfbPayKey.OUT__REQUEST__NO_KEY, this.getRefundNo());
	}

	@Override
	protected void putTradeNoSignData() {
		putSignatureData(DqZfbPayKey.TRADE__NO_KEY, this.getTradeNo());
	}

	@Override
	protected void putOutTradeNoSignData() {
		putSignatureData(DqZfbPayKey.OUT__TRADE__NO_KEY, this.getOutTradeNo());
	}

	@Override
	protected void putRefundAmountSignData() {
		putSignatureData(DqZfbPayKey.REFUND__AMOUNT_KEY, this.getRefundAmountOfCent());
	}

	@Override
	protected void putTotalAmountSignData() {
		putSignatureData(DqZfbPayKey.TOTAL__AMOUNT_KEY, this.getTotalAmountOfCent());
	}
	
	@Override
	protected void putMethodSignData() {
		putSignatureData(DqZfbPayKey.METHOD_KEY, this.getMethod());		
	}

	@Override
	public void putSignSignData() {
		putSignatureData(DqZfbPayKey.SIGN_KEY, super.getSign());
	}

	@Override
	protected void putOrderDateSignData() {
		
	}

}
