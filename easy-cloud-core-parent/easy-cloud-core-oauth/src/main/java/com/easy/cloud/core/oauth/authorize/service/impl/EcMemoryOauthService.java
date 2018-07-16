package com.easy.cloud.core.oauth.authorize.service.impl;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.date.utils.EcDateUtils;
import com.easy.cloud.core.oauth.authorize.base.config.EcAccessTokenChannelConfig;
import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.authorize.service.EcOauthService;
import freemarker.template.utility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @author daiqi
 * @create 2018-07-16 16:17
 */
@Service
public class EcMemoryOauthService implements EcOauthService {

    @Override
    public EcBaseAccessTokenDTO getAccessToken(Integer accessTokenChannel) {
        return EcAccessTokenChannelConfig.getAccessTokenDTO(accessTokenChannel);
    }

    @Override
    public void saveAccessToken(Integer accessTokenChannel, EcBaseAccessTokenDTO accessTokenDTO) {
        doSaveAccessToken(accessTokenChannel, accessTokenDTO);
    }

    private synchronized void doSaveAccessToken(Integer accessTokenChannel, EcBaseAccessTokenDTO accessTokenDTO) {
        accessTokenDTO.setAccessTokenPreTimeSec(EcDateUtils.getCurrentTimeSec());
        EcAccessTokenChannelConfig.putAccessTokenChannel(accessTokenChannel, accessTokenDTO);
    }
}
