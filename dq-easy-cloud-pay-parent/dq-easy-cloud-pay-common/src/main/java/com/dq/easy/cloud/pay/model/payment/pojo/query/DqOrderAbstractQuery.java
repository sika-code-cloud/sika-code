package com.dq.easy.cloud.pay.model.payment.pojo.query;

import java.math.BigDecimal;
import java.util.Date;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.model.exception.bo.DqBaseBusinessException;
import com.dq.easy.cloud.pay.model.base.pojo.dto.DqBasePayDTO;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayErrorCode;

/**
 * 订单辅助接口
 * 
 * @author: egan
 * @email egzosn@gmail.com
 * @date 2017/3/12 14:50
 */
public abstract class DqOrderAbstractQuery extends DqBasePayDTO{

	private Integer payId;
	// 支付平台订单号
	private String tradeNo;
	// 商户单号
	private String outTradeNo;
	// 退款订单号	
	private String refundTradeNo;
	// 退款金额
	private BigDecimal refundAmount;
	// 总金额
	private BigDecimal totalAmount;
	// 账单时间：具体请查看对应支付平台
	private Date billDate;
	// 账单时间字符串：具体请查看对应支付平台
	private Long billDateTimestamp;
	// 账单类型：具体请查看对应支付平台
	private String billType;
	// 支付平台订单号或者账单日期
	private Object tradeNoOrBillDate;
	// 商户单号或者 账单类型
	private String outTradeNoBillType;
	// 交易类型
	private String transactionType;
	
	/**
	 * 
	 * <p>
	 * 校验tradeNo和outTradeNo
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * 创建时间    2018年3月2日 下午1:45:14
	 */
	public DqOrderAbstractQuery verifyTradeNoAndOutTradeNo() {
		if (DqStringUtils.isEmpty(this.getTradeNo()) && DqStringUtils.isEmpty(this.getOutTradeNo())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.TRADE_NO_AND_OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}
	/** 校验支付平台订单号 */
	public DqOrderAbstractQuery verifyTradeNo() {
		if (DqStringUtils.isEmpty(this.getTradeNo())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.TRADE_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验商户订单号 */
	public DqOrderAbstractQuery verifyOutTradeNo() {
		if (DqStringUtils.isEmpty(this.getOutTradeNo())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验账单时间 */
	public DqOrderAbstractQuery verifyBillDate() {
		if (DqBaseUtils.isNull(this.getBillDate())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.BILL_DATE_CANT_NULL);
		}
		return this;
	}

	/** 校验账单类型 */
	public DqOrderAbstractQuery verifyBillType() {
		if (DqStringUtils.isEmpty(this.getBillType())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.BILL_TYPE_CANT_NULL);
		}
		return this;
	}
	
	/** 校验账单类型 */
	public DqOrderAbstractQuery verifyTradeNoOrBillDate() {
		if (DqBaseUtils.isNull(this.getTradeNoOrBillDate())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.TRADE_NO_OR_BILL_DATE_CANT_NULL);
		}
		return this;
	}
	
	/** 校验账单类型 */
	public DqOrderAbstractQuery verifyOutTradeNoBillType() {
		if (DqStringUtils.isEmpty(this.getOutTradeNoBillType())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.OUT_TRADE_NO_BILL_TYPE_CANT_NULL);
		}
		return this;
	}
	/** 校验退款订单号 */
	public DqOrderAbstractQuery verifyRefundTradeNo() {
		if (DqStringUtils.isEmpty(this.getRefundTradeNo())) {
			throw DqBaseBusinessException.newInstance(DqPayErrorCode.REFUND_ORDER_NO_CANT_EMPTY);
		}
		return this;
	}

	protected abstract void putBillDateTimestampSignData();
	protected abstract void putPayIdSignData();
	protected abstract void putTradeNoSignData();
	protected abstract void putOutTradeNoSignData();
	protected abstract void putRefundAmountSignData();
	protected abstract void putTotalAmountSignData();
	protected abstract void putBillDateSignData();
	protected abstract void putBillTypeSignData();
	protected abstract void putTradeNoOrBillDateSignData();
	protected abstract void putOutTradeNoBillTypeSignData();
	protected abstract void putRefundTradeNoSignData();
	
	public Long getBillDateTimestamp() {
		return billDateTimestamp;
	}

	public void setBillDateTimestamp(Long billDateTimestamp) {
		this.billDateTimestamp = billDateTimestamp;
		putBillDateTimestampSignData();
	}

	public Integer getPayId() {
		return payId;
	}

	public void setPayId(Integer payId) {
		this.payId = payId;
		putPayIdSignData();
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
		putTradeNoSignData();
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
		putOutTradeNoSignData();
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
		putRefundAmountSignData();
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
		putTotalAmountSignData();
	}

	public Date getBillDate() {
		if (billDate == null && billDateTimestamp != null) {
			billDate = new Date(billDateTimestamp);
		}
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
		putBillDateSignData();
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
		putBillTypeSignData();
	}

	public Object getTradeNoOrBillDate() {
		return tradeNoOrBillDate;
	}

	public void setTradeNoOrBillDate(Object tradeNoOrBillDate) {
		this.tradeNoOrBillDate = tradeNoOrBillDate;
		putTradeNoOrBillDateSignData();
	}

	public String getOutTradeNoBillType() {
		return outTradeNoBillType;
	}

	public void setOutTradeNoBillType(String outTradeNoBillType) {
		this.outTradeNoBillType = outTradeNoBillType;
		putOutTradeNoBillTypeSignData();
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getRefundTradeNo() {
		return refundTradeNo;
	}

	public void setRefundTradeNo(String refundTradeNo) {
		this.refundTradeNo = refundTradeNo;
		putRefundTradeNoSignData();
	}

	@Override
	public String toString() {
		return "QueryOrder{" + "payId=" + payId + ", tradeNo='" + tradeNo + '\'' + ", outTradeNo='" + outTradeNo + '\''
				+ ", refundAmount=" + refundAmount + ", totalAmount=" + totalAmount + ", billDate=" + billDate
				+ ", billType='" + billType + '\'' + ", tradeNoOrBillDate=" + tradeNoOrBillDate
				+ ", outTradeNoBillType='" + outTradeNoBillType + '\'' + ", transactionType='" + transactionType + '\''
				+ '}';
	}
}
