package com.easy.cloud.core.authority.manager;

import com.easy.cloud.core.authority.realm.EcAuthorityRealm;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.json.utils.EcJSONUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.crazycake.shiro.AuthCachePrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author daiqi
 * @create 2018-06-25 17:26
 */
@Component
public class EcAuthorityManager {
    @Autowired
    private ShiroFilterFactoryBean shiroFilterFactoryBean;
    /**
     * shiro 配置的cacheManager， 需要使用Spring bean进行注入
     */
    @Autowired
    private CacheManager cacheManager;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 清除用户的权限
     * <p>
     * 这里需要注意的是，
     * 网上很多实现都是这里只传递一个String类型的username过来，根据这个String当key去清除Cache
     * 但是Shiro在缓存用户权限的时候使用的key并不是String类型，所以调用remove的时候并不能清除缓存的权限
     * <p>
     * shiro缓存时使用的key，是登录时使用的SimplePrincipalCollection对象，所以remove的时候需要的不是一个String值，
     * 具体可以参考下面方法中打印cache的key的过程, 可以看到打印出key的类是 `class org.apache.shiro.subject.SimplePrincipalCollection`
     * 所以你cache.remove(String username)肯定清除不了
     *
     * @param principal
     */
    public void clearAuthorizationInfo(AuthCachePrincipal principal) {
        logger.info("clear the user: " + principal.toString() + "'s authorizationInfo");
        Cache<Object, Object> cache = cacheManager.getCache(EcAuthorityRealm.class.getName() + ".authorizationCache");
        SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, principal.getAuthCacheKey());
        for (Object k : cache.keys()) {
            logger.info(k.getClass().getName());
        }
        cache.remove(principals);
    }

    /**
     * 更新过滤器链
     */
    public synchronized EcBaseServiceResult updateFilterChains(Map<String, String> filterChainDefinitionMap) {
        Map<String, String> oldFilterChainDefinitionMap = EcJSONUtils.parseObject(shiroFilterFactoryBean.getFilterChainDefinitionMap(), LinkedHashMap.class);
        try {
            doUpdateFilterChains(filterChainDefinitionMap);
            logger.info("更新权限成功！！");
        } catch (Exception e) {
            // 发送异常 还原过滤器链
            doUpdateFilterChains(oldFilterChainDefinitionMap);
            throw new RuntimeException(e.getMessage(), e);
        }
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    /**
     * <p>
     * 执行更新过滤器链
     * </p>
     *
     * @param filterChainDefinitionMap
     * @return void
     * @author daiqi
     * @date 2018/6/27 13:00
     */
    private void doUpdateFilterChains(Map<String, String> filterChainDefinitionMap) {
        AbstractShiroFilter shiroFilter;
        try {
            shiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
        // 清空老的过滤器链
        manager.getFilterChains().clear();
        shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        // 重新构建生成
        Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
        for (Map.Entry<String, String> entry : chains.entrySet()) {
            String url = entry.getKey();
            String chainDefinition = entry.getValue().trim().replace(" ", "");
            manager.createChain(url, chainDefinition);
        }
    }
}
