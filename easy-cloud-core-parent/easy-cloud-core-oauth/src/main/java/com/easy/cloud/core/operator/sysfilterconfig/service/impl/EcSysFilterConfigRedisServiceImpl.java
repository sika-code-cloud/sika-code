package com.easy.cloud.core.operator.sysfilterconfig.service.impl;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.query.SysFilterConfigQuery;
import com.easy.cloud.core.operator.sysfilterconfig.service.EcSysFilterConfigService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 使用reidis处理过滤器配置信息
 * @author daiqi
 * @create 2018-07-20 9:13
 */
@Service
@ConditionalOnProperty(value = "ec.oauth.filter.config.redis")
@ConditionalOnMissingBean(name = "ecSysFilterConfigService")
public class EcSysFilterConfigRedisServiceImpl extends EcBaseService implements EcSysFilterConfigService{
    @Override
    public List<SysFilterConfigDTO> listByAvailable() {
        return null;
    }

    @Override
    public EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        return null;
    }

    @Override
    public EcBaseServiceResult updateSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        return null;
    }

    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        return null;
    }

    @Override
    public EcBaseServiceResult deleteByQuery(SysFilterConfigQuery filterConfigQuery) {
        return null;
    }
}
