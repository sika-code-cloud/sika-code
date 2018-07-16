package com.easy.cloud.core.oauth.authorize.wechat.pojo.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseOauthUserDTO;

/**
 * @author daiqi
 * @create 2018-07-16 11:45
 */
public class EcWechatUserDTO extends EcBaseOauthUserDTO {

    @Override
    @JSONField(name = "errcode")
    public void setErrCode(String errCode) {
        super.setErrCode(errCode);
    }

    @Override
    @JSONField(name = "errmsg")
    public void setErrMsg(String errMsg) {
        super.setErrMsg(errMsg);
    }
}
