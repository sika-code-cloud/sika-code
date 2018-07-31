package com.easy.cloud.core.oauth.client.base.pojo.dto;

import com.easy.cloud.core.common.date.utils.EcDateUtils;
import org.apache.oltu.oauth2.common.token.OAuthToken;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daiqi
 * @create 2018-07-16 15:37
 */
public abstract class EcBaseAccessTokenDTO implements OAuthToken {

    protected Map<String, Object> accessTokenParam;

    /** 获取accessToken的上一次时间 */
    protected Long accessTokenPreTimeSec;

    public EcBaseAccessTokenDTO() {
        accessTokenParam = new HashMap<>();
    }

    public EcBaseAccessTokenDTO(Map<String, Object> accessTokenParam) {
        this.accessTokenParam = accessTokenParam;
    }

    public EcBaseAccessTokenDTO putAccessTokenParam(Map<String, Object> accessTokenParam) {
        this.accessTokenParam.putAll(accessTokenParam);
        return this;
    }
    public Boolean available() {
        if (this.accessTokenPreTimeSec == null) {
            return false;
        }
        Long currentTimeMillis = EcDateUtils.getCurrentTimeMillis() / 1000;

        Long cCustomEffectiveTimeSec = getCustomEffectiveTimeSec();
        if (currentTimeMillis - this.accessTokenPreTimeSec <= cCustomEffectiveTimeSec) {
            return true;
        }
        return false;
    }

    public Map<String, Object> getAccessTokenParam() {
        return accessTokenParam;
    }

    public void setAccessTokenParam(Map<String, Object> accessTokenParam) {
        this.accessTokenParam = accessTokenParam;
    }

    public Long getAccessTokenPreTimeSec() {
        return accessTokenPreTimeSec;
    }

    public void setAccessTokenPreTimeSec(Long accessTokenPreTimeSec) {
        this.accessTokenPreTimeSec = accessTokenPreTimeSec;
    }

    public abstract Long getCustomEffectiveTimeSec();
}
