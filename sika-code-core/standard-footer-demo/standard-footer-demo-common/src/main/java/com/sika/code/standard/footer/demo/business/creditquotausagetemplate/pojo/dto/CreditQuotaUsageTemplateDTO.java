package com.sika.code.standard.footer.demo.business.creditquotausagetemplate.pojo.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 额度模板类型配置
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:03:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CreditQuotaUsageTemplateDTO extends BaseStandardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据表id
     */
    private Long creditQuotaUsageTemplateId;
    /**
     * 规则类型 [1:授信成功的额度模板 2:授信失败的额度模板]
     */
    private Integer ruleType;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 客户押金类型 0:非全额 1:全额
     */
    private Integer customerDepositType;
    /**
     * 授信额度 以具体规则计算而来，此处为了兼容
     */
    private BigDecimal creditQuota;
    /**
     * 单台限制价值 以具体规则计算而来，此处为了兼容
     */
    private BigDecimal singleLimitPrice;
    /**
     * 押金期数，以具体规则计算而来，此处为了兼容
     */
    private Integer depositPhase;
    /**
     * 付款期数
     */
    private Integer paymentPhase;
    /**
     * 支付方式 1:先用后付 2:先付后用
     */
    private Integer payMode;
    /**
     * 限制苹果类型，1:限制 0:不限制
     */
    private Integer limitAppleType;
    /**
     * 苹果设备押金期数，以具体规则计算而来，此处为了兼容
     */
    private Integer appleDepositPhase;
    /**
     * 苹果设备付款期数
     */
    private Integer applePaymentPhase;
    /**
     * 限制全新类型，1:限制 0:不限制
     */
    private Integer limitNewType;
    /**
     * 全新押金期数，以具体规则计算而来，此处为了兼容
     */
    private Integer newDepositPhase;
    /**
     * 全新付款期数
     */
    private Integer newPaymentPhase;
    /**
     * 结算日类型，0:非统一结算 1:统一结算
     */
    private Integer statementDateType;
    /**
     * 最晚支付时间 单位天
     */
    private Integer latestPaymentDate;
    /**
     * 货到付款类型 0:非货到付款 1:货到付款
     */
    private Integer paymentType;
    /**
     * 收货类型 0:非送货上门 1:送货上门
     */
    private Integer receivingType;
    /**
     * 回访频率（单位，月）
     */
    private Integer returnVisitFrequency;
    /**
     * 展示类型 [0:不展示 1:展示]
     */
    private Integer showType;
    /**
     * 排序编号 序号越小，排序越靠前
     */
    private Integer orderNum;
    /**
     * 优先级，序号越小优先级越高
     */
    private Integer priority;

}
