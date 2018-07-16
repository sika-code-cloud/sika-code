package com.easy.cloud.core.oauth.authorize.wechat.response.resource;

import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseOauthUserDTO;
import com.easy.cloud.core.oauth.authorize.base.response.token.EcBaseOauthResourceResponse;
import com.easy.cloud.core.oauth.authorize.wechat.pojo.dto.EcWechatUserDTO;

/**
 * @author daiqi
 * @create 2018-07-16 10:48
 */
public class EcWechatOauthResourceResponse extends EcBaseOauthResourceResponse {
    @Override
    public Class<? extends EcBaseOauthUserDTO> getResourceDTOClass() {
        return EcWechatUserDTO.class;
    }
}
