package com.easy.cloud.core.authority.utils;

import com.easy.cloud.core.authority.config.EcAuthorityConfig;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 权限校验工具类
 * </p>
 *
 * @author daiqi
 * @date 2018/6/26 10:13
 * @return
 */
@Component
public class EcAuthorityUtils {
    private static EcAuthorityConfig authorityConfig;

    @Autowired
    public void setAuthorityConfig(EcAuthorityConfig authorityConfig) {
        EcAuthorityUtils.authorityConfig = authorityConfig;
    }

    /**
     * <p>
     * 使用MD5进行加密
     * </p>
     *
     * @param needBeEncryptStr : String : 需要加密的字符串
     * @param salt             : String : 加密需要的盐
     * @return java.lang.String
     * @author daiqi
     * @date 2018/6/25 14:29
     */
    public static String encryptOfMD5(String needBeEncryptStr, String salt) {
        return new SimpleHash("MD5", needBeEncryptStr, salt, authorityConfig.getHashIterations()).toString();
    }
}
