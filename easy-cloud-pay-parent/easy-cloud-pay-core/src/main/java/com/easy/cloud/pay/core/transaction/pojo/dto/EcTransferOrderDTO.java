package com.easy.cloud.pay.core.transaction.pojo.dto;

import java.math.BigDecimal;

import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.core.base.constant.EcCurType;
import com.easy.cloud.pay.core.base.inf.EcBank;
import com.easy.cloud.pay.core.payment.utils.EcPayUtils;

/**
 * 
 * <p>
 * 转账订单数据传输对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午3:06:02
 */
public class EcTransferOrderDTO {

	/**
	 * 转账订单单号
	 */
	private String outNo;

	/**
	 * 收款方账户
	 */
	private String payeeAccount;

	/**
	 * 转账金额
	 */
	private BigDecimal amount;

	/**
	 * 付款人名称
	 */
	private String payerName;

	/**
	 * 收款人名称
	 */
	private String payeeName;
	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 收款开户行
	 */
	private EcBank bank;
	
	/**
	 * 收款开户行枚举字符串
	 */
	private String bankStr;

	/**
	 * 币种
	 */
	private EcCurType curType;

	/** 获取amount以分为单位 */
	public int getAmountOfCent() {
		return EcPayUtils.yuanToCent(amount);
	}

	public String getBankStrUpper() {
		return EcStringUtils.upperCase(bankStr);
	}
	public String getOutNo() {
		return outNo;
	}

	public void setOutNo(String outNo) {
		this.outNo = outNo;
	}

	public String getPayeeAccount() {
		return payeeAccount;
	}

	public void setPayeeAccount(String payeeAccount) {
		this.payeeAccount = payeeAccount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public EcBank getBank() {
		return bank;
	}

	public void setBank(EcBank bank) {
		this.bank = bank;
	}

	public String getBankStr() {
		return bankStr;
	}

	public void setBankStr(String bankStr) {
		this.bankStr = bankStr;
	}

	public EcCurType getCurType() {
		return curType;
	}

	public void setCurType(EcCurType curType) {
		this.curType = curType;
	}

}
