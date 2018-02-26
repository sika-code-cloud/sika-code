package com.dq.easy.cloud.pay.wx.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dq.easy.cloud.model.basic.constant.DqBaseConstant.DqImageFormat;
import com.dq.easy.cloud.model.basic.logic.DqBaseLogic;
import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.basic.utils.DqBaseUtils;
import com.dq.easy.cloud.model.common.http.constant.DqHttpConstant.MethodType;
import com.dq.easy.cloud.model.common.http.constant.DqHttpConstant.RequestHeaderKey;
import com.dq.easy.cloud.pay.model.base.constant.DqPayErrorCode;
import com.dq.easy.cloud.pay.model.base.pojo.dto.DqPayException;
import com.dq.easy.cloud.pay.model.payment.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.model.payment.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.wx.pojo.bo.DqWxPayOrderBO;
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
 * @author daiqi 创建时间 2018年2月24日 下午2:07:49
 */
@Component
public class DqWxPayLogic extends DqBaseLogic {
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
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	public DqBaseServiceResult wxJsapiPay(DqPayOrderDTO dqPayOrderDTO) {
//		1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.JSAPI);
//		2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
//		3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyPubPayData();
//		4、获取订单信息
		Map<String, Object> orderInfo = dqWxPayService.orderInfo(dqPayOrderBO.getDqPayOrderDTO());
//		5、返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(orderInfo));
	}
	
	/**
	 * 
	 * <p>
	 * 微信网页支付
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	public String wxMWebPay(DqPayOrderDTO dqPayOrderDTO, HttpServletRequest request) {
//		1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.MWEB);
//		2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO().initMWebData(request);
//		3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyMWebPayData();
//		4、获取订单信息
		Map<String, Object> orderInfo = dqWxPayService.orderInfo(dqPayOrderBO.getDqPayOrderDTO());
//		5、返回结果
		return dqWxPayService.buildRequest(orderInfo, MethodType.POST);
	}
	
	/**
	 * 
	 * <p>
	 * 微信App支付
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
	public DqBaseServiceResult wxAppPay(DqPayOrderDTO dqPayOrderDTO) {
//		1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.APP);
//		2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
//		3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyAppPayData();
//		4、获取订单信息
		Map<String, Object> orderInfo = dqWxPayService.orderInfo(dqPayOrderBO.getDqPayOrderDTO());
//		5、返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqWxPayResultDTO(orderInfo));
	}
	/**
	 * 
	 * <p>
	 * 获取微信支付二维码
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param dqPayOrderDTO
	 *            : DqPayOrderDTO : 支付订单数据传输对象
	 * @return DqBaseServiceResult
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 * @throws IOException 
	 */
	public byte[] generateWxPayQrCode(DqPayOrderDTO dqPayOrderDTO) throws IOException{
//		1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = new DqWxPayOrderBO(dqPayOrderDTO, DqWxTransactionType.NATIVE);
//		2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
//		3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyGeneratePayQrCodeData();
//		4、获取支付二维码
		BufferedImage bufferedImage = dqWxPayService.generatePayQrCode(dqPayOrderDTO);
//		5、将bufferedImage写进ByteArrayOutputStream对象中
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, DqImageFormat.JPEG, baos);
//		6、返回结果
		return baos.toByteArray();
	}
}
