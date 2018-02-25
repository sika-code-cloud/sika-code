package com.dq.easy.cloud.pay.model.payment.bo;


import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.number.bigdecimal.utils.DqBigDecimalUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.base.constant.DqPayErrorCode;
import com.dq.easy.cloud.pay.model.base.pojo.dto.DqPayException;
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
public abstract class DqPayOrderBO {
	protected DqPayOrderDTO dqPayOrderDTO;
	
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
	public final DqPayOrderBO initDqPayOrderDTO(){
		if (DqBaseUtils.isNull(this.dqPayOrderDTO)){
			throw DqPayException.newInstance(DqPayErrorCode.DQ_PAY_ORDER_DTO_CANT_NULL);
		}
//		初始化订单信息
		initOutTradeNo();
		return this;
	}
//	初始化交易订单号
	protected abstract void initOutTradeNo();	
	/**
	 * 
	 * <p>
	 * 校验微信公众号或者支付宝生活号支付数据
	 * </p>
	 *
	 * @return DqPayOrderBO
	 * @author daiqi 
	 * 创建时间    2018年2月24日 下午3:40:21
	 */
	public abstract DqPayOrderBO verifyPubPayData();
	
	/**
	 * 
	 * <p>
	 * 校验app支付数据
	 * </p>
	 *
	 * @return DqPayOrderBO
	 * @author daiqi 
	 * 创建时间    2018年2月24日 下午3:40:21
	 */
	public abstract DqPayOrderBO verifyAppPayData();
	
	/**
	 * 
	 * <p>
	 * 校验生成支付二维码数据
	 * </p>
	 *
	 * @return DqPayOrderBO
	 * @author daiqi 
	 * 创建时间    2018年2月24日 下午3:40:21
	 */
	public abstract DqPayOrderBO verifyGeneratePayQrCodeData();
	
	/** 只用作json序列化的时候请勿调用默认构造函数创建对象 */
	public DqPayOrderBO() {

	}

	public DqPayOrderBO(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		this.dqPayOrderDTO = dqPayOrderDTO;
		this.dqPayOrderDTO.buildTransactionType(transactionType);
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
	protected DqPayOrderBO verifyDqPayOrderDTO(){
		if (DqBaseUtils.isNull(this.dqPayOrderDTO)){
			throw DqPayException.newInstance(DqPayErrorCode.DQ_PAY_ORDER_DTO_CANT_NULL);
		}
		return this;
	}
	
	/** 校验openid对象 */
	protected DqPayOrderBO verifyOpenid(){
		if (DqStringUtils.isEmpty(this.dqPayOrderDTO.getOpenid())){
			throw DqPayException.newInstance(DqPayErrorCode.OPENID_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验价格 */
	protected DqPayOrderBO verifyPrice() {
		if (DqBaseUtils.isNull(dqPayOrderDTO.getPrice())){
			throw DqPayException.newInstance(DqPayErrorCode.PRICE_CANT_NULL);
		}
		if (DqBigDecimalUtils.isNotGreaterThan(dqPayOrderDTO.getPrice(), DqBigDecimalUtils.ZERO)){
			throw DqPayException.newInstance(DqPayErrorCode.PRICE_CANT_LESS_THAN_ZERO);
		}
		return this;
	}
	
	/** 校验subject */
	protected DqPayOrderBO verifySubject(){
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getSubject())){
			throw DqPayException.newInstance(DqPayErrorCode.SUBJECT_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验body */
	protected DqPayOrderBO verifyBody(){
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getBody())){
			throw DqPayException.newInstance(DqPayErrorCode.BODY_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验outTradeNo */
	protected DqPayOrderBO verifyOutTradeNo(){
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getOutTradeNo())){
			throw DqPayException.newInstance(DqPayErrorCode.OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}
	
	/** 校验TransactionType */
	protected DqPayOrderBO verifyTransactionType(){
		if (DqBaseUtils.isNull(dqPayOrderDTO.getTransactionType())){
			throw DqPayException.newInstance(DqPayErrorCode.TRANSACTION_TYPE_CANT_NULL);
		}
		return this;
	}
}
