package com.easy.cloud.core.operator.sysfilterconfig.service.impl;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.query.SysFilterConfigQuery;
import com.google.common.collect.Maps;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.core.io.Resource;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用内存处理过滤器配置信息
 *
 * @author daiqi
 * @create 2018-07-20 9:13
 */
public class EcSysFilterConfigMemoryServiceImpl extends EcBaseSysFilterConfigService {

    private Resource shiroConfig;

    public EcSysFilterConfigMemoryServiceImpl() {

    }

    public EcSysFilterConfigMemoryServiceImpl buildShiroConfig(Resource shiroConfig) {
        this.shiroConfig = shiroConfig;
        return this;
    }

    /**
     * 过滤器链定义列表
     */
    protected Map<String, SysFilterConfigDTO> filterConfigDTOMap = Maps.newLinkedHashMap();

    @Override
    public EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        EcAssert.verifyObjNull(sysFilterConfigDTO, "sysFilterConfigDTO");
        EcAssert.verifyObjNull(sysFilterConfigDTO.getFilterName(), "filterName");
        EcAssert.verifyObjNull(sysFilterConfigDTO.getUrlPattern(), "urlPattern");
        EcAssert.verifyObjNull(sysFilterConfigDTO.getPriorityLevel(), "priorityLevel");
        filterConfigDTOMap.put(sysFilterConfigDTO.getUrlPattern(), sysFilterConfigDTO);
        return EcBaseServiceResult.newInstanceOfSuccess();
    }

    @Override
    public synchronized EcBaseServiceResult updateSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        if (filterConfigDTOMap.containsKey(sysFilterConfigDTO.getUrlPattern())) {
            filterConfigDTOMap.put(sysFilterConfigDTO.getUrlPattern(), sysFilterConfigDTO);
        } else {
            return EcBaseServiceResult.newInstanceOfSuccess().buildResult(0);
        }
        return EcBaseServiceResult.newInstanceOfSuccess().buildResult(1);
    }

    @Override
    public synchronized Map<String, String> loadFilterChainDefinitions() {
        if (EcMapUtils.isNotEmpty(filterConfigDTOMap)) {
            return buildFilterChainDefinitions(this.filterConfigDTOMap);
        }
        Yaml yaml = new Yaml();
        Map<String, String> filterChainDefinitions = new LinkedHashMap<>();
        try {
            ShiroFilterFactoryBean shiroFilterFactoryBean = yaml.loadAs(shiroConfig.getInputStream(), ShiroFilterFactoryBean.class);
            if (shiroFilterFactoryBean != null) {
                filterChainDefinitions.putAll(shiroFilterFactoryBean.getFilterChainDefinitionMap());
                int priorityLevel = 0;
                for (String key : filterChainDefinitions.keySet()) {
                    SysFilterConfigDTO filterConfigDTO = new SysFilterConfigDTO();
                    filterConfigDTO.setUrlPattern(key);
                    filterConfigDTO.setFilterName(filterChainDefinitions.get(key));
                    filterConfigDTO.setPriorityLevel(priorityLevel++);
                    filterConfigDTOMap.put(key, filterConfigDTO);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return filterChainDefinitions;
    }

    @Override
    public synchronized EcBaseServiceResult deleteByQuery(SysFilterConfigQuery filterConfigQuery) {
        EcAssert.verifyObjNull(filterConfigQuery, "filterConfigQuery");
        String urlPattern = filterConfigQuery.getUrlPattern();
        EcAssert.verifyObjNull(urlPattern, "urlPattern");
        if (filterConfigDTOMap.containsKey(urlPattern)) {
            SysFilterConfigDTO filterConfigDTO = filterConfigDTOMap.remove(urlPattern);
            return EcBaseServiceResult.newInstanceOfSuccess().buildResult(filterConfigDTO);
        } else {
            return EcBaseServiceResult.newInstanceOfSuccess().buildResult(0);
        }

    }
}
