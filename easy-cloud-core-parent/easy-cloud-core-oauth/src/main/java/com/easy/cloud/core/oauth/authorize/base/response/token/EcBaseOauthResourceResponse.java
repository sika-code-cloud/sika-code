package com.easy.cloud.core.oauth.authorize.base.response.token;

import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseOauthUserDTO;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;

/**
 * @author daiqi
 * @create 2018-07-16 10:36
 */
public abstract class EcBaseOauthResourceResponse extends OAuthResourceResponse {

    public <T> T getResourceObj(Class<T> resourceClass) {
        return EcJSONUtils.parseObject(super.getBody(), resourceClass);
    }

    public abstract Class<? extends EcBaseOauthUserDTO> getResourceDTOClass();
}
