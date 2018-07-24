package com.easy.cloud.core.operator.sysfilterconfig.service.impl;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.cache.redis.handler.EcRedisTemplateHandler;
import com.easy.cloud.core.common.string.utils.EcStringUtils;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.query.SysFilterConfigQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 使用reidis处理过滤器配置信息
 *
 * @author daiqi
 * @create 2018-07-20 9:13
 */
@Service
@ConditionalOnProperty(value = "ec.oauth.filter.config.redis")
public class EcSysFilterConfigRedisServiceImpl extends EcBaseSysFilterConfigService {
    private static final String EC_OAUTH_FILTER_CONFIG_KEY = EcStringUtils.generateKeyUseColonSeparator("ec", "oauth", "filter", "config");
    @Autowired
    private EcRedisTemplateHandler redisTemplateHandler;

    @Override
    public List<SysFilterConfigDTO> listByAvailable() {
        return null;
    }

    @Override
    public synchronized EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        EcRedisTemplateHandler.putToHash(EC_OAUTH_FILTER_CONFIG_KEY, sysFilterConfigDTO.getUrlPattern(), sysFilterConfigDTO);
        return EcBaseServiceResult.newInstanceOfSuccess().buildResult(sysFilterConfigDTO);
    }

    @Override
    public synchronized EcBaseServiceResult updateSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        EcRedisTemplateHandler.putToHash(EC_OAUTH_FILTER_CONFIG_KEY, sysFilterConfigDTO.getUrlPattern(), sysFilterConfigDTO);
        return EcBaseServiceResult.newInstanceOfSuccess().buildResult(sysFilterConfigDTO);
    }

    @Override
    public synchronized Map<String, String> loadFilterChainDefinitions() {
        Map<String, SysFilterConfigDTO> filterConfigDTOMapFromCache = EcRedisTemplateHandler.entriesFromHash(EC_OAUTH_FILTER_CONFIG_KEY, SysFilterConfigDTO.class);
        return super.buildFilterChainDefinitions(filterConfigDTOMapFromCache);
    }

    @Override
    public EcBaseServiceResult deleteByQuery(SysFilterConfigQuery filterConfigQuery) {
        EcAssert.verifyObjNull(filterConfigQuery, "filterConfigQuery");
        EcAssert.verifyObjNull(filterConfigQuery.getUrlPattern(), "urlPattern");
        long count = EcRedisTemplateHandler.deleteHashKey(EC_OAUTH_FILTER_CONFIG_KEY, filterConfigQuery.getUrlPattern());
        return EcBaseServiceResult.newInstanceOfSuccess().buildResult(count);
    }
}
