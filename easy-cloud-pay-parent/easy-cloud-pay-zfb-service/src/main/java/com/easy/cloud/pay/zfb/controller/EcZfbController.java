package com.easy.cloud.pay.zfb.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.pay.common.payment.pojo.dto.EcPayOrderDTO;
import com.easy.cloud.pay.common.payment.pojo.query.EcOrderAbstractQuery;
import com.easy.cloud.pay.common.transaction.pojo.dto.EcTransferOrderDTO;
import com.easy.cloud.pay.zfb.logic.EcZfbPayLogic;
import com.easy.cloud.pay.zfb.pojo.bo.EcZfbTransactionType;
import com.easy.cloud.pay.zfb.pojo.dto.EcZfbRefundOrderDTO;
import com.easy.cloud.pay.zfb.pojo.query.EcZfbOrderQuery;

/**
 * 
 * <p>
 * 支付宝Controllerc层
 * </p>
 *
 * @author daiqi 创建时间 2018年2月27日 上午10:47:29
 */
@RestController
@RequestMapping("zfb")
public class EcZfbController extends EcBaseController {
	@Autowired
	private EcZfbPayLogic ecZfbPayLogic;

	/**
	 * 
	 * <p>
	 * 实时支付
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
	 * @param ecPayOrderDTO
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
    @RequestMapping(value = "directPay", produces = "text/html;charset=UTF-8")
    public String directPay(EcPayOrderDTO ecPayOrderDTO) {
        return ecZfbPayLogic.directPay(ecPayOrderDTO, null);
    }
	
    /**
	 * 
	 * <p>
	 * 移动h5网页支付
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
	 * @param ecPayOrderDTO
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
    @RequestMapping(value = "mWebPay", produces = "text/html;charset=UTF-8")
    public String mWebPay(EcPayOrderDTO ecPayOrderDTO) {
        return ecZfbPayLogic.mWebPay(ecPayOrderDTO, null, EcZfbTransactionType.WAP);
    }

    /**
	 * 
	 * <p>
	 * App支付
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
	 * @param ecPayOrderDTO
	 * @return String : 重定向的路径
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
    @RequestMapping("appPay")
    public EcBaseServiceResult appPay(EcPayOrderDTO ecPayOrderDTO) {
        return ecZfbPayLogic.appPay(ecPayOrderDTO, EcZfbTransactionType.APP);
    }

    /**
	 * 
	 * <p>
	 * 获取支付二维码
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
	 * @param ecPayOrderDTO
	 *            : EcPayOrderDTO : 支付订单数据传输对象
	 * @return 二维码图像
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 * @throws IOException
	 */
    @RequestMapping(value = "generatePayQrCode", produces = "image/jpeg;charset=UTF-8")
    public byte[] generatePayQrCode(EcPayOrderDTO ecPayOrderDTO) throws IOException {
        return ecZfbPayLogic.generatePayQrCode(ecPayOrderDTO, EcZfbTransactionType.SWEEPPAY);
    }

    /**
	 * 
	 * <p>
	 * 刷卡支付---pos主动扫码付款(条码付)
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqPayOrderDTO.subject : 支付洛 : 支付主题 : 是
	 *     dqPayOrderDTO.body : 摘要 : 支付主简述: 是
	 *     dqPayOrderDTO.price : 0.01 : 支付价格 : 是
	 *     dqPayOrderDTO.authCode : ewae : 授权码，条码等 : 是
	 *     dqPayOrderDTO.outTradeNo : PON2017152453125487 : 商户订单号 : 否，不传由支付系统自动创建
	 * </pre>
	 *
	 * @param ecPayOrderDTO
	 * @return EcBaseServiceResult : 处理结果
	 * @author daiqi 创建时间 2018年2月24日 下午2:19:55
	 */
    @RequestMapping(value = "microPay")
    public EcBaseServiceResult microPay(EcPayOrderDTO ecPayOrderDTO) throws IOException {
        return ecZfbPayLogic.microPay(ecPayOrderDTO, EcZfbTransactionType.BAR_CODE);
    }

    /**
	 * 
	 * <p>
	 * 支付回调
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param request
	 * @return 处理结果
	 * @throws IOException
	 * @author daiqi 创建时间 2018年2月26日 下午5:57:51
	 */
    @RequestMapping(value = "payCallBack")
    public String payCallBack(HttpServletRequest request) throws IOException {
    	return ecZfbPayLogic.payCallBack(request);
    }


