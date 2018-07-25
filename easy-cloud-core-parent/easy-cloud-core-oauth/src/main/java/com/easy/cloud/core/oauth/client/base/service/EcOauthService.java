package com.easy.cloud.core.oauth.client.base.service;

import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthResourceDTO;

/**
 * @author daiqi
 * @create 2018-06-29 17:37
 */
public interface EcOauthService {
    /**
     * <p>
     * 根据accessToken渠道获取accessToken
     * </p>
     *
     * @param accessTokenChannel
     * @return com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseAccessTokenDTO
     * @author daiqi
     * @date 2018/7/25 17:26
     */
    EcBaseAccessTokenDTO getAccessToken(Integer accessTokenChannel);

    /**
     * <p>
     * 保存accessToken
     * </p>
     *
     * @param accessTokenChannel
     * @param accessTokenDTO
     * @return void
     * @author daiqi
     * @date 2018/7/25 17:25
     */
    void saveAccessToken(Integer accessTokenChannel, EcBaseAccessTokenDTO accessTokenDTO);

    /**
     * <p>
     * 保存授权资源
     * </p>
     *
     * @param baseOauthResourceDTO
     * @return void
     * @author daiqi
     * @date 2018/7/25 17:25
     */
    void saveOauthResource(EcBaseOauthResourceDTO baseOauthResourceDTO);

    /**
     * <p>
     * 获取授权资源信息
     * </p>
     *
     * @param oauthResourceId
     * @return com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthResourceDTO
     * @author daiqi
     * @date 2018/7/25 17:27
     */
    EcBaseOauthResourceDTO getOauthResource(Object oauthResourceId);
}
