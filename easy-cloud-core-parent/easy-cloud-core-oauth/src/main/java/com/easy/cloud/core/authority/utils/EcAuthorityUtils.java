package com.easy.cloud.core.authority.utils;

import com.easy.cloud.core.authority.config.EcAuthorityConfig;
import com.easy.cloud.core.authority.config.EcBaseAuthorityCustomFilterConfig;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

;

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

    private static EcBaseAuthorityCustomFilterConfig customFilterConfig;

    private static Logger logger = LoggerFactory.getLogger(EcAuthorityUtils.class);

    @Autowired
    public void setAuthorityConfig(EcAuthorityConfig authorityConfig) {
        EcAuthorityUtils.authorityConfig = authorityConfig;
    }

    @Autowired
    public void setCustomFilterConfig(EcBaseAuthorityCustomFilterConfig customFilterConfig) {
        EcAuthorityUtils.customFilterConfig = customFilterConfig;
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

    /**
     * <p>
     * 校验过滤器名称
     * </p>
     *
     * @return void
     * @author daiqi
     * @date 2018/6/27 11:31
     */
    public static List<String> getSupportFilterNames() {
        List<String> filterNames = new ArrayList<>();
        for (Enum defaultFilter : DefaultFilter.values()) {
            filterNames.add(defaultFilter.name());
        }
        for (String customFilterName : customFilterConfig.customFilters().keySet()) {
            filterNames.add(customFilterName);
        }
        return filterNames;
    }
}
