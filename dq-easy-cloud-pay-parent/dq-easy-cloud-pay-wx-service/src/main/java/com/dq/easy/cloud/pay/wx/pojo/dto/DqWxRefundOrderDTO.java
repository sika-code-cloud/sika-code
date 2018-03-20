package com.dq.easy.cloud.pay.wx.pojo.dto;

import com.dq.easy.cloud.model.common.sign.utils.DqSignUtils;
import com.dq.easy.cloud.pay.model.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.payment.constant.DqWxPayConstant.DqWxPayKey;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderAbstractDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;

/**
 * 
 * <p>
 * 微信退款订单数据传输对象
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年3月16日 下午6:47:06
 */
public class DqWxRefundOrderDTO extends DqRefundOrderAbstractDTO {
	@Override
	public void buildSignParamters(DqPayConfigStorageInf dqPayConfigStorage, DqTransactionType dqTransactionType) {
		putSignatureData(DqWxPayKey.APPID_KEY, dqPayConfigStorage.getAppid());
		putSignatureData(DqWxPayKey.MCH__ID_KEY, dqPayConfigStorage.getPid());
		putSignatureData(DqWxPayKey.SIGN__TYPE_KEY, dqPayConfigStorage.getSignType());
		putSignatureData(DqWxPayKey.NONCE__STR_KEY, DqSignUtils.randomStr());

		buildSign(dqPayConfigStorage);
	}

	@Override
	protected void putRefundNoSignData() {
		putSignatureData(DqWxPayKey.OUT__REFUND__NO_KEY, this.getTradeNo());
	}

	@Override
	protected void putTradeNoSignData() {
		putSignatureData(DqWxPayKey.TRANSACTION__ID_KEY, this.getTradeNo());
	}

	@Override
	protected void putOutTradeNoSignData() {
		putSignatureData(DqWxPayKey.OUT__TRADE__NO_KEY, this.getOutTradeNo());
	}

	@Override
	protected void putRefundAmountSignData() {
		putSignatureData(DqWxPayKey.OUT__TRADE__NO_KEY, this.getRefundAmountOfCent());
	}

	@Override
	protected void putTotalAmountSignData() {
		putSignatureData(DqWxPayKey.TOTAL__FEE_KEY, this.getTradeNo());
	}

	@Override
	public void putSignSignData() {
		putSignatureData(DqWxPayKey.SIGN_KEY, super.getSign());
	}

	@Override
	protected void buildSign(DqPayConfigStorageInf dqPayConfigStorage) {
		
	}

}
