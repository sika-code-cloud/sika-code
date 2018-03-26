package com.dq.easy.cloud.pay.common.transaction.pojo.dto;

import java.math.BigDecimal;

import com.dq.easy.cloud.module.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.common.base.constant.DqCurType;
import com.dq.easy.cloud.pay.common.base.inf.DqBank;
import com.dq.easy.cloud.pay.common.payment.utils.DqPayUtils;

/**
 * 
 * <p>
 * 转账订单数据传输对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月23日 下午3:06:02
 */
public class DqTransferOrderDTO {

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
	private DqBank bank;
	
	/**
	 * 收款开户行枚举字符串
	 */
	private String bankStr;

	/**
	 * 币种
	 */
	private DqCurType curType;

	/** 获取amount以分为单位 */
	public int getAmountOfCent() {
		return DqPayUtils.yuanToCent(amount);
	}

	public String getBankStrUpper() {
		return DqStringUtils.upperCase(bankStr);
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

	public DqBank getBank() {
		return bank;
	}

	public void setBank(DqBank bank) {
		this.bank = bank;
	}

	public String getBankStr() {
		return bankStr;
	}

	public void setBankStr(String bankStr) {
		this.bankStr = bankStr;
	}

	public DqCurType getCurType() {
		return curType;
	}

	public void setCurType(DqCurType curType) {
		this.curType = curType;
	}

}
