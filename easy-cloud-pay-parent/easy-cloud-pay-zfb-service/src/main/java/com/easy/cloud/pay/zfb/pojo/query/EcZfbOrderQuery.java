package com.easy.cloud.pay.zfb.pojo.query;

import java.util.TimeZone;

import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;
import com.easy.cloud.pay.core.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.core.payment.constant.EcZfbPayConstant.EcZfbPayKey;
import com.easy.cloud.pay.core.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.zfb.common.utils.EcZfbPayUtils.EcZfbSignUtils;

/**
 * 
 * <p>
 * 支付宝订单查询对象
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
 * 创建时间    2018年3月20日 下午8:10:31
 */
public class EcZfbOrderQuery extends EcOrderAbstractQuery{

	@Override
	protected void putBillDateTimestampSignData() {
		putBillDateSignData();
		
	}

	@Override
	protected void putPayIdSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putTradeNoSignData() {
		super.putContentSignatureData(EcZfbPayKey.TRADE__NO_KEY, getTradeNo());
	}

	@Override
	protected void putOutTradeNoSignData() {
		super.putContentSignatureData(EcZfbPayKey.OUT__TRADE__NO_KEY, getOutTradeNo());
	}

	@Override
	protected void putRefundAmountSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putTotalAmountSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putBillDateSignData() {
		String fomatDate = EcDateFormatUtils.format(getBillDate(), EcDateFormatUtils.FORMAT_NORMAL_DAY, TimeZone.getTimeZone(EcDateFormatUtils.EAST_EIGHT_TIME_ZONE));
		putContentSignatureData(EcZfbPayKey.BILL__DATE_KEY, fomatDate);
	}

	@Override
	protected void putBillTypeSignData() {
		putContentSignatureData(EcZfbPayKey.BILL__TYPE_KEY, getBillType());
	}

	@Override
	protected void putTradeNoOrBillDateSignData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void putOutTradeNoBillTypeSignData() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void putRefundTradeNoSignData() {
		super.putContentSignatureData(EcZfbPayKey.OUT__REQUEST__NO_KEY, getRefundTradeNo());
	}

	@Override
	public void buildSignatureParameters(EcPayConfigStorageInf dqPayConfigStorage,
			EcTransactionType ecTransactionType) {
		super.setSignatureParameters(EcZfbSignUtils.buildSignatureParameters(contentSignatureMap, dqPayConfigStorage, ecTransactionType));
	}
	
}
