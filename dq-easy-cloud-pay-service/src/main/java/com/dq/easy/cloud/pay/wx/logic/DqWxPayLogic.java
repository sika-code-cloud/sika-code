package com.dq.easy.cloud.pay.wx.logic;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.logic.DqBaseLogic;
import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.pay.model.base.constant.DqPayErrorCode;
import com.dq.easy.cloud.pay.model.base.pojo.dto.DqPayException;
import com.dq.easy.cloud.pay.model.payment.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.model.payment.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxTransactionType;
import com.dq.easy.cloud.pay.wx.pojo.dto.DqWxPayResultDTO;
import com.dq.easy.cloud.pay.wx.service.DqWxPayService;

/**
 * 
 * <p>
 * 业务逻辑处理层
 * </p>
 *
 * <pre>
 *  说明：若服务业务逻辑比较复杂则加上该层来组合各个service层之间的逻辑
 *  约定：介于controller层和service层之间
 *  命名规范：以logic结尾
 *  使用示例：
 * </pre>
 *
 * @author daiqi
 * 创建时间    2018年2月24日 下午2:07:49
 */
@Component
public class DqWxPayLogic extends DqBaseLogic{
	/** 统一日志处理会获取到该属性进行日志打印 */
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DqWxPayService dqWxPayService;
	/**
	 * 
	 * <p>
	 * 微信公众号支付
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.openid : eraweea343 : 用户openid : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return DqBaseServiceResult
	 * @author daiqi
	 * 创建时间    2018年2月24日 下午2:19:55
	 */
	public DqBaseServiceResult wxJsapiPay(DqPayOrderDTO dqPayOrderDTO){
		DqPayOrderBO dqPayOrderBO = DqPayOrderBO.newInstance(dqPayOrderDTO, DqWxTransactionType.JSAPI).initDqPayOrderDTO();
//		创建支付订单校验
		dqPayOrderBO.verifyCreatePayOrder();
		DqWxPayResultDTO dqWxPayResultDTO = new DqWxPayResultDTO(dqWxPayService.orderInfo(dqPayOrderBO.getDqPayOrderDTO()));
		return DqBaseServiceResult.newInstanceOfSucResult(dqWxPayResultDTO);
	}
}
