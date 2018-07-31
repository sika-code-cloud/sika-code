package com.easy.cloud.core.oauth.client.zfb.pojo.dto;

import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthUserDTO;

/**
 * @author daiqi
 * @create 2018-07-16 11:45
 */
public class EcZfbUserDTO extends EcBaseOauthUserDTO {
    /** 用户头像	String	如果没有数据的时候不会返回该数据，请做好容错 */
    private String avatar;
    /** 省份	String	用户注册时填写的省份 如果没有数据的时候不会返回该数据，请做好容错 */
    private String province;
    /** 城市	String	用户注册时填写的城市， 如果没有数据的时候不会返回该数据，请做好容错 */
    private String city;
    /** 用户性别	String	M为男性，F为女性， 如果没有数据的时候不会返回该数据，请做好容错 */
    private String gender;
    /** 当前用户的userId	String	支付宝用户的userId */
    private String userId;
    /** 用户类型	String	1代表公司账户2代表个人账户 */
    private String userType;
    /** 是否通过实名认证	T是通过 F是没有实名认证 */
    private String userStatus;
    /** 是否通过实名认证	T是通过 F是没有实名认证 */
    private String isCertified;
    /** 是否是学生 T是学生 F不是学生 */
    private String isStudentCertified;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        super.setOpenid(userId);
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(String isCertified) {
        this.isCertified = isCertified;
    }

    public String getIsStudentCertified() {
        return isStudentCertified;
    }

    public void setIsStudentCertified(String isStudentCertified) {
        this.isStudentCertified = isStudentCertified;
    }

    public void setCode(String code) {
        super.setErrCode(code);
    }

    public void setMsg(String msg) {
        super.setErrMsg(msg);
    }

}
