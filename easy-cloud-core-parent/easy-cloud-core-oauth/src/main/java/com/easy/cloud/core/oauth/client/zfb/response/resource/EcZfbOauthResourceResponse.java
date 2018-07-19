package com.easy.cloud.core.oauth.client.zfb.response.resource;

import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthUserDTO;
import com.easy.cloud.core.oauth.client.base.response.token.EcBaseOauthResourceResponse;
import com.easy.cloud.core.oauth.client.zfb.pojo.dto.EcZfbUserDTO;

/**
 * @author daiqi
 * @create 2018-07-16 10:48
 */
public class EcZfbOauthResourceResponse extends EcBaseOauthResourceResponse {
    @Override
    public Class<? extends EcBaseOauthUserDTO> getResourceDTOClass() {
        return EcZfbUserDTO.class;
    }
}
