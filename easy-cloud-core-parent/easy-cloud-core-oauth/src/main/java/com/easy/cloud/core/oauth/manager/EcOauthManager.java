package com.easy.cloud.core.oauth.manager;

import com.easy.cloud.core.basic.utils.EcBaseUtils;
import com.easy.cloud.core.common.log.utils.EcLogUtils;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthResourceDTO;
import com.easy.cloud.core.oauth.client.base.service.EcOauthService;
import com.easy.cloud.core.oauth.client.base.token.EcBaseOauthToken;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * 授权tokenManager类
 *
 * @author daiqi
 * @create 2018-06-29 17:37
 */
@Component
public class EcOauthManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EcOauthService oauthService;

    /**
     * <p>
     * 获取授权资源数据传输对象
     * </p>
     *
     * @param oauth2Token
     * @return com.easy.cloud.core.oauth.client.base.pojo.dto.EcBaseOauthUserDTO
     * @author daiqi
     * @date 2018/7/16 16:07
     */
    @Transactional(rollbackFor = Exception.class)
    public EcBaseOauthResourceDTO getOauthResourceDTO(EcBaseOauthToken oauth2Token) {
        try {
            // 获取授权token
            Map<String, Object> oAuthResponseParam = getAccessToken(oauth2Token);
            EcBaseOauthResourceDTO oauthResourceDTO = oauth2Token.doGetOauthResource(oAuthResponseParam);
            oauthService.saveOauthResource(oauthResourceDTO);
            return oauthResourceDTO;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException(e);
        }
    }

    /**
     * 获取accessToken
     */
    private Map<String, Object> getAccessToken(EcBaseOauthToken oauth2Token) throws Exception {
        Map<String, Object> accessToken = new HashMap<>(oauth2Token.getResourceRequestParam());
        EcBaseAccessTokenDTO localAccessToken = oauthService.getAccessToken(oauth2Token.getAccessTokenChannel());
        if (EcBaseUtils.isNotNull(localAccessToken) && localAccessToken.available()) {
            accessToken.putAll(localAccessToken.getAccessTokenParam());
            EcLogUtils.info("从缓存中获取accessToken数据" , accessToken, logger);
        } else {
            accessToken.putAll(oauth2Token.doGetOauthAccessToken());
            EcLogUtils.info("从第三方获取accessToken数据" , accessToken, logger);
            // 保存accessToken
            oauthService.saveAccessToken(oauth2Token.getAccessTokenChannel(), oauth2Token.getAccessTokenDTO(accessToken));
        }
        return accessToken;
    }

}
