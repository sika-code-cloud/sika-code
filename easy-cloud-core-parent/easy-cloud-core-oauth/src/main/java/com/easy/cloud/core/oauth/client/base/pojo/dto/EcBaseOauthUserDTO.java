package com.easy.cloud.core.oauth.client.base.pojo.dto;

/**
 * @author daiqi
 * @create 2018-07-16 11:46
 */
public class EcBaseOauthUserDTO extends EcBaseOauthResourceDTO<String> {
    /**
     * 错误代码
     */
    private String errCode;
    /**
     * 错误信息
     */
    private String errMsg;
    /**
     * 用户的唯一标识
     */
    private String openid;
    /**
     * 用户昵称
     */
    private String nickname;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String getOauthResourceId() {
        return openid;
    }
}
