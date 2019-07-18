package com.sika.code.standard.footer.demo.common.demo.util;

import com.sika.code.basic.util.Assert;
import com.sika.code.basic.util.BaseUtil;
import com.sika.code.common.threadlocal.manager.ThreadLocalManager;
import com.sika.code.standard.base.pojo.dto.BaseStandardDTO;
import com.sika.code.standard.footer.demo.common.demo.constant.DemoConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 风控工具类
 *
 * @author daiqi
 * @create 2019-05-29 19:09
 */
@Component
@Slf4j
public class DemoUtil {

    private static HttpServletRequest httpServletRequest;

    @Autowired
    public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
        DemoUtil.httpServletRequest = httpServletRequest;
    }

    /**
     * <p>
     * 获取报告编号
     * </p>
     *
     * @return java.lang.String
     * @author daiqi
     * @date 2019/5/31 10:11
     */
    public static String getCreditReportNo() {
        return (String) ThreadLocalManager.get(DemoConstant.CREDIT_REPORT_NO_NAME);
    }

    /**
     * <p>
     * 编号前缀
     * </p>
     *
     * @param creditReportNo
     * @return void
     * @author daiqi
     * @date 2019/5/31 10:07
     */
    public static void addCreditReportNo(String creditReportNo) {
        Assert.verifyStrEmpty(creditReportNo, "授信报告编号");
        ThreadLocalManager.set(DemoConstant.CREDIT_REPORT_NO_NAME, creditReportNo);
    }

    /**
     * <p>
     * 获取授信客户编号
     * </p>
     *
     * @param
     * @return java.lang.Long
     * @author daiqi
     * @date 2019/5/29 19:13
     */
    public static Long getCreditCustomerId() {
        CreditCustomerDTO creditCustomerDTO = getCreditCustomer();
        if (BaseUtil.isNotNull(creditCustomerDTO)) {
            return creditCustomerDTO.getCreditCustomerId();
        }
        return null;
    }

    /**
     * 获取业务系统客户编号
     */
    public static String getBusinessCustomerNo() {
        return getHttpRequest().getHeader(DemoConstant.BUSINESS_CUSTOMER_NO_NAME);
    }

    /**
     * 获取客户信息
     */
    public static CreditCustomerDTO getCreditCustomer() {
        try {
            return (CreditCustomerDTO) ThreadLocalManager.get(DemoConstant.CREDIT_CUSTOMER_NAME);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * add客户对象
     */
    public static void addCreditCustomer(CreditCustomerDTO creditCustomerDTO) {
        Assert.verifyObjNull(creditCustomerDTO, "授信客户对象");
        ThreadLocalManager.set(DemoConstant.CREDIT_CUSTOMER_NAME, creditCustomerDTO);
    }

    /**
     * 获取HttpRequest
     */
    public static HttpServletRequest getHttpRequest() {
        return httpServletRequest;
    }



    /**
     * <p>
     * 授信类型常规规则
     * </p>
     *
     * @author daiqi
     * @since 2019-05-29 19:55:00
     */
    @Data
    @EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    public static class CreditCustomerDTO extends BaseStandardDTO implements Serializable {

        private static final long serialVersionUID = 1L;

        /**
         * 数据表id
         */
        private Long creditCustomerId;
        /**
         * 业务系统客户编号
         */
        private String businessCustomerNo;
        /**
         * 业务系统客户名称 冗余
         */
        private String businessCustomerName;
        /**
         * 客户类型[0:未知 1:企业 2:个人]
         */
        private Integer businessCustomerType;
        /**
         * 业务系统类型
         */
        private Integer businessSystemType;
        /**
         * 授信类型[1:授信成功 2:授信中 3:授信失败]
         */
        private Integer creditStatus;
        /**
         * 是否展示 [0:不展示 1:展示]
         */
        private Integer showFlag;
        /**
         * 排序编号 序号越小，排序越靠前
         */
        private Integer orderNum;
        /**
         * 优先级，序号越小优先级越高
         */
        private Integer priority;

    }
}
