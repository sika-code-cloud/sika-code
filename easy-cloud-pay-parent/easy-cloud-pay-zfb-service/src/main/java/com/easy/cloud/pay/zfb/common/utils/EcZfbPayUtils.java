package com.easy.cloud.pay.zfb.common.utils;

import java.util.Map;
import java.util.TimeZone;

import com.easy.cloud.core.common.date.utils.EcDateFormatUtils;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.common.sign.utils.EcSignUtils;
import com.easy.cloud.core.common.string.constant.EcStringConstant.EcSymbol;
import com.easy.cloud.pay.core.payment.config.dto.EcPayConfigStorageInf;
import com.easy.cloud.pay.core.payment.constant.EcZfbPayConstant.EcZfbPayKey;
import com.easy.cloud.pay.core.payment.utils.EcPayUtils.EcOrderNoGenerator;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.zfb.pojo.bo.EcZfbTransactionType;

public class EcZfbPayUtils {
	
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
	public static class EcZfbSignUtils {
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
		 * @param ecTransactionType
		 * @return
		 * @author daiqi
		 * 创建时间    2018年3月20日 下午7:51:48
		 */
		public static Map<String, Object> buildSignatureParameters(Map<String, Object> contentSignatureMap, EcPayConfigStorageInf dqPayConfigStorage, EcTransactionType ecTransactionType) {
			Map<String, Object> initSignData = initCommonSignData(dqPayConfigStorage, ecTransactionType);
//			设置content签名参数
			initSignData.put(EcZfbPayKey.BIZ__CONTENT_KEY, EcJSONUtils.parseObject(contentSignatureMap, String.class));
//			构建sign
			String needSignParamsStr = EcSignUtils.parameterText(initSignData, EcSymbol.SINGLE_AND,
					EcZfbPayKey.SIGN_KEY);
			String sign = EcSignUtils.valueOf(dqPayConfigStorage.getSignType()).createSign(needSignParamsStr,
					dqPayConfigStorage.getKeyPrivate(), dqPayConfigStorage.getInputCharset());
			initSignData.put(EcZfbPayKey.SIGN_KEY, sign);
			return initSignData;
		}
		
		private static Map<String, Object> initCommonSignData(EcPayConfigStorageInf dqPayConfigStorage, EcTransactionType ecTransactionType) {
			Map<String, Object> signParams = EcMapUtils.newTreeMap();
//			设置公共签名参数
			signParams.put(EcZfbPayKey.APP__ID_KEY, dqPayConfigStorage.getAppid());
			signParams.put(EcZfbPayKey.CHARSET_KEY, dqPayConfigStorage.getInputCharset());
			signParams.put(EcZfbPayKey.SIGN__TYPE_KEY, dqPayConfigStorage.getSignType());
			String timestampStr = EcDateFormatUtils.format(EcDateUtils.getCurrentDate(), EcDateFormatUtils.FORMAT_NORMAL, TimeZone.getTimeZone(EcDateFormatUtils.EAST_EIGHT_TIME_ZONE));
			signParams.put(EcZfbPayKey.TIMESTAMP_KEY, timestampStr);
			signParams.put(EcZfbPayKey.VERSION_KEY, "1.0");
			signParams.put(EcZfbPayKey.METHOD_KEY, ecTransactionType.getMethod());
			return signParams;
		}
	}
	
	
	public static class EcZfbOrderNoGenerator extends EcOrderNoGenerator{
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
		public static String generateZfbOrderNO (EcTransactionType transactionType) {
			String outTradeNo = null;
			if (EcZfbTransactionType.isDIRECT(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateZfbDirectPayOrderNO();
			} else if (EcZfbTransactionType.isWAP(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateZfbWapPayOrderNO();
			} else if (EcZfbTransactionType.isAPP(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateZfbAppPayOrderNO();
			} else if (EcZfbTransactionType.isSWEEPPAY(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateZfbSweepPayOrderNO();
			} else if (EcZfbTransactionType.isBAR_CODE(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateZfbBarCodePayOrderNO();
			} else if (EcZfbTransactionType.isWAVE_CODE(transactionType)) {
				outTradeNo = EcOrderNoGenerator.generateZfbWaveCodePayOrderNO();
			} else if (EcZfbTransactionType.isTRANS(transactionType)){
				outTradeNo = EcOrderNoGenerator.generateZfbTransferOrderNO();
			} else if (EcZfbTransactionType.isREFUND(transactionType)){
				outTradeNo = EcOrderNoGenerator.generateZfbRefundOrderNO();
			}
			return outTradeNo;
		}
	}
}
