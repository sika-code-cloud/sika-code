package com.sika.code.demo.infrastructure.db.business.order.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.sika.code.demo.infrastructure.db.common.po.BaseBizPO;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * mchOrder对象 t_mch_order
 * 
 * @author dwq
 * @date 2023-05-23
 */
@TableName("t_mch_order")
public class OrderPO extends BaseBizPO implements Serializable{

    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 交易主体客户号 */
    private String custId;

    /** 交易主体商户号 */
    private String userCode;

    /** 收款方客户号 */
    private String payeeCustId;

    /** 收款方商户号 */
    private String payeeUserCode;

    /** 收款方机构号 */
    private String payeeOrgNo;

    /** 交易发起方编号 */
    private String originatorCustId;

    /** 交易发起方商户号 */
    private String originatorUserCode;

    /** 服务商机构号 */
    private String originatorOrgNo;

    /** 服务商编号 */
    private String agentNo;

    /** 平台系统调用者id */
    private String sysCallerId;

    /** 平台系统调用机器ip */
    private String sysCallerIp;

    /** 银盛订单号 */
    private String orderNo;

    /** 商户订单号 */
    private String outTradeNo;

    /** 支付请求流水号 */
    private String payReqNo;

    /** 订单类型 */
    private Integer orderType;

    /** 签约产品 */
    private Integer signProduct;

    /** 业务代码 */
    private String bizCode;

    /** 交易类型 */
    private Integer transType;

    /** 支付方式 */
    private Integer payMode;

    /** 支付产品 */
    private Integer payProduct;

    /** 币种 */
    private String currency;

    /**
     * 订单金额
     */
    private Long orderAmount;

    /** 是否允重复次支付 */
    private Integer allowRepeatPay;

    /** 关单来源 */
    private Integer closeSource;

    /** 订单状态 */
    private Integer state;

    /** 结算状态 */
    private Integer settleState;

    /** 异常\失败原因描述 */
    private String stateDesc;

    /** 订单超时绝对时间 */
    private LocalDateTime timeExpire;

    /** 会计日期 */
    private String accountDate;

    /** 支付完成时间 */
    private LocalDateTime completeTime;

    /** 退款状态 */
    private Integer refundState;

    /** 结算周期 */
    private Integer settleCycle;

    /** 清结算标识 */
    private Integer clearingSettleFlag;

    /** 支付中心的支付流水号 */
    private String transactionId;

    /** 结算流水id */
    private String settleId;

    /** 子商户ip */
    private String subMchIp;

    /** 订单来源 */
    private Integer orderSource;

    /** 商户传入的附加信息 */
    private String mchAttach;

    /** 商品描述 */
    private String orderSubject;

    /** 计费标签 */
    private Integer feeFlag;

    /** 实时同步计费标识 */
    private Integer feeSyncFlag;

    /** 预计结算发起方计费信息 */
    private String preSettleOriginatorFeeInfo;

    /** 预计结算收款方计费信息 */
    private String preSettlePayeeFeeInfo;

    /** 预计结算付款方计费信息 */
    private String preSettlePayerFeeInfo;

    /** 终端信息数据（B端） */
    private String terminalInfo;

    /** 商户门店编号 */
    private String mchStoreInfo;

    /** 通知信息 */
    private String notifyInfo;

    /** 二级商户信息 */
    private String subMerInfo;

    /** 发起方信息 */
    private String extOriginatorInfo;

    /** 收款方信息 */
    private String extPayeeInfo;

    /** 码牌信息 */
    private String qrCodeInfo;

    /** 物理存在状态 */
    private Integer physicalState;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 修改时间 */
    private LocalDateTime updateTime;

    /**
     * 预留字段1
     */
    private String reserved1;

    /**
     * 预留字段2
     */
    private String reserved2;

    /**
     * 预留字段3
     */
    private String reserved3;

    /**
     * 预留字段4，t_mch_order_detail.payable_amount
     */
    private Long reserved4;

    /**
     * 预留字段5 ，t_mch_order_detail.discount_total_amount
     */
    private Long reserved5;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }
    public void setCustId(String custId){
        this.custId = custId;
    }

    public String getCustId(){
        return custId;
    }
    public void setUserCode(String userCode){
        this.userCode = userCode;
    }

    public String getUserCode(){
        return userCode;
    }
    public void setPayeeCustId(String payeeCustId){
        this.payeeCustId = payeeCustId;
    }

    public String getPayeeCustId(){
        return payeeCustId;
    }
    public void setPayeeUserCode(String payeeUserCode){
        this.payeeUserCode = payeeUserCode;
    }

    public String getPayeeUserCode(){
        return payeeUserCode;
    }
    public void setPayeeOrgNo(String payeeOrgNo){
        this.payeeOrgNo = payeeOrgNo;
    }

    public String getPayeeOrgNo(){
        return payeeOrgNo;
    }
    public void setOriginatorCustId(String originatorCustId){
        this.originatorCustId = originatorCustId;
    }

    public String getOriginatorCustId(){
        return originatorCustId;
    }
    public void setOriginatorUserCode(String originatorUserCode){
        this.originatorUserCode = originatorUserCode;
    }

    public String getOriginatorUserCode(){
        return originatorUserCode;
    }
    public void setOriginatorOrgNo(String originatorOrgNo){
        this.originatorOrgNo = originatorOrgNo;
    }

    public String getOriginatorOrgNo(){
        return originatorOrgNo;
    }
    public void setAgentNo(String agentNo){
        this.agentNo = agentNo;
    }

    public String getAgentNo(){
        return agentNo;
    }
    public void setSysCallerId(String sysCallerId){
        this.sysCallerId = sysCallerId;
    }

    public String getSysCallerId(){
        return sysCallerId;
    }
    public void setSysCallerIp(String sysCallerIp){
        this.sysCallerIp = sysCallerIp;
    }

    public String getSysCallerIp(){
        return sysCallerIp;
    }
    public void setOrderNo(String orderNo){
        this.orderNo = orderNo;
    }

    public String getOrderNo(){
        return orderNo;
    }
    public void setOutTradeNo(String outTradeNo){
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo(){
        return outTradeNo;
    }
    public void setPayReqNo(String payReqNo){
        this.payReqNo = payReqNo;
    }

    public String getPayReqNo(){
        return payReqNo;
    }
    public void setOrderType(Integer orderType){
        this.orderType = orderType;
    }

    public Integer getOrderType(){
        return orderType;
    }
    public void setSignProduct(Integer signProduct){
        this.signProduct = signProduct;
    }

    public Integer getSignProduct(){
        return signProduct;
    }
    public void setBizCode(String bizCode){
        this.bizCode = bizCode;
    }

    public String getBizCode(){
        return bizCode;
    }
    public void setTransType(Integer transType){
        this.transType = transType;
    }

    public Integer getTransType(){
        return transType;
    }
    public void setPayMode(Integer payMode){
        this.payMode = payMode;
    }

    public Integer getPayMode(){
        return payMode;
    }
    public void setPayProduct(Integer payProduct){
        this.payProduct = payProduct;
    }

    public Integer getPayProduct(){
        return payProduct;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }

    public String getCurrency(){
        return currency;
    }
    public void setOrderAmount(Long orderAmount){
        this.orderAmount = orderAmount;
    }

    public Long getOrderAmount(){
        return orderAmount;
    }
    public void setAllowRepeatPay(Integer allowRepeatPay){
        this.allowRepeatPay = allowRepeatPay;
    }

    public Integer getAllowRepeatPay(){
        return allowRepeatPay;
    }
    public void setCloseSource(Integer closeSource){
        this.closeSource = closeSource;
    }

    public Integer getCloseSource(){
        return closeSource;
    }
    public void setState(Integer state){
        this.state = state;
    }

    public Integer getState(){
        return state;
    }
    public void setSettleState(Integer settleState){
        this.settleState = settleState;
    }

    public Integer getSettleState(){
        return settleState;
    }
    public void setStateDesc(String stateDesc){
        this.stateDesc = stateDesc;
    }

    public String getStateDesc(){
        return stateDesc;
    }
    public void setTimeExpire(LocalDateTime timeExpire){
        this.timeExpire = timeExpire;
    }

    public LocalDateTime getTimeExpire(){
        return timeExpire;
    }
    public void setAccountDate(String accountDate){
        this.accountDate = accountDate;
    }

    public String getAccountDate(){
        return accountDate;
    }
    public void setCompleteTime(LocalDateTime completeTime){
        this.completeTime = completeTime;
    }

    public LocalDateTime getCompleteTime(){
        return completeTime;
    }
    public void setRefundState(Integer refundState){
        this.refundState = refundState;
    }

    public Integer getRefundState(){
        return refundState;
    }
    public void setSettleCycle(Integer settleCycle){
        this.settleCycle = settleCycle;
    }

    public Integer getSettleCycle(){
        return settleCycle;
    }
    public void setClearingSettleFlag(Integer clearingSettleFlag){
        this.clearingSettleFlag = clearingSettleFlag;
    }

    public Integer getClearingSettleFlag(){
        return clearingSettleFlag;
    }
    public void setTransactionId(String transactionId){
        this.transactionId = transactionId;
    }

    public String getTransactionId(){
        return transactionId;
    }
    public void setSettleId(String settleId){
        this.settleId = settleId;
    }

    public String getSettleId(){
        return settleId;
    }

    public void setSubMchIp(String subMchIp){
        this.subMchIp = subMchIp;
    }

    public String getSubMchIp(){
        return subMchIp;
    }
    public void setOrderSource(Integer orderSource){
        this.orderSource = orderSource;
    }

    public Integer getOrderSource(){
        return orderSource;
    }
    public void setMchAttach(String mchAttach){
        this.mchAttach = mchAttach;
    }

    public String getMchAttach(){
        return mchAttach;
    }
    public void setOrderSubject(String orderSubject){
        this.orderSubject = orderSubject;
    }

    public String getOrderSubject(){
        return orderSubject;
    }
    public void setFeeFlag(Integer feeFlag){
        this.feeFlag = feeFlag;
    }

    public Integer getFeeFlag(){
        return feeFlag;
    }
    public void setFeeSyncFlag(Integer feeSyncFlag){
        this.feeSyncFlag = feeSyncFlag;
    }

    public Integer getFeeSyncFlag(){
        return feeSyncFlag;
    }
    public void setPreSettleOriginatorFeeInfo(String preSettleOriginatorFeeInfo){
        this.preSettleOriginatorFeeInfo = preSettleOriginatorFeeInfo;
    }

    public String getPreSettleOriginatorFeeInfo(){
        return preSettleOriginatorFeeInfo;
    }
    public void setPreSettlePayeeFeeInfo(String preSettlePayeeFeeInfo){
        this.preSettlePayeeFeeInfo = preSettlePayeeFeeInfo;
    }

    public String getPreSettlePayeeFeeInfo(){
        return preSettlePayeeFeeInfo;
    }
    public void setPreSettlePayerFeeInfo(String preSettlePayerFeeInfo){
        this.preSettlePayerFeeInfo = preSettlePayerFeeInfo;
    }

    public String getPreSettlePayerFeeInfo(){
        return preSettlePayerFeeInfo;
    }
    public void setTerminalInfo(String terminalInfo){
        this.terminalInfo = terminalInfo;
    }

    public String getTerminalInfo(){
        return terminalInfo;
    }
    public void setMchStoreInfo(String mchStoreInfo){
        this.mchStoreInfo = mchStoreInfo;
    }

    public String getMchStoreInfo(){
        return mchStoreInfo;
    }
    public void setNotifyInfo(String notifyInfo){
        this.notifyInfo = notifyInfo;
    }

    public String getNotifyInfo(){
        return notifyInfo;
    }
    public void setSubMerInfo(String subMerInfo){
        this.subMerInfo = subMerInfo;
    }

    public String getSubMerInfo(){
        return subMerInfo;
    }
    public void setPhysicalState(Integer physicalState){
        this.physicalState = physicalState;
    }

    public Integer getPhysicalState(){
        return physicalState;
    }
    public void setCreateTime(LocalDateTime createTime){
        this.createTime = createTime;
    }

    public LocalDateTime getCreateTime(){
        return createTime;
    }
    public void setUpdateTime(LocalDateTime updateTime){
        this.updateTime = updateTime;
    }

    public LocalDateTime getUpdateTime(){
        return updateTime;
    }

    public String getExtOriginatorInfo() {
        return extOriginatorInfo;
    }

    public void setExtOriginatorInfo(String extOriginatorInfo) {
        this.extOriginatorInfo = extOriginatorInfo;
    }

    public String getExtPayeeInfo() {
        return extPayeeInfo;
    }

    public void setExtPayeeInfo(String extPayeeInfo) {
        this.extPayeeInfo = extPayeeInfo;
    }

    public String getQrCodeInfo() {
        return qrCodeInfo;
    }

    public void setQrCodeInfo(String qrCodeInfo) {
        this.qrCodeInfo = qrCodeInfo;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public Long getReserved4() {
        return reserved4;
    }

    public void setReserved4(Long reserved4) {
        this.reserved4 = reserved4;
    }

    public Long getReserved5() {
        return reserved5;
    }

    public void setReserved5(Long reserved5) {
        this.reserved5 = reserved5;
    }
}
