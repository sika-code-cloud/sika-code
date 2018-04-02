package com.easy.cloud.pay.wx.common.utils;

import com.easy.cloud.pay.common.payment.utils.EcPayUtils.EcOrderNoGenerator;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.wx.pojo.bo.EcWxTransactionType;

public class EcWxPayUtils {
	
	public static class EcWxOrderNoGenerator extends EcOrderNoGenerator{
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
		public static String generateWxOrderNO (EcTransactionType transactionType) {
			String outTradeNo = null;
			if (EcWxTransactionType.isJSAPI(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateWxJsapiPayOrderNO();
			} else if (EcWxTransactionType.isAPP(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateWxJsapiPayOrderNO();
			} else if (EcWxTransactionType.isNATIVE(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateWxQrCodePayOrderNO();
			} else if (EcWxTransactionType.isMWEB(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateWxMWebPayOrderNO();
			} else if (EcWxTransactionType.isMICROPAY(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateWxMicroPayOrderNO();
			}else if (EcWxTransactionType.isBANK(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateWxTransferOrderNO();
			} else if (EcWxTransactionType.isREFUND(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateWxRefundOrderNO();
			}
			return outTradeNo;
		}
	}
}