    /**
	 * 
	 * <p>
	 * 查询订单信息
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqOrderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     dqOrderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 查询回来的结果集，支付方原值返回
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
    @RequestMapping("queryPayResult")
    public EcBaseServiceResult queryPayResult(EcOrderAbstractQuery dqOrderQuery) {
        return ecZfbPayLogic.queryPayResult(dqOrderQuery);
    }


    /**
	 * 
	 * <p>
	 * 交易关闭接口
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqOrderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     dqOrderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 支付方交易关闭后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
    @RequestMapping("close")
    public EcBaseServiceResult close(EcOrderAbstractQuery dqOrderQuery) {
        return ecZfbPayLogic.close(dqOrderQuery);
    }

    /**
	 * 
	 * <p>
	 * 申请退款接口
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 * </pre>
	 *
	 * @param EcZfbRefundOrderDTO : dqRefundOrderDTO : 支付宝退款数据传输对象
	 * @return EcBaseServiceResult : 返回支付方申请退款后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
    @RequestMapping("refund")
    public EcBaseServiceResult refund(EcZfbRefundOrderDTO dqRefundOrderDTO) {
        return ecZfbPayLogic.refund(dqRefundOrderDTO);
    }

    /**
	 * 
	 * <p>
	 * 查询退款结果
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqOrderQuery.tradeNo : ert43543rete : 支付平台订单号 : 是
	 *     dqOrderQuery.outTradeNo : CNG20154987957 : 商户订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : EcZfbOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回支付方查询退款后的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
    @RequestMapping("queryRefundResult")
    public EcBaseServiceResult queryRefundResult(EcZfbOrderQuery dqOrderQuery) {
        return ecZfbPayLogic.queryRefundResult(dqOrderQuery);
    }

    /**
	 * 
	 * <p>
	 * 下载对账单
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqOrderQuery.billDate : 2017-10-12 : 具体请查看对应支付平台 : 是
	 *     dqOrderQuery.billType : 1 : 账单类型、具体请查看对应支付平台: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回支付方下载对账单的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
    @RequestMapping("downLoadBill")
    public Object downLoadBill(EcZfbOrderQuery dqOrderQuery) {
        return ecZfbPayLogic.downLoadBill(dqOrderQuery);
    }


    /**
	 * 
	 * <p>
	 * 通用查询接口，根据 WxTransactionType 类型进行实现,此接口不包括退款
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqOrderQuery.transactionType : APP : 交易类型字符串 : 是
	 *     dqOrderQuery.tradeNoOrBillDate : 2017-10-12 : 支付平台订单号或者账单日期  : 是
	 *     dqOrderQuery.outTradeNoBillType : 1 : 商户单号或者 账单类型 : 是
	 * </pre>
	 *
	 * @param dqOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回支付方下载对账单的结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
    @RequestMapping("secondaryInterface")
    public EcBaseServiceResult secondaryInterface(EcOrderAbstractQuery dqOrderQuery) {
        return ecZfbPayLogic.secondaryInterface(dqOrderQuery);
    }

    /**
	 * 
	 * <p>
	 * 转账
	 * </p>
	 * <p>
	 *  采用标准RSA算法，公钥由侧提供,将公钥信息配置在EcPayConfigStorage#setKeyPublic(String)
	 * </p>
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqTransferOrderDTO.payeeAccount : 468555xx : enc_bank_no，收款方银行卡号 : 是
	 *     dqTransferOrderDTO.payeeName : 张三 : 收款方用户名  : 是
	 *     dqTransferOrderDTO.bankStr : ACBC : 收款方开户行枚举字符串 : 是
	 *     dqTransferOrderDTO.amount : 0.01 : 转账金额 : 是
	 *     dqTransferOrderDTO.outNo : WXTON2014578... : partner_trade_no,商户转账订单号 : 否
	 *     dqTransferOrderDTO.remark : 1 : 转账备注, 非必填 : 是
	 * </pre>
	 *
	 * @param dqOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回对应的转账结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
    @RequestMapping("transfer")
    public EcBaseServiceResult transfer(EcTransferOrderDTO ecTransferOrderDTO) {
        return ecZfbPayLogic.transfer(ecTransferOrderDTO);
    }

    /**
	 * 
	 * <p>
	 * 查询转账结果
	 * </p>
	 *
	 * <pre>
	 *     所需参数示例及其说明
	 *     参数名称 : 示例值 : 说明 : 是否必须
	 *     dqOrderQuery.tradeNo : ert43543rete : 支付平台转账订单号 : 是
	 *     dqOrderQuery.outTradeNo : CNG20154987957 : 商户转账订单号: 是
	 * </pre>
	 *
	 * @param dqOrderQuery : EcOrderQuery : 订单查询对象
	 * @return EcBaseServiceResult : 返回查询的转账结果
	 * @author daiqi
	 * 创建时间    2018年2月26日 下午7:04:13
	 */
    @RequestMapping("queryTransferResult")
    public EcBaseServiceResult queryTransferResult(EcOrderAbstractQuery dqOrderQuery) {
        return ecZfbPayLogic.queryTransferResult(dqOrderQuery);
    }
}
