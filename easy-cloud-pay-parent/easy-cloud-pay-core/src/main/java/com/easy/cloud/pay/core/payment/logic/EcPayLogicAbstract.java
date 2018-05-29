package com.easy.cloud.pay.core.payment.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easy.cloud.core.basic.constant.EcBaseConstant.EcImageFormat;
import com.easy.cloud.core.basic.logic.EcBaseLogic;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.http.constant.EcHttpConstant.EcMethodType;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.pay.core.payment.constant.EcPayErrorCodeEnum;
import com.easy.cloud.pay.core.payment.constant.EcPayConstant.EcPayKey;
import com.easy.cloud.pay.core.payment.constant.EcPayConstant.EcPayValue;
import com.easy.cloud.pay.core.payment.pojo.bo.EcPayOrderBO;
import com.easy.cloud.pay.core.payment.pojo.bo.EcRefundOrderBO;
import com.easy.cloud.pay.core.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.core.payment.pojo.dto.EcPayResultDTO;
import com.easy.cloud.pay.core.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.core.payment.service.EcPayServiceInf;
import com.easy.cloud.pay.core.refund.dto.EcRefundOrderAbstractDTO;
import com.easy.cloud.pay.core.transaction.inf.EcTransactionType;
import com.easy.cloud.pay.core.transaction.pojo.bo.EcTransferOrderBO;
import com.easy.cloud.pay.core.transaction.pojo.dto.EcTransferOrderDTO;

/**
 * 
 * <p>
 * 支付逻辑 抽象类
 * </p>
 *
 * <pre>
 *  说明：
 *  约定：
 *  命名规范：
 *  使用示例：
 * </pre>
 *
 * @author daiqi 创建时间 2018年2月27日 上午10:19:45
 */
public abstract class EcPayLogicAbstract extends EcBaseLogic implements EcPayLogicInf {
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Override
	public String mWebPay(EcPayOrderDTO ecPayOrderDTO, HttpServletRequest request, EcTransactionType transactionType) {
		// 1、创建支付订单业务逻辑对象
		EcPayOrderBO ecPayOrderBO = getEcPayOrderBO(ecPayOrderDTO, transactionType);
		// 2、支付订单数据传输对象数据初始化
		ecPayOrderBO.initEcPayOrderDTO().initMWebPayData(request);
		// 3、支付订单数据传输对象数据校验
		ecPayOrderBO.verifyMWebPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = getEcPayService().orderInfo(ecPayOrderBO.getEcPayOrderDTO());
		// 5、返回结果
		return getEcPayService().buildRequest(orderInfo, EcMethodType.POST);
	}

	@Override
	public EcBaseServiceResult appPay(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) {
		// 1、创建支付订单业务逻辑对象
		EcPayOrderBO ecPayOrderBO = getEcPayOrderBO(ecPayOrderDTO, transactionType);
		// 2、支付订单数据传输对象数据初始化
		ecPayOrderBO.initEcPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		ecPayOrderBO.verifyAppPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = getEcPayService().orderInfo(ecPayOrderBO.getEcPayOrderDTO());
		// 5、返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(orderInfo));
	}

	@Override
	public EcBaseServiceResult microPay(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) throws IOException {
		// 1、创建支付订单业务逻辑对象
		EcPayOrderBO ecPayOrderBO = getEcPayOrderBO(ecPayOrderDTO, transactionType);
		// 2、支付订单数据传输对象数据初始化
		ecPayOrderBO.initEcPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		ecPayOrderBO.verifyMicroPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = getEcPayService().microPay(ecPayOrderBO.getEcPayOrderDTO());
		// 5、签名校验
		if (getEcPayService().verify(orderInfo)) {
//					支付校验通过后的处理
			return microPayLogic(orderInfo);
		}
		// 6、返回结果
		return EcBaseServiceResult.newInstanceOfError(EcPayErrorCodeEnum.PAY_FAILURE).buildResult(orderInfo);
	}

	@Override
	public byte[] generatePayQrCode(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType) throws IOException {
		// 1、创建支付订单业务逻辑对象
		EcPayOrderBO ecPayOrderBO = getEcPayOrderBO(ecPayOrderDTO, transactionType);
		// 2、支付订单数据传输对象数据初始化
		ecPayOrderBO.initEcPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		ecPayOrderBO.verifyGeneratePayQrCodeData();
		// 4、获取支付二维码
		BufferedImage bufferedImage = getEcPayService().generatePayQrCode(ecPayOrderDTO);
		// 5、将bufferedImage写进ByteArrayOutputStream对象中
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, EcImageFormat.JPEG, baos);
		// 6、返回结果
		return baos.toByteArray();
	}

	@Override
	public String payCallBack(HttpServletRequest request) throws IOException {
		// 获取支付方返回的对应参数
		Map<String, Object> payCallBackParams = getEcPayService().getParameterToMap(request.getParameterMap(),
				request.getInputStream());
		EcLogUtils.info("支付回调", payCallBackParams, getLogger());
		if (EcMapUtils.isEmpty(payCallBackParams)) {
			return getEcPayService().getPayOutMessage(EcPayValue.FAIL_CODE, EcPayValue.FAIL_DESC).toMessage();
		}
		// 参数校验
		if (getEcPayService().verify(payCallBackParams)) {
			
			return payCallBackLogic(payCallBackParams);
		}
		return getEcPayService().getPayOutMessage(EcPayValue.FAIL_CODE, EcPayValue.FAIL_DESC).toMessage();
	}

	@Override
	public EcBaseServiceResult queryPayResult(EcOrderAbstractQuery EcOrderQuery) {
//		数据校验
		EcOrderQuery.verifyTradeNoAndOutTradeNo();
//		获取查询结果
		Map<String, Object> queryResult = getEcPayService().queryPayResult(EcOrderQuery.getTradeNo(), EcOrderQuery.getOutTradeNo());
		if (getEcPayService().signVerify(queryResult, queryResult.get(EcPayKey.SIGN_KEY).toString())) {
			EcLogUtils.info("签名校验通过洛", queryResult, LOG);
		}
//		返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(queryResult));
	}

	@Override
	public EcBaseServiceResult close(EcOrderAbstractQuery EcOrderQuery) {
//		数据校验
		EcOrderQuery.verifyTradeNoAndOutTradeNo();
//		获取结果
		Map<String, Object> closeResult = getEcPayService().close(EcOrderQuery.getTradeNo(), EcOrderQuery.getOutTradeNo());
//		返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(closeResult));
	}

	@Override
	public EcBaseServiceResult refund(EcRefundOrderAbstractDTO EcRefundOrderDTO) {
//		1、创建退款订单业务逻辑对象
		EcRefundOrderBO ecRefundOrderBO = getEcRefundOrderBO(EcRefundOrderDTO);
//		2、数据初始化
		ecRefundOrderBO.initEcRefundOrderDTO();
//		3、数据校验
		ecRefundOrderBO.verifyRefundData();
//		4、获取结果
		Map<String, Object> refundResult = getEcPayService().refund(EcRefundOrderDTO);
//		5、返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(refundResult));
	}

	@Override
	public EcBaseServiceResult queryRefundResult(EcOrderAbstractQuery EcOrderQuery) {
//		数据校验
		EcOrderQuery.verifyTradeNoAndOutTradeNo().verifyRefundTradeNo();
//		获取结果
		Map<String, Object> queryResult = getEcPayService().queryRefundResult(EcOrderQuery);
//		返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(queryResult));
	}

	@Override
	public Object downLoadBill(EcOrderAbstractQuery EcOrderQuery) {
//		数据校验
		EcOrderQuery.verifyBillDate().verifyBillType();
//		获取结果
		Map<String, Object> billResult = getEcPayService().downLoadBill(EcOrderQuery);
//		返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(billResult));
	}

	@Override
	public EcBaseServiceResult secondaryInterface(EcOrderAbstractQuery EcOrderQuery) {
//		数据校验
		EcOrderQuery.verifyOutTradeNoBillType().verifyTradeNoOrBillDate();
//		数据转换
		EcTransactionType ecTransactionType = getEcTransactionType(EcOrderQuery.getTransactionType());
//		获取结果
		Map<String, Object> queryResult = getEcPayService().secondaryInterface(EcOrderQuery.getTradeNoOrBillDate(), EcOrderQuery.getOutTradeNoBillType(), ecTransactionType);
//		返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(queryResult));
	}

	@Override
	public EcBaseServiceResult transfer(EcTransferOrderDTO ecTransferOrderDTO) {
//		获取转账业务逻辑对象
		EcTransferOrderBO ecTransferOrderBO = getEcTransferOrderBO(ecTransferOrderDTO);
//		转账数据初始化
		ecTransferOrderBO.initEcTransferOrderDTO().initBank();
//		转账数据校验
		ecTransferOrderBO.verifyTransferData();
//		执行转账
		Map<String, Object> transferResult = getEcPayService().transfer(ecTransferOrderBO.getEcTransferOrderDTO());
//		返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(transferResult));
	}

	@Override
	public EcBaseServiceResult queryTransferResult(EcOrderAbstractQuery EcOrderQuery) {
//		数据校验
		EcOrderQuery.verifyOutTradeNo().verifyTradeNo();
//		获取结果
		Map<String, Object> queryResult = getEcPayService().queryTransferResult(EcOrderQuery.getOutTradeNo(), EcOrderQuery.getTradeNo());
//		返回结果
		return EcBaseServiceResult.newInstanceOfSucResult(new EcPayResultDTO(queryResult));
	}
	/**
	 * 
	 * <p>
	 * pos主动扫码付款(条码付)业务逻辑处理
	 * </p>
	 *
	 * @param orderInfo
	 * @return
	 * @author daiqi 创建时间 2018年2月26日 下午6:46:52
	 */
	protected abstract EcBaseServiceResult microPayLogic(Map<String, Object> orderInfo);

	/**
	 * 
	 * <p>
	 * 支付回调业务逻辑处理
	 * </p>
	 *
	 * @param payCallBackParams
	 *            : Map<String, Object> : 封装的支付回调参数
	 * @return 处理结果
	 * @author daiqi 创建时间 2018年2月26日 下午6:44:48
	 */
	protected abstract String payCallBackLogic(Map<String, Object> payCallBackParams);
	/** 获取支付订单业务逻辑对象 */
	protected abstract EcPayOrderBO getEcPayOrderBO(EcPayOrderDTO ecPayOrderDTO, EcTransactionType transactionType);
	/** 获取支付订单业务逻辑对象 */
	protected abstract EcRefundOrderBO getEcRefundOrderBO(EcRefundOrderAbstractDTO EcRefundOrderDTO);
	/** 获取交易订单业务逻辑对象 */
	protected abstract EcTransferOrderBO getEcTransferOrderBO(EcTransferOrderDTO ecTransferOrderDTO);
	/** 获取支付服务类 */
	protected abstract EcPayServiceInf getEcPayService();
	/** 获取日志记录对象 */
	protected abstract Logger getLogger();
	/** 获取EcTransactionType对象 */
	protected abstract EcTransactionType getEcTransactionType(String transactionTypeName);
}
