package com.easy.cloud.pay.common.payment.pojo.bo;

import javax.servlet.http.HttpServletRequest;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.number.bigdecimal.utils.EcBigDecimalUtils;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.pay.common.payment.constant.EcPayConstant.EcPayValue;
import com.easy.cloud.pay.common.payment.constant.EcPayErrorCodeEnum;
import com.easy.cloud.pay.common.payment.exception.EcPayException;
import com.easy.cloud.pay.common.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.common.transaction.inf.EcTransactionType;

/**
 * 
 * <p>
 * 订单业务逻辑对象
 * </p>
 *
 * @author daiqi 创建时间 2018年2月24日 下午3:34:30
 */
public abstract class EcPayOrderBO {
	protected EcPayOrderDTO ecPayOrderDTO;

	/**
	 * 
	 * <p>
	 * 初始化EcPayOrderDTO对象的数据
	 * </p>
	 * 
	 * @return
	 * @author daiqi 创建时间 2018年2月24日 下午5:31:04
	 */
	public EcPayOrderBO initEcPayOrderDTO() {
		if (EcBaseUtils.isNull(this.ecPayOrderDTO)) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.PAY_ORDER_DTO_CANT_NULL);
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
	public EcPayOrderBO initEcTransactionType(EcTransactionType transactionType) {
		this.ecPayOrderDTO.setTransactionType(transactionType);
		return this;
	}

	/** 初始化initWapName */
	public EcPayOrderBO initWapName() {
		if (EcStringUtils.isEmpty(this.ecPayOrderDTO.getWapName())) {
			this.ecPayOrderDTO.setWapName(EcPayValue.WAP_NAME_DEFAULT);
		}
		return this;
	}

	/** 初始化交易订单号 */
	protected abstract void initOutTradeNo();

	/** 初始化H5支付数据 */
	public abstract EcPayOrderBO initMWebPayData(HttpServletRequest request);

	/** 初始化刷卡支付数据 */
	public abstract EcPayOrderBO initMicroPayData(HttpServletRequest request);
	/**
	 * 
	 * <p>
	 * 校验微信公众号或者支付宝生活号支付数据
	 * </p>
	 *
	 * @return EcPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract EcPayOrderBO verifyPubPayData();

	/**
	 * 
	 * <p>
	 * 校验app支付数据
	 * </p>
	 *
	 * @return EcPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract EcPayOrderBO verifyAppPayData();
	
	/**
	 * 
	 * <p>
	 * 校验刷卡支付数据
	 * </p>
	 *
	 * @return EcPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract EcPayOrderBO verifyMicroPayData();

	/**
	 * 
	 * <p>
	 * 校验生成支付二维码数据
	 * </p>
	 *
	 * @return EcPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract EcPayOrderBO verifyGeneratePayQrCodeData();

	/**
	 * 
	 * <p>
	 * 校验web支付数据
	 * </p>
	 *
	 * @return EcPayOrderBO
	 * @author daiqi 创建时间 2018年2月24日 下午3:40:21
	 */
	public abstract EcPayOrderBO verifyMWebPayData();

	/** 只用作json序列化的时候请勿调用默认构造函数创建对象 */
	public EcPayOrderBO() {

	}

	public EcPayOrderBO(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) {
		this.ecPayOrderDTO = ecPayOrderDTO;
		this.ecPayOrderDTO.buildTransactionType(transactionType);
	}

	public EcPayOrderBO buildEcPayOrderDTO(EcPayOrderDTO ecPayOrderDTO) {
		this.ecPayOrderDTO = ecPayOrderDTO;
		return this;
	}

	public EcPayOrderDTO getEcPayOrderDTO() {
		return ecPayOrderDTO;
	}

	public void setEcPayOrderDTO(EcPayOrderDTO ecPayOrderDTO) {
		this.ecPayOrderDTO = ecPayOrderDTO;
	}

	/** 校验EcPayOrderDTO对象 */
	protected EcPayOrderBO verifyEcPayOrderDTO() {
		if (EcBaseUtils.isNull(this.ecPayOrderDTO)) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.PAY_ORDER_DTO_CANT_NULL);
		}
		return this;
	}

	/** 校验openid对象 */
	protected EcPayOrderBO verifyOpenid() {
		if (EcStringUtils.isEmpty(this.ecPayOrderDTO.getOpenid())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.OPENID_CANT_EMPTY);
		}
		return this;
	}

	/** 校验价格 */
	protected EcPayOrderBO verifyPrice() {
		if (EcBaseUtils.isNull(ecPayOrderDTO.getPrice())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.PRICE_CANT_NULL);
		}
		if (EcBigDecimalUtils.isNotGreaterThan(ecPayOrderDTO.getPrice(), EcBigDecimalUtils.ZERO)) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.PRICE_CANT_LESS_THAN_ZERO);
		}
		return this;
	}

	/** 校验subject */
	protected EcPayOrderBO verifySubject() {
		if (EcStringUtils.isEmpty(ecPayOrderDTO.getSubject())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.SUBJECT_CANT_EMPTY);
		}
		return this;
	}

	/** 校验body */
	protected EcPayOrderBO verifyBody() {
		if (EcStringUtils.isEmpty(ecPayOrderDTO.getBody())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.BODY_CANT_EMPTY);
		}
		return this;
	}

	/** 校验outTradeNo */
	protected EcPayOrderBO verifyOutTradeNo() {
		if (EcStringUtils.isEmpty(ecPayOrderDTO.getOutTradeNo())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.OUT_TRADE_NO_CANT_EMPTY);
		}
		return this;
	}

	/** 校验TransactionType */
	protected EcPayOrderBO verifyTransactionType() {
		if (EcBaseUtils.isNull(ecPayOrderDTO.getTransactionType())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.TRANSACTION_TYPE_CANT_NULL);
		}
		return this;
	}

	protected EcPayOrderBO verifySpbillCreateIp() {
		if (EcStringUtils.isEmpty(ecPayOrderDTO.getSpbillCreateIp())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.SPBILL_CREATE_IP_CANT_EMPTY);
		}
		return this;
	}
	
	protected EcPayOrderBO verifyWapUrl() {
		if (EcStringUtils.isEmpty(ecPayOrderDTO.getWapUrl())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.WAP_URL_CANT_EMPTY);
		}
		return this;
	}
	
	/** 授权码校验 */
	protected EcPayOrderBO verifyAuthCode() {
		if (EcStringUtils.isEmpty(ecPayOrderDTO.getAuthCode())) {
			throw EcPayException.newInstance(EcPayErrorCodeEnum.AUTH_CODE_CANT_EMPTY);
		}
		return this;
	}
	/** 校验公共支付支付 */
	protected EcPayOrderBO verifyCommonData() {
		// 校验链
		this.verifyEcPayOrderDTO();
		this.verifyOutTradeNo();
		this.verifyPrice();
		this.verifyTransactionType();
		this.verifyBody();
		this.verifySubject();
		return this;
	}
}
