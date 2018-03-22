package com.dq.easy.cloud.pay.model.payment.logic;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dq.easy.cloud.model.basic.constant.DqBaseConstant.DqImageFormat;
import com.dq.easy.cloud.model.basic.logic.DqBaseLogic;
import com.dq.easy.cloud.model.basic.pojo.dto.DqBaseServiceResult;
import com.dq.easy.cloud.model.common.http.constant.DqHttpConstant.DqMethodType;
import com.dq.easy.cloud.model.common.log.utils.DqLogUtils;
import com.dq.easy.cloud.model.common.map.utils.DqMapUtils;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayConstant.DqPayKey;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayConstant.DqPayValue;
import com.dq.easy.cloud.pay.model.payment.constant.DqPayErrorCodeEnum;
import com.dq.easy.cloud.pay.model.payment.pojo.bo.DqPayOrderBO;
import com.dq.easy.cloud.pay.model.payment.pojo.bo.DqRefundOrderBO;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayOrderDTO;
import com.dq.easy.cloud.pay.model.payment.pojo.dto.DqPayResultDTO;
import com.dq.easy.cloud.pay.model.payment.pojo.query.DqOrderAbstractQuery;
import com.dq.easy.cloud.pay.model.payment.service.DqPayServiceInf;
import com.dq.easy.cloud.pay.model.refund.dto.DqRefundOrderAbstractDTO;
import com.dq.easy.cloud.pay.model.transaction.inf.DqTransactionType;
import com.dq.easy.cloud.pay.model.transaction.pojo.bo.DqTransferOrderBO;
import com.dq.easy.cloud.pay.model.transaction.pojo.dto.DqTransferOrderDTO;

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
public abstract class DqPayLogicAbstract extends DqBaseLogic implements DqPayLogicInf {
	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Override
	public String mWebPay(DqPayOrderDTO dqPayOrderDTO, HttpServletRequest request, DqTransactionType transactionType) {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = getDqPayOrderBO(dqPayOrderDTO, transactionType);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO().initMWebPayData(request);
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyMWebPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = getDqPayService().orderInfo(dqPayOrderBO.getDqPayOrderDTO());
		// 5、返回结果
		return getDqPayService().buildRequest(orderInfo, DqMethodType.POST);
	}

	@Override
	public DqBaseServiceResult appPay(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = getDqPayOrderBO(dqPayOrderDTO, transactionType);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyAppPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = getDqPayService().orderInfo(dqPayOrderBO.getDqPayOrderDTO());
		// 5、返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(orderInfo));
	}

	@Override
	public DqBaseServiceResult microPay(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) throws IOException {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = getDqPayOrderBO(dqPayOrderDTO, transactionType);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyMicroPayData();
		// 4、获取订单信息
		Map<String, Object> orderInfo = getDqPayService().microPay(dqPayOrderBO.getDqPayOrderDTO());
		// 5、签名校验
		if (getDqPayService().verify(orderInfo)) {
//					支付校验通过后的处理
			return microPayLogic(orderInfo);
		}
		// 6、返回结果
		return DqBaseServiceResult.newInstanceOfError(DqPayErrorCodeEnum.PAY_FAILURE).buildResult(orderInfo);
	}

	@Override
	public byte[] generatePayQrCode(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType) throws IOException {
		// 1、创建支付订单业务逻辑对象
		DqPayOrderBO dqPayOrderBO = getDqPayOrderBO(dqPayOrderDTO, transactionType);
		// 2、支付订单数据传输对象数据初始化
		dqPayOrderBO.initDqPayOrderDTO();
		// 3、支付订单数据传输对象数据校验
		dqPayOrderBO.verifyGeneratePayQrCodeData();
		// 4、获取支付二维码
		BufferedImage bufferedImage = getDqPayService().generatePayQrCode(dqPayOrderDTO);
		// 5、将bufferedImage写进ByteArrayOutputStream对象中
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(bufferedImage, DqImageFormat.JPEG, baos);
		// 6、返回结果
		return baos.toByteArray();
	}

	@Override
	public String payCallBack(HttpServletRequest request) throws IOException {
		// 获取支付方返回的对应参数
		Map<String, Object> payCallBackParams = getDqPayService().getParameterToMap(request.getParameterMap(),
				request.getInputStream());
		DqLogUtils.info("支付回调", payCallBackParams, getLogger());
		if (DqMapUtils.isEmpty(payCallBackParams)) {
			return getDqPayService().getPayOutMessage(DqPayValue.FAIL_CODE, DqPayValue.FAIL_DESC).toMessage();
		}
		// 参数校验
		if (getDqPayService().verify(payCallBackParams)) {
			
			return payCallBackLogic(payCallBackParams);
		}
		return getDqPayService().getPayOutMessage(DqPayValue.FAIL_CODE, DqPayValue.FAIL_DESC).toMessage();
	}

