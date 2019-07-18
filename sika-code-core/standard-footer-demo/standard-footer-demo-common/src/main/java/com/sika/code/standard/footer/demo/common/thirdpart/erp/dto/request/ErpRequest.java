package com.sika.code.standard.footer.demo.common.thirdpart.erp.dto.request;

import lombok.Data;

/**
 * Created by IntelliJ IDEA
 * User: liuyong
 * Date: 2018/9/11
 * Time: 15:49
 */

@Data
public class ErpRequest {
    /**
     * 业务系统APP ID由ERP系统生成，提供给业务系统
     */
    private String erpAppId;
    /**
     * 业务系统app secret由ERP系统生成，提供给业务系统
     */
    private String erpAppSecret;
    /**
     * 第三方客户编号
     */
    private String thirdSystemCustomerNo;

}
