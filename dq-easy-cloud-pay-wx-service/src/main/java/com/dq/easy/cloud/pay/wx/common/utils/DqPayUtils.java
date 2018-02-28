package com.dq.easy.cloud.pay.wx.common.utils;

import com.dq.easy.cloud.pay.model.payment.utils.DqPayUtils.DqOrderNoGenerator;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxTransactionType;

public class DqPayUtils {
	
	public static class DqPayOrderNoGenerator extends DqOrderNoGenerator{
		/**
		 * 
		 * <p>
		 * 	根据交易类型产生微信订单号
		 * </p>
		 *
		 * @param transactionType
		 * @return
		 * @author daiqi
		 * 创建时间    2018年2月27日 下午4:22:22
		 */
		public static String generateWxOrderNO (DqTransactionType transactionType) {
			String outTradeNo = null;
			if (DqWxTransactionType.isJSAPI(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateWxJsapiPayOrderNO();
			} else if (DqWxTransactionType.isAPP(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateWxJsapiPayOrderNO();
			} else if (DqWxTransactionType.isNATIVE(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateWxQrCodePayOrderNO();
			} else if (DqWxTransactionType.isMWEB(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateWxMWebPayOrderNO();
			} else if (DqWxTransactionType.isBANK(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateWxTransferOrderNO();
			}
			return outTradeNo;
		}
	}
}
