package com.dq.easy.cloud.pay.model.refund.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.dq.easy.cloud.pay.model.base.utils.DqPayUtils;

/**
 * 
 * <p>
 * 退款订单信息数据传输对象
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午3:05:13
 */
public class DqRefundOrderDTO {
    /**
     * 退款单号，每次进行退款的单号，此处唯一
     */
    private String refundNo;
    /**
     * 支付平台订单号,交易号
     */
    private String tradeNo;
    /**
     * 商户单号
     */
    private String outTradeNo;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 交易日期
     */
    private Date orderDate;
    
    /** 以分为单位 */
    public int getTotalAmountOfCent(){
    	return DqPayUtils.yuanToCent(totalAmount);
    }

    /** 以分为单位 */
    public int getRefundAmountOfCent(){
    	return DqPayUtils.yuanToCent(refundAmount);
    }

    public String getRefundNo() {
        return refundNo;
    }

    public void setRefundNo(String refundNo) {
        this.refundNo = refundNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public DqRefundOrderDTO() {
    }

    public DqRefundOrderDTO(String refundNo, String tradeNo, BigDecimal refundAmount) {
        this.refundNo = refundNo;
        this.tradeNo = tradeNo;
        this.refundAmount = refundAmount;
    }

    public DqRefundOrderDTO(String tradeNo, String outTradeNo, BigDecimal refundAmount, BigDecimal totalAmount) {
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.refundAmount = refundAmount;
        this.totalAmount = totalAmount;
    }

    public DqRefundOrderDTO(String refundNo, String tradeNo, String outTradeNo, BigDecimal refundAmount, BigDecimal totalAmount) {
        this.refundNo = refundNo;
        this.tradeNo = tradeNo;
        this.outTradeNo = outTradeNo;
        this.refundAmount = refundAmount;
        this.totalAmount = totalAmount;
    }
}
