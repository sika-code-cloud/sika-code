package com.easy.cloud.core.oauth.authorize.base.config;

import com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseAccessTokenDTO;
import com.easy.cloud.core.oauth.authorize.wechat.pojo.dto.EcWechatAccessTokenDTO;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * accessToken渠道配置类
 * </p>
 * <pre>
 *     拓展渠道可以通过继承此类将渠道dto通过putAccessTokenChannel方法放入map中
 * </pre>
 *
 * @author daiqi
 * @date 2018/7/16 16:40
 */
@Component
public class EcAccessTokenChannelConfig {
    public static final Integer WECHAT = 1;
    public static final Integer ZFB = 2;
    public static final Integer QQ = 3;
    public static final Integer BAIDU = 4;
    public static final Integer GITHUB = 5;

    private static Map<Integer, EcBaseAccessTokenDTO> channelDTOMap = new ConcurrentHashMap<>();

    static {
        putAccessTokenChannel(WECHAT, new EcWechatAccessTokenDTO());
    }


    public static void putAccessTokenChannel(Integer channel, EcBaseAccessTokenDTO accessTokenDTO) {
        channelDTOMap.put(channel, accessTokenDTO);
    }

    /**
     * <p>
     * 根据授权渠道类型获取accessToken数据传输对象
     * </p>
     *
     * @param channelType
     * @return com.easy.cloud.core.oauth.authorize.base.pojo.dto.EcBaseAccessTokenDTO
     * @author daiqi
     * @date 2018/7/16 16:40
     */
    public static EcBaseAccessTokenDTO getAccessTokenDTO(Integer channelType) {
        if (channelType == null) {
            return null;
        }
        return channelDTOMap.get(channelType);
    }

}