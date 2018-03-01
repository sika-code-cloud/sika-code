package com.dq.easy.cloud.pay.model.payment.pojo.bo;

import javax.servlet.http.HttpServletRequest;

import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.number.bigdecimal.utils.DqBigDecimalUtils;
import com.dq.easy.cloud.model.common.string.utils.DqStringUtils;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayErrorCode;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayConstant.DqPayValue;
import com.dq.easy.cloud.pay.model.payment.exception.DqPayException;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
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
	 * @author daiqi 创建时间 2018年2月24日 下午5:31:04
	 */
	public DqPayOrderBO initDqPayOrderDTO() {
		if (DqBaseUtils.isNull(this.dqPayOrderDTO)) {
			throw DqPayException.newInstance(DqPayErrorCode.DQ_PAY_ORDER_DTO_CANT_NULL);
		}
		// 初始化订单信息
		initOutTradeNo();
		return this;
	}
	/**
	 * 
	 * <p>
	 * 初始化transactionType类型
	 * </p>
	 *
	 * @param transactionType
	 * @return
	 * @author daiqi
	 * 创建时间    2018年2月27日 下午5:07:46
	 */
	public DqPayOrderBO initDqTransactionType(DqTransactionType transactionType) {
		this.dqPayOrderDTO.setTransactionType(transactionType);
		return this;
	}

	/** 初始化initWapName */
	public DqPayOrderBO initWapName() {
		if (DqStringUtils.isEmpty(this.dqPayOrderDTO.getWapName())) {
			this.dqPayOrderDTO.setWapName(DqPayValue.WAP_NAME_DEFAULT);
		}
		return this;
	}

	/** 初始化交易订单号 */
	protected abstract void initOutTradeNo();

	/** 初始化H5支付数据 */
	public abstract DqPayOrderBO initMWebPayData(HttpServletRequest request);

	/** 初始化刷卡支付数据 */
	public abstract DqPayOrderBO initMicroPayData(HttpServletRequest request);
	/**
	 * 
	 * <p>
	 * 校验微信公众号或者支付宝生活号支付数据
	 * </p>
	 *
	 * @return DqPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract DqPayOrderBO verifyPubPayData();

	/**
	 * 
	 * <p>
	 * 校验app支付数据
	 * </p>
	 *
	 * @return DqPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract DqPayOrderBO verifyAppPayData();
	
	/**
	 * 
	 * <p>
	 * 校验刷卡支付数据
	 * </p>
	 *
	 * @return DqPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract DqPayOrderBO verifyMicroPayData();

	/**
	 * 
	 * <p>
	 * 校验生成支付二维码数据
	 * </p>
	 *
	 * @return DqPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract DqPayOrderBO verifyGeneratePayQrCodeData();

	/**
	 * 
	 * <p>
	 * 校验web支付数据
	 * </p>
	 *
	 * @return DqPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract DqPayOrderBO verifyMWebPayData();

	/** 只用作json序列化的时候请勿调用默认构造函数创建对象 */
	public DqPayOrderBO() {

	}

	public DqPayOrderBO(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		this.dqPayOrderDTO = dqPayOrderDTO;
		this.dqPayOrderDTO.buildTransactionType(transactionType);
	}

	public DqPayOrderBO buildDqPayOrderDTO(DqPayOrderDTO dqPayOrderDTO) {
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
	protected DqPayOrderBO verifyDqPayOrderDTO() {
		if (DqBaseUtils.isNull(this.dqPayOrderDTO)) {
			throw DqPayException.newInstance(DqPayErrorCode.DQ_PAY_ORDER_DTO_CANT_NULL);
		}
		return this;
	}

	/** 校验openid对象 */
	protected DqPayOrderBO verifyOpenid() {
		if (DqStringUtils.isEmpty(this.dqPayOrderDTO.getOpenid())) {
			throw DqPayException.newInstance(DqPayErrorCode.OPENID_CANT_EMPTY);
		}
		return this;
	}

	/** 校验价格 */
	protected DqPayOrderBO verifyPrice() {
		if (DqBaseUtils.isNull(dqPayOrderDTO.getPrice())) {
			throw DqPayException.newInstance(DqPayErrorCode.PRICE_CANT_NULL);
		}
		if (DqBigDecimalUtils.isNotGreaterThan(dqPayOrderDTO.getPrice(), DqBigDecimalUtils.ZERO)) {
			throw DqPayException.newInstance(DqPayErrorCode.PRICE_CANT_LESS_THAN_ZERO);
		}
		return this;
	}

	/** 校验subject */
	protected DqPayOrderBO verifySubject() {
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getSubject())) {
			throw DqPayException.newInstance(DqPayErrorCode.SUBJECT_CANT_EMPTY);
		}
		return this;
	}

	/** 校验body */
	protected DqPayOrderBO verifyBody() {
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getBody())) {
			throw DqPayException.newInstance(DqPayErrorCode.BODY_CANT_EMPTY);
		}
		return this;
	}

	/** 校验outTradeNo */
	protected DqPayOrderBO verifyOutTradeNo() {
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getOutTradeNo())) {
			throw DqPayException.newInstance(DqPayErrorCode.OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验TransactionType */
	protected DqPayOrderBO verifyTransactionType() {
		if (DqBaseUtils.isNull(dqPayOrderDTO.getTransactionType())) {
			throw DqPayException.newInstance(DqPayErrorCode.TRANSACTION_TYPE_CANT_NULL);
		}
		return this;
	}

	protected DqPayOrderBO verifySpbillCreateIp() {
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getSpbillCreateIp())) {
			throw DqPayException.newInstance(DqPayErrorCode.SPBILL_CREATE_IP_CANT_EMPTY);
		}
		return this;
	}
	
	protected DqPayOrderBO verifyWapUrl() {
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getWapUrl())) {
			throw DqPayException.newInstance(DqPayErrorCode.WAP_URL_CANT_EMPTY);
		}
		return this;
	}
	
	/** 授权码校验 */
	protected DqPayOrderBO verifyAuthCode() {
		if (DqStringUtils.isEmpty(dqPayOrderDTO.getAuthCode())) {
			throw DqPayException.newInstance(DqPayErrorCode.AUTH_CODE_CANT_EMPTY);
		}
		return this;
	}
	/** 校验公共支付支付 */
	protected DqPayOrderBO verifyCommonData() {
		// 校验链
		this.verifyDqPayOrderDTO();
		this.verifyOutTradeNo();
		this.verifyPrice();
		this.verifyTransactionType();
		this.verifyBody();
		this.verifySubject();
		return this;
	}
}
