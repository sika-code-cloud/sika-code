package com.easy.cloud.core.authority.config;

import com.easy.cloud.core.authority.filter.login.EcLoginEmailPasswordFilter;
import com.easy.cloud.core.authority.filter.login.EcLoginPhonePasswordFilter;
import com.easy.cloud.core.authority.filter.login.EcLoginPhoneVerifyCodeFilter;
import com.easy.cloud.core.authority.filter.login.EcLoginUsernamePasswordFilter;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 基础自定义过滤器配置类
 * </p>
 * <pre>
 *     子类可以通过继承该基础类添加自己的自定义过滤器
 *     同时使用@Configuration注解交给容器管理或者使用@Bean等等
 * </pre>
 *
 * @author daiqi
 * @date 2018/6/27 20:34
 * @return
 */
public class EcBaseAuthorityCustomFilterConfig {
    private static Map<String, Filter> customFilters = new LinkedHashMap<>();
    public final Map<String, Filter> customFilters() {
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
    private final synchronized void loadFilters() {
        putCustomFilter("loginEmailPasswordFilter", new EcLoginEmailPasswordFilter());
        putCustomFilter("loginPhonePasswordFilter", new EcLoginPhonePasswordFilter());
        putCustomFilter("loginPhoneVerifyCodeFilter", new EcLoginPhoneVerifyCodeFilter());
        putCustomFilter("loginUsernamePasswordFilter", new EcLoginUsernamePasswordFilter());
        loadOtherFilters();
    }

    protected synchronized void loadOtherFilters() {

    }

    protected final synchronized void putCustomFilter(String name, Filter filter) {
        if (customFilters.containsKey(name)) {
            EcAssert.verifyDataExistent(name, name);
        }
        customFilters.put(name, filter);
    }


}
