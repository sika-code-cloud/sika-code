package com.dq.easy.cloud.pay.zfb.common.utils;

import java.util.Map;
import java.util.TimeZone;

import com.dq.easy.cloud.module.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.module.common.date.utils.DqDateUtils;
import com.dq.easy.cloud.module.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.module.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.module.common.sign.utils.DqSignUtils;
import com.dq.easy.cloud.module.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.pay.module.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.module.payment.constant.DqZfbPayConstant.DqZfbPayKey;
import com.dq.easy.cloud.pay.module.payment.utils.DqPayUtils.DqOrderNoGenerator;
import com.dq.easy.cloud.pay.module.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.zfb.pojo.bo.DqZfbTransactionType;

public class DqZfbPayUtils {
	
	/**
	 * 
	 * <p>
	 * 支付宝签名工具类
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
	 * 创建时间    2018年3月20日 下午8:23:15
	 */
	public static class DqZfbSignUtils {
		/**
		 * 
		 * <p>
		 * 构建签名参数结果
		 * </p>
		 *
		 * <pre>
		 *     所需参数示例及其说明
		 *     参数名称 : 示例值 : 说明 : 是否必须
		 * </pre>
		 *
		 * @param contentSignatureMap
		 * @param dqPayConfigStorage
		 * @param dqTransactionType
		 * @return
		 * @author daiqi
		 * 创建时间    2018年3月20日 下午7:51:48
		 */
		public static Map<String, Object> buildSignatureParameters(Map<String, Object> contentSignatureMap, DqPayConfigStorageInf dqPayConfigStorage, DqTransactionType dqTransactionType) {
			Map<String, Object> initSignData = initCommonSignData(dqPayConfigStorage, dqTransactionType);
//			设置content签名参数
			initSignData.put(DqZfbPayKey.BIZ__CONTENT_KEY, DqJSONUtils.parseObject(contentSignatureMap, String.class));
//			构建sign
			String needSignParamsStr = DqSignUtils.parameterText(initSignData, DqSymbol.SINGLE_AND,
					DqZfbPayKey.SIGN_KEY);
			String sign = DqSignUtils.valueOf(dqPayConfigStorage.getSignType()).createSign(needSignParamsStr,
					dqPayConfigStorage.getKeyPrivate(), dqPayConfigStorage.getInputCharset());
			initSignData.put(DqZfbPayKey.SIGN_KEY, sign);
			return initSignData;
		}
		
		private static Map<String, Object> initCommonSignData(DqPayConfigStorageInf dqPayConfigStorage, DqTransactionType dqTransactionType) {
			Map<String, Object> signParams = DqMapUtils.newTreeMap();
//			设置公共签名参数
			signParams.put(DqZfbPayKey.APP__ID_KEY, dqPayConfigStorage.getAppid());
			signParams.put(DqZfbPayKey.CHARSET_KEY, dqPayConfigStorage.getInputCharset());
			signParams.put(DqZfbPayKey.SIGN__TYPE_KEY, dqPayConfigStorage.getSignType());
			String timestampStr = DqDateFormatUtils.format(DqDateUtils.getCurrentDate(), DqDateFormatUtils.FORMAT_NORMAL, TimeZone.getTimeZone(DqDateFormatUtils.EAST_EIGHT_TIME_ZONE));
			signParams.put(DqZfbPayKey.TIMESTAMP_KEY, timestampStr);
			signParams.put(DqZfbPayKey.VERSION_KEY, "1.0");
			signParams.put(DqZfbPayKey.METHOD_KEY, dqTransactionType.getMethod());
			return signParams;
		}
	}
	
	
	public static class DqZfbOrderNoGenerator extends DqOrderNoGenerator{
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
			} else if (DqZfbTransactionType.isREFUND(transactionType)){
				outTradeNo = DqOrderNoGenerator.generateZfbRefundOrderNO();
			}
			return outTradeNo;
		}
	}
}
