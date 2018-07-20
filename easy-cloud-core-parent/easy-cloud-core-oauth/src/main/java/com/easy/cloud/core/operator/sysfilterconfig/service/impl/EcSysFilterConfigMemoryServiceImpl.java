package com.easy.cloud.core.operator.sysfilterconfig.service.impl;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.basic.utils.EcAssert;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.query.SysFilterConfigQuery;
import com.easy.cloud.core.operator.sysfilterconfig.service.EcSysFilterConfigService;
import com.google.common.collect.Maps;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.*;

/**
 * 使用内存处理过滤器配置信息
 *
 * @author daiqi
 * @create 2018-07-20 9:13
 */
@Service
@ConditionalOnMissingBean(name = "ecSysFilterConfigService")
public class EcSysFilterConfigMemoryServiceImpl extends EcBaseService implements EcSysFilterConfigService {
    @Value("classpath:config/shiro-filter.yml")
    private Resource shiroConfig;
    /**
     * 过滤器链定义列表
     */
    protected Map<String, SysFilterConfigDTO> filterConfigDTOMap = Maps.newLinkedHashMap();

    @Override
    public synchronized List<SysFilterConfigDTO> listByAvailable() {
        return null;
    }

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
            return buildFilterChainDefinitions();
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

    /**
     * 构建过滤器链定义列表
     */
    private synchronized Map<String, String> buildFilterChainDefinitions() {
        Map<String, String> filterChainDefinitions = Maps.newLinkedHashMap();
        sortPriorityLevelDesc();
        for (String key : filterConfigDTOMap.keySet()) {
            filterChainDefinitions.put(key, filterConfigDTOMap.get(key).getFilterName());
        }
        return filterChainDefinitions;
    }

    /**
     * 按照优先级降序排列
     */
    private synchronized void sortPriorityLevelDesc() {
        List<SysFilterConfigDTO> filterConfigDTOS = new ArrayList<>();
        for (SysFilterConfigDTO filterConfigDTO : filterConfigDTOMap.values()) {
            filterConfigDTOS.add(filterConfigDTO);
        }
        Collections.sort(filterConfigDTOS, new Comparator<SysFilterConfigDTO>() {
            @Override
            public int compare(SysFilterConfigDTO o1, SysFilterConfigDTO o2) {
                return o2.getPriorityLevel() - o1.getPriorityLevel();
            }
        });
        filterConfigDTOMap.clear();
        for (SysFilterConfigDTO filterConfigDTO : filterConfigDTOS) {
            filterConfigDTOMap.put(filterConfigDTO.getUrlPattern(), filterConfigDTO);
        }
    }

    @Override
    public synchronized EcBaseServiceResult deleteByQuery(SysFilterConfigQuery filterConfigQuery) {
        return null;
    }
}
