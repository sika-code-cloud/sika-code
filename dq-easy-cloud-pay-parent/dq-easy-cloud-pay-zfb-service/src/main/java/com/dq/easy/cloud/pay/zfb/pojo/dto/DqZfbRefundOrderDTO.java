package com.dq.easy.cloud.pay.zfb.pojo.dto;

import java.util.Map;
import java.util.TimeZone;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.date.utils.DqDateFormatUtils;
import com.dq.easy.cloud.model.common.date.utils.DqDateUtils;
import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;
import com.dq.easy.cloud.model.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.model.common.number.bigdecimal.utils.DqBigDecimalUtils;
import com.dq.easy.cloud.model.common.sign.utils.DqSignUtils;
import com.dq.easy.cloud.model.common.string.constant.DqStringConstant.DqSymbol;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.payment.config.dto.DqPayConfigStorageInf;
import com.dq.easy.cloud.pay.model.payment.constant.DqZfbPayConstant.DqZfbPayKey;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderAbstractDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;

/**
 * 支付宝退款订单数据传输对象
 * @author daiqi
 * @date 2018年3月18日 上午1:56:44
 */
public class DqZfbRefundOrderDTO extends DqRefundOrderAbstractDTO{
	private Map<String, Object> contentSignatureMap;
	
	public DqZfbRefundOrderDTO() {
		contentSignatureMap = DqMapUtils.newHashMap();
	}

	@Override
	public void buildSignParamters(DqPayConfigStorageInf dqPayConfigStorage, DqTransactionType dqTransactionType) {
//		设置公共签名参数
		putSignatureData(DqZfbPayKey.APP__ID_KEY, dqPayConfigStorage.getAppid());
		putSignatureData(DqZfbPayKey.CHARSET_KEY, dqPayConfigStorage.getInputCharset());
		putSignatureData(DqZfbPayKey.SIGN__TYPE_KEY, dqPayConfigStorage.getSignType());
		String timestampStr = DqDateFormatUtils.format(DqDateUtils.getCurrentDate(), DqDateFormatUtils.FORMAT_NORMAL, TimeZone.getTimeZone(DqDateFormatUtils.EAST_EIGHT_TIME_ZONE));
		putSignatureData(DqZfbPayKey.TIMESTAMP_KEY, timestampStr);
		putSignatureData(DqZfbPayKey.VERSION_KEY, "1.0");
		putSignatureData(DqZfbPayKey.METHOD_KEY, dqTransactionType.getMethod());
//		设置content签名参数
		putSignatureData(DqZfbPayKey.BIZ__CONTENT_KEY, DqJSONUtils.parseObject(contentSignatureMap, String.class));
//		构建sign
		buildSign(dqPayConfigStorage);
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
	protected void buildSign(DqPayConfigStorageInf dqPayConfigStorage) {
		String needSignParamsStr = DqSignUtils.parameterText(getSignatureParameters(), DqSymbol.SINGLE_AND,
				DqZfbPayKey.SIGN_KEY);
		String sign = DqSignUtils.valueOf(dqPayConfigStorage.getSignType()).createSign(needSignParamsStr,
				dqPayConfigStorage.getKeyPrivate(), dqPayConfigStorage.getInputCharset());
		setSign(sign);
	}
	
	@Override
	public void putSignSignData() {
		putSignatureData(DqZfbPayKey.SIGN_KEY, super.getSign());
	}
	
	/**
	 * 
	 * <p>
	 * put支付宝内容签名数据
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param key
	 * @param value
	 * @author daiqi
	 * 创建时间    2018年3月20日 下午4:13:23
	 */
	private void putContentSignatureData(String key, Object value) {
		if (DqBaseUtils.isNull(value)) {
			return ;
		}
		if (value instanceof String && DqStringUtils.isEmpty((String)value)) {
			return ;
		}
		contentSignatureMap.put(key, value);
	}

	@Override
	public String toString() {
		return "DqZfbRefundOrderDTO [contentSignatureMap=" + contentSignatureMap + "]";
	}
	
}
