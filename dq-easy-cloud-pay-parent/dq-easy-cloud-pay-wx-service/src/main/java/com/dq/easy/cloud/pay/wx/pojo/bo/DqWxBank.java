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
    ICBC("1002", "工商银行"),
    ABC("1005", "农业银行"),
    BOC("1026", "中国银行"),
    CCB("1003", "建设银行"),
    CMB("1001", "招商银行"),
    PSBC("1066", "邮储银行"),
    BOCOM("1020", "交通银行"),
    SPDB("1004", "浦发银行"),
    CMBC("1006", "民生银行"),
    CIB("1009", "兴业银行"),
    PINGAN("1010", "平安银行"),
    CCITICB("1021", "中信银行"),
    HXB("1025", "华夏银行"),
    CGBC("1027", "广发银行"),
    CEBB("1022", "光大银行"),
    BOB("1032", "北京银行"),
    NBCB("1056", "宁波银行");

    private String name;

    private String code;

    DqWxBank(String code, String name) {
        this.code = code;
        this.name = name;
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
