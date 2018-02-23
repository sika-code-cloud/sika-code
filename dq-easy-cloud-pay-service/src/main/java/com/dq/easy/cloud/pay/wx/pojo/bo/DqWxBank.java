package com.dq.easy.cloud.pay.wx.pojo.bo;

import com.dq.easy.cloud.pay.model.base.inf.DqBank;

/**
 * 
 * <p>
 * 微信对应的银行列表
 * </p>
 *
 * @author daiqi
 * 创建时间    2018年2月23日 下午4:01:12
 */
public enum DqWxBank implements DqBank{
    ICBC("工商银行","1002"),
    ABC("农业银行","1005"),
    BOC("中国银行","1026"),
    CCB("建设银行","1003"),
    CMB("招商银行","1001"),
    PSBC("邮储银行","1066"),
    BOCOM("交通银行","1020"),
    SPDB("浦发银行","1004"),
    CMBC("民生银行","1006"),
    CIB("兴业银行","1009"),
    PINGAN("平安银行","1010"),
    CCITICB("中信银行","1021"),
    HXB("华夏银行","1025"),
    CGBC("广发银行","1027"),
    CEBB("光大银行","1022"),
    BOB("北京银行","1032"),
    NBCB("宁波银行","1056");

    private String name;

    private String code;

    DqWxBank(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * 获取银行的代码
     *
     * @return 银行的代码
     */
    @Override
    public String getCode() {
        return code;
    }

    /**
     * 获取银行的名称
     *
     * @return 银行的名称
     */
    @Override
    public String getName() {
        return name;
    }
}
