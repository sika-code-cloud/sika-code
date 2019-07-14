package com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.request;

import com.easy.cloud.standard.base.pojo.bo.request.BaseStandardAlterRequestBO;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.domain.CreditQuotaUsageTemplateDomain;
import com.easy.cloud.standard.footer.demo.business.creditquotausagetemplate.pojo.bo.response.CreditQuotaUsageTemplateAlterResponseBO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 额度模板类型配置 修改请求类
 * </p>
 *
 * @author daiqi
 * @since 2019-06-07 10:12:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public abstract class CreditQuotaUsageTemplateAlterRequestBO<ResponseBO extends CreditQuotaUsageTemplateAlterResponseBO> extends BaseStandardAlterRequestBO<ResponseBO> implements CreditQuotaUsageTemplateDomain {

    @Data
    @Accessors(chain = true)
    public static class CreditQuotaUsageTemplateAlterRequest extends BaseStandardAlterRequest {
        /**
         * 规则类型 [1:授信成功的额度模板 2:授信失败的额度模板]
         */
        protected Integer ruleType;
        /**
         * 规则名称
         */
        protected String ruleName;
        /**
         * 客户押金类型 0:非全额 1:全额
         */
        protected Integer customerDepositType;
        /**
         * 授信额度 以具体规则计算而来，此处为了兼容
         */
        protected BigDecimal creditQuota;
        /**
         * 单台限制价值 以具体规则计算而来，此处为了兼容
         */
        protected BigDecimal singleLimitPrice;
        /**
         * 押金期数，以具体规则计算而来，此处为了兼容
         */
        protected Integer depositPhase;
        /**
         * 付款期数
         */
        protected Integer paymentPhase;
        /**
         * 支付方式 1:先用后付 2:先付后用
         */
        protected Integer payMode;
        /**
         * 限制苹果类型，1:限制 0:不限制
         */
        protected Integer limitAppleType;
        /**
         * 苹果设备押金期数，以具体规则计算而来，此处为了兼容
         */
        protected Integer appleDepositPhase;
        /**
         * 苹果设备付款期数
         */
        protected Integer applePaymentPhase;
        /**
         * 限制全新类型，1:限制 0:不限制
         */
        protected Integer limitNewType;
        /**
         * 全新押金期数，以具体规则计算而来，此处为了兼容
         */
        protected Integer newDepositPhase;
        /**
         * 全新付款期数
         */
        protected Integer newPaymentPhase;
        /**
         * 结算日类型，0:非统一结算 1:统一结算
         */
        protected Integer statementDateType;
        /**
         * 最晚支付时间 单位天
         */
        protected Integer latestPaymentDate;
        /**
         * 货到付款类型 0:非货到付款 1:货到付款
         */
        protected Integer paymentType;
        /**
         * 收货类型 0:非送货上门 1:送货上门
         */
        protected Integer receivingType;
        /**
         * 回访频率（单位，月）
         */
        protected Integer returnVisitFrequency;
        /**
         * 展示类型 [0:不展示 1:展示]
         */
        protected Integer showType;
        /**
         * 排序编号 序号越小，排序越靠前
         */
        protected Integer orderNum;
        /**
         * 优先级，序号越小优先级越高
         */
        protected Integer priority;
    }

}
