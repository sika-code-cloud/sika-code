package com.dq.easy.cloud.payorder.pojo.entity;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Date;
import com.dq.easy.cloud.module.basic.pojo.entity.DqBaseEntity;

/**
 * 描述：支付单表模型
 * @author THINK
 * @date 2018-03-23 18:13:39
 */
@Entity
@Table(name="payment_pay_order")
public class PayOrderEntity extends DqBaseEntity {

    /** 支付编号 */
    @Column(name = "pay_order_no",columnDefinition = "VARCHAR")
    private String payOrderNo;
    /** 支付类型，1网银支付，2公众号支付  */
    @Column(name = "pay_type",columnDefinition = "INT")
    private Integer payType;
    /** 客户ID */
    @Column(name = "customer_id",columnDefinition = "INT")
    private Integer customerId;
    /** 业务系统类型，1为凌雄商城 */
    @Column(name = "business_system_type",columnDefinition = "INT")
    private Integer businessSystemType;
    /** 业务系统订单编号 */
    @Column(name = "business_order_no",columnDefinition = "VARCHAR")
    private String businessOrderNo;
    /** 支付备注 */
    @Column(name = "business_order_remark",columnDefinition = "VARCHAR")
    private String businessOrderRemark;
    /** 业务系统的回调地址 */
    @Column(name = "business_notify_url",columnDefinition = "TEXT")
    private String businessNotifyUrl;
    /** 发起支付时间 */
    @Column(name = "pay_time",columnDefinition = "DATETIME")
    private Date payTime;
    /** 实际支付时间 */
    @Column(name = "pay_time_real",columnDefinition = "DATETIME")
    private Date payTimeReal;
    /** 预计支付金额 */
    @Column(name = "pay_amount_expect",columnDefinition = "DECIMAL")
    private BigDecimal payAmountExpect;
    /** 实际支付金额 */
    @Column(name = "pay_amount_real",columnDefinition = "DECIMAL")
    private BigDecimal payAmountReal;
    /** 支付状态，0初始化，4支付中，8已经支付，16付款失败，20付款失效 */
    @Column(name = "pay_status",columnDefinition = "INT")
    private Integer payStatus;
    /** 最后一次通知时间 */
    @Column(name = "last_notify_time",columnDefinition = "DATETIME")
    private Date lastNotifyTime;
    /** 支付名称 */
    @Column(name = "pay_name",columnDefinition = "VARCHAR")
    private String payName;
    /** 支付主题 */
    @Column(name = "pay_subject",columnDefinition = "VARCHAR")
    private String paySubject;
    /** 支付描述 */
    @Column(name = "pay_description",columnDefinition = "VARCHAR")
    private String payDescription;
    /** 微信公众号或者支付宝生活号的用户id */
    @Column(name = "open_id",columnDefinition = "VARCHAR")
    private String openId;
    /** 状态：0不可用；1可用；2删除 */
    @Column(name = "data_status",columnDefinition = "INT")
    private Integer dataStatus;
    /** 添加时间 */
    @Column(name = "create_time",columnDefinition = "DATETIME")
    private Date createTime;
    /** 添加时间 */
    @Column(name = "update_time",columnDefinition = "DATETIME")
    private Date updateTime;
    /** 添加人 */
    @Column(name = "create_user",columnDefinition = "VARCHAR")
    private String createUser;
    /** 修改人 */
    @Column(name = "update_user",columnDefinition = "VARCHAR")
    private String updateUser;

    public String getPayOrderNo() {
        return this.payOrderNo;
    }

    public void setPayOrderNo(String payOrderNo) {
        this.payOrderNo = payOrderNo;
    }
    
    public Integer getPayType() {
        return this.payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
    
    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public Integer getBusinessSystemType() {
        return this.businessSystemType;
    }

    public void setBusinessSystemType(Integer businessSystemType) {
        this.businessSystemType = businessSystemType;
    }
    
    public String getBusinessOrderNo() {
        return this.businessOrderNo;
    }

    public void setBusinessOrderNo(String businessOrderNo) {
        this.businessOrderNo = businessOrderNo;
    }
    
    public String getBusinessOrderRemark() {
        return this.businessOrderRemark;
    }

    public void setBusinessOrderRemark(String businessOrderRemark) {
        this.businessOrderRemark = businessOrderRemark;
    }
    
    public String getBusinessNotifyUrl() {
        return this.businessNotifyUrl;
    }

    public void setBusinessNotifyUrl(String businessNotifyUrl) {
        this.businessNotifyUrl = businessNotifyUrl;
    }
    
    public Date getPayTime() {
        return this.payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    public Date getPayTimeReal() {
        return this.payTimeReal;
    }

    public void setPayTimeReal(Date payTimeReal) {
        this.payTimeReal = payTimeReal;
    }
    
    public BigDecimal getPayAmountExpect() {
        return this.payAmountExpect;
    }

    public void setPayAmountExpect(BigDecimal payAmountExpect) {
        this.payAmountExpect = payAmountExpect;
    }
    
    public BigDecimal getPayAmountReal() {
        return this.payAmountReal;
    }

    public void setPayAmountReal(BigDecimal payAmountReal) {
        this.payAmountReal = payAmountReal;
    }
    
    public Integer getPayStatus() {
        return this.payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
    
    public Date getLastNotifyTime() {
        return this.lastNotifyTime;
    }

    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
    }
    
    public String getPayName() {
        return this.payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }
    
    public String getPaySubject() {
        return this.paySubject;
    }

    public void setPaySubject(String paySubject) {
        this.paySubject = paySubject;
    }
    
    public String getPayDescription() {
        return this.payDescription;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription;
    }
    
    public String getOpenId() {
        return this.openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
    
    public Integer getDataStatus() {
        return this.dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }
    
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getCreateUser() {
        return this.createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public String getUpdateUser() {
        return this.updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
}