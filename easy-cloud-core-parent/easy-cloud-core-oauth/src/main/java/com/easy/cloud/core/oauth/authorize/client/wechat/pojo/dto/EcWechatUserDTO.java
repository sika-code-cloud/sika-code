package com.easy.cloud.core.oauth.authorize.client.wechat.pojo.dto;

import com.easy.cloud.core.oauth.authorize.client.base.pojo.dto.EcBaseOauthUserDTO;

/**
 * @author daiqi
 * @create 2018-07-16 11:45
 */
public class EcWechatUserDTO extends EcBaseOauthUserDTO {
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 用户省份
     */
    private String province;
    /**
     * 用户所在城市
     */
    private String city;
    /**
     * 用户所在国家
     */
    private String country;
    /**
     * 用户头像
     */
    private String headimgurl;
    /**
     * 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）
     */
    private String privilege;
    /**
     * 用户unionid
     */
    private String unionid;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public void setErrcode(String errcode) {
        super.setErrCode(errcode);
    }

    public void setErrmsg(String errmsg) {
        super.setErrMsg(errmsg);
    }
}