	@Override
	public DqBaseServiceResult queryPayResult(DqOrderAbstractQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyTradeNoAndOutTradeNo();
//		获取查询结果
		Map<String, Object> queryResult = getDqPayService().queryPayResult(dqOrderQuery.getTradeNo(), dqOrderQuery.getOutTradeNo());
		if (getDqPayService().signVerify(queryResult, queryResult.get(DqPayKey.SIGN_KEY).toString())) {
			DqLogUtils.info("签名校验通过洛", queryResult, LOG);
		}
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(queryResult));
	}

	@Override
	public DqBaseServiceResult close(DqOrderAbstractQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyTradeNoAndOutTradeNo();
//		获取结果
		Map<String, Object> closeResult = getDqPayService().close(dqOrderQuery.getTradeNo(), dqOrderQuery.getOutTradeNo());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(closeResult));
	}

	@Override
	public DqBaseServiceResult refund(DqRefundOrderAbstractDTO dqRefundOrderDTO) {
//		1、创建退款订单业务逻辑对象
		DqRefundOrderBO dqRefundOrderBO = getDqRefundOrderBO(dqRefundOrderDTO);
//		2、数据初始化
		dqRefundOrderBO.initDqRefundOrderDTO();
//		3、数据校验
		dqRefundOrderBO.verifyRefundData();
//		4、获取结果
		Map<String, Object> refundResult = getDqPayService().refund(dqRefundOrderDTO);
//		5、返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(refundResult));
	}

	@Override
	public DqBaseServiceResult queryRefundResult(DqOrderAbstractQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyTradeNoAndOutTradeNo().verifyRefundTradeNo();
//		获取结果
		Map<String, Object> queryResult = getDqPayService().queryRefundResult(dqOrderQuery);
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(queryResult));
	}

	@Override
	public Object downLoadBill(DqOrderAbstractQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyBillDate().verifyBillType();
//		获取结果
		Map<String, Object> billResult = getDqPayService().downLoadBill(dqOrderQuery);
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(billResult));
	}

	@Override
	public DqBaseServiceResult secondaryInterface(DqOrderAbstractQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyOutTradeNoBillType().verifyTradeNoOrBillDate();
//		数据转换
		DqTransactionType dqTransactionType = getDqTransactionType(dqOrderQuery.getTransactionType());
//		获取结果
		Map<String, Object> queryResult = getDqPayService().secondaryInterface(dqOrderQuery.getTradeNoOrBillDate(), dqOrderQuery.getOutTradeNoBillType(), dqTransactionType);
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(queryResult));
	}

	@Override
	public DqBaseServiceResult transfer(DqTransferOrderDTO dqTransferOrderDTO) {
//		获取转账业务逻辑对象
		DqTransferOrderBO dqTransferOrderBO = getDqTransferOrderBO(dqTransferOrderDTO);
//		转账数据初始化
		dqTransferOrderBO.initDqTransferOrderDTO().initBank();
//		转账数据校验
		dqTransferOrderBO.verifyTransferData();
//		执行转账
		Map<String, Object> transferResult = getDqPayService().transfer(dqTransferOrderBO.getDqTransferOrderDTO());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(transferResult));
	}

	@Override
	public DqBaseServiceResult queryTransferResult(DqOrderAbstractQuery dqOrderQuery) {
//		数据校验
		dqOrderQuery.verifyOutTradeNo().verifyTradeNo();
//		获取结果
		Map<String, Object> queryResult = getDqPayService().queryTransferResult(dqOrderQuery.getOutTradeNo(), dqOrderQuery.getTradeNo());
//		返回结果
		return DqBaseServiceResult.newInstanceOfSucResult(new DqPayResultDTO(queryResult));
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
	protected abstract DqBaseServiceResult microPayLogic(Map<String, Object> orderInfo);

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
	protected abstract DqPayOrderBO getDqPayOrderBO(DqPayOrderDTO dqPayOrderDTO, DqTransactionType transactionType);
	/** 获取支付订单业务逻辑对象 */
	protected abstract DqRefundOrderBO getDqRefundOrderBO(DqRefundOrderAbstractDTO dqRefundOrderDTO);
	/** 获取交易订单业务逻辑对象 */
	protected abstract DqTransferOrderBO getDqTransferOrderBO(DqTransferOrderDTO dqTransferOrderDTO);
	/** 获取支付服务类 */
	protected abstract DqPayServiceInf getDqPayService();
	/** 获取日志记录对象 */
	protected abstract Logger getLogger();
	/** 获取DqTransactionType对象 */
	protected abstract DqTransactionType getDqTransactionType(String transactionTypeName);
}
