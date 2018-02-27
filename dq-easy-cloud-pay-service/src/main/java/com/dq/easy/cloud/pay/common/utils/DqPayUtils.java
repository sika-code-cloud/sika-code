package com.dq.easy.cloud.pay.common.utils;

import com.dq.easy.cloud.pay.model.payment.utils.DqPayUtils.DqOrderNoGenerator;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxTransactionType;
import com.dq.easy.cloud.pay.zfb.pojo.bo.DqZfbTransactionType;

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
		
		/**
		 * 
		 * <p>
		 * 	根据交易类型产生支付宝订单号
		 * </p>
		 *
		 * @param transactionType
		 * @return
		 * @author daiqi
		 * 创建时间    2018年2月27日 下午4:22:22
		 */
		public static String generateZfbOrderNO (DqTransactionType transactionType) {
			String outTradeNo = null;
			if (DqZfbTransactionType.isDIRECT(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateZfbDirectPayOrderNO();
			} else if (DqZfbTransactionType.isWAP(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateZfbWapPayOrderNO();
			} else if (DqZfbTransactionType.isAPP(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateZfbAppPayOrderNO();
			} else if (DqZfbTransactionType.isSWEEPPAY(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateZfbSweepPayOrderNO();
			} else if (DqZfbTransactionType.isBAR_CODE(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateZfbBarCodePayOrderNO();
			} else if (DqZfbTransactionType.isWAVE_CODE(transactionType)) {
				outTradeNo = DqOrderNoGenerator.generateZfbWaveCodePayOrderNO();
			} else if (DqZfbTransactionType.isTRANS(transactionType)){
				outTradeNo = DqOrderNoGenerator.generateZfbTransferOrderNO();
			}
			return outTradeNo;
		}
	}
}
