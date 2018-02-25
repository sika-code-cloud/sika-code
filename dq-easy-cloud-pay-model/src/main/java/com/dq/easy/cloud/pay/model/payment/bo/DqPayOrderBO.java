package com.dq.easy.cloud.pay.model.payment.bo;

import java.math.BigDecimal;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.number.bigdecimal.utils.DqBigDecimalUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.base.constant.DqPayErrorCode;
import com.dq.easy.cloud.pay.model.base.pojo.dto.DqPayException;
import com.dq.easy.cloud.pay.model.base.utils.DqPayUtils.DqOrderNoGenerator;
import com.dq.easy.cloud.pay.model.payment.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;

/**
 * 
 * <p>
 * 订单业务逻辑对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午3:34:30
 */
public class DqPayOrderBO {
	private DqPayOrderDTO dqPayOrderDTO;
	
	/**
	 * 
	 * <p>
	 * 初始化dqPayOrderDTO对象的数据
	 * </p>
	 * 
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午5:31:04
	 */
	public DqPayOrderBO initDqPayOrderDTO(){
		if (DqBaseUtils.isNull(this.dqPayOrderDTO)){
			throw DqPayException.newInstance(DqPayErrorCode.DQ_PAY_ORDER_DTO_CANT_NULL);
		}
		if (DqStringUtils.isEmpty(this.dqPayOrderDTO.getOutTradeNo())) {
			this.dqPayOrderDTO.setOutTradeNo(DqOrderNoGenerator.generatePayOrderNO());
		}
		return this;
	}
	/**
	 * 
	 * <p>
	 * 校验创建支付订单数据
	 * </p>
	 *
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午3:40:21
	 */
	public DqPayOrderBO verifyCreatePayOrder(){
//		校验链
		verifyDqPayOrderDTO().verifyBody().verifyOutTradeNo().verifyPrice()
		.verifyOpenid().verifySubject().verifyTransactionType();
		return this;
	}
	
	/** 只用作json序列化的时候请勿调用默认构造函数创建对象 */
	public DqPayOrderBO() {

	}

	private DqPayOrderBO(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		this.dqPayOrderDTO = dqPayOrderDTO;
		this.dqPayOrderDTO.buildTransactionType(transactionType);
	}
	
	public static DqPayOrderBO newInstance(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		return new DqPayOrderBO(dqPayOrderDTO, transactionType);
	}
	
	public DqPayOrderBO buildDqPayOrderDTO(DqPayOrderDTO dqPayOrderDTO){
		this.dqPayOrderDTO = dqPayOrderDTO;
		return this;
	}
	
	public DqPayOrderDTO getDqPayOrderDTO() {
		return dqPayOrderDTO;
	}

	public void setDqPayOrderDTO(DqPayOrderDTO dqPayOrderDTO) {
		this.dqPayOrderDTO = dqPayOrderDTO;
	}

	/** 校验dqPayOrderDTO对象 */
	private DqPayOrderBO verifyDqPayOrderDTO(){
		if (DqBaseUtils.isNull(this.dqPayOrderDTO)){
			throw DqPayException.newInstance(DqPayErrorCode.DQ_PAY_ORDER_DTO_CANT_NULL);
		}
		return this;
	}
	
	/** 校验openid对象 */
	private DqPayOrderBO verifyOpenid(){
		if (DqStringUtils.isEmpty(this.dqPayOrderDTO.getOpenid())){
			throw DqPayException.newInstance(DqPayErrorCode.OPENID_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验价格 */
	private DqPayOrderBO verifyPrice() {
		if (DqBaseUtils.isNull(dqPayOrderDTO.getPrice())){
			throw DqPayException.newInstance(DqPayErrorCode.PRICE_CANT_NULL);
		}
		if (DqBigDecimalUtils.isNotGreaterThan(dqPayOrderDTO.getPrice(), DqBigDecimalUtils.ZERO)){
			throw DqPayException.newInstance(DqPayErrorCode.PRICE_CANT_LESS_THAN_ZERO);
		}
		return this;
	}
	
	/** 校验subject */
	private DqPayOrderBO verifySubject(){
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getSubject())){
			throw DqPayException.newInstance(DqPayErrorCode.SUBJECT_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验body */
	private DqPayOrderBO verifyBody(){
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getBody())){
			throw DqPayException.newInstance(DqPayErrorCode.BODY_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验outTradeNo */
	private DqPayOrderBO verifyOutTradeNo(){
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getOutTradeNo())){
			throw DqPayException.newInstance(DqPayErrorCode.OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验TransactionType */
	private DqPayOrderBO verifyTransactionType(){
		if (DqBaseUtils.isNull(dqPayOrderDTO.getTransactionType())){
			throw DqPayException.newInstance(DqPayErrorCode.TRANSACTION_TYPE_CANT_NULL);
		}
		return this;
	}
}
