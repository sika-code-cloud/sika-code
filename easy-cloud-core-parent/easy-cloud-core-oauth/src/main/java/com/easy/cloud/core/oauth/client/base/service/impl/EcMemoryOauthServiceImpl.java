package com.easy.cloud.core.oauth.client.base.service.impl;

import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.oauth.client.base.config.EcAccessTokenChannelConfig;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthResourceDTO;
import com.easy.cloud.core.oauth.client.base.service.EcOauthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author daiqi
 * @create 2018-07-16 16:17
 */
public class EcMemoryOauthServiceImpl implements EcOauthService {
    private static Map<Object, EcBaseOauthResourceDTO> oauthResourceDTOMap = new ConcurrentHashMap<>();
    @Override
    public EcBaseAccessTokenDTO getAccessToken(Integer accessTokenChannel) {
        return EcAccessTokenChannelConfig.getAccessTokenDTO(accessTokenChannel);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAccessToken(Integer accessTokenChannel, EcBaseAccessTokenDTO accessTokenDTO) {
        doSaveAccessToken(accessTokenChannel, accessTokenDTO);
    }

    private synchronized void doSaveAccessToken(Integer accessTokenChannel, EcBaseAccessTokenDTO accessTokenDTO) {
        accessTokenDTO.setAccessTokenPreTimeSec(EcDateUtils.getCurrentTimeSec());
        EcAccessTokenChannelConfig.putAccessTokenChannel(accessTokenChannel, accessTokenDTO);
    }

    @Override
    public synchronized void saveOauthResource(EcBaseOauthResourceDTO baseOauthResourceDTO) {
        oauthResourceDTOMap.put(baseOauthResourceDTO.getOauthResourceId(), baseOauthResourceDTO);
    }

    @Override
    public EcBaseOauthResourceDTO getOauthResource(Object oauthResourceId) {
        return oauthResourceDTOMap.get(oauthResourceId);
    }
}
