package com.easy.cloud.core.authority.config;

import com.easy.cloud.core.authority.filter.EcCustomFilter;
import com.easy.cloud.core.authority.filter.EcCustomFilter2;
import com.easy.cloud.core.authority.filter.login.EcLoginEmailPasswordFilter;
import com.easy.cloud.core.authority.filter.login.EcLoginPhonePasswordFilter;
import com.easy.cloud.core.authority.filter.login.EcLoginPhoneVerifyCodeFilter;
import com.easy.cloud.core.authority.filter.login.EcLoginUsernamePasswordFilter;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 自定义过滤器配置类
 *
 * @author daiqi
 * @create 2018-06-27 9:45
 */
@Configuration
public class EcCustomFilterConfig {
    private static Map<String, Filter> customFilters = new LinkedHashMap<>();
    @Autowired
    private EcLoginEmailPasswordFilter loginEmailPasswordFilter;
    @Autowired
    private EcLoginPhonePasswordFilter loginPhonePasswordFilter;
    @Autowired
    private EcLoginPhoneVerifyCodeFilter loginPhoneVerifyCodeFilter;
    @Autowired
    private EcLoginUsernamePasswordFilter loginUsernamePasswordFilter;

    public Map<String, Filter> customFilters() {
        if (EcMapUtils.isEmpty(customFilters)) {
            loadFilters();
        }
        return customFilters;
    }

    /**
     * <p>
     * 加载过滤器列表信息
     * </p>
     * <pre>
     *     对于自定义的过滤器链可以在该方法put到链中
     * </pre>
     *
     * @param
     * @return void
     * @author daiqi
     * @date 2018/6/27 10:42
     */
    private synchronized void loadFilters() {
        putCustomFilter("custom", new EcCustomFilter());
        putCustomFilter("custom2", new EcCustomFilter2());
        putCustomFilter("loginEmailPasswordFilter", loginEmailPasswordFilter);
        putCustomFilter("loginPhonePasswordFilter", loginPhonePasswordFilter);
        putCustomFilter("loginPhoneVerifyCodeFilter", loginPhoneVerifyCodeFilter);
        putCustomFilter("loginUsernamePasswordFilter", loginUsernamePasswordFilter);
    }

    private synchronized void putCustomFilter(String name, Filter filter) {
        if (customFilters.containsKey(name)) {
            EcAssert.verifyDataExistent(name, name);
        }
        customFilters.put(name, filter);
    }
}
