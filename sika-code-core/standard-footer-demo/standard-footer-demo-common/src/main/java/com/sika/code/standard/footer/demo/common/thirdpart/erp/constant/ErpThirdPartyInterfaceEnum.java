package com.sika.code.standard.footer.demo.common.thirdpart.erp.constant;


import com.sika.code.common.thirdpart.constant.BaseThirdPartyInterface;

/**
 * <p>
 * ERP接口枚举类
 * </p>
 *
 * @author daiqi
 * @date 2019/4/8 10:35
 */
public enum ErpThirdPartyInterfaceEnum implements BaseThirdPartyInterface {
    /**ERP 测试接口 */
    LOGIN("system_user/login/anon", "用户登录")
    ;
    public String uri;
    public String desc;

    ErpThirdPartyInterfaceEnum(String uri, String desc) {
        this.uri = uri;
        this.desc = desc;
    }

    @Override
    public String getUri() {
        return this.uri;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}