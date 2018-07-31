package com.easy.cloud.core.operator.sysfilterconfig.service.impl;

import com.easy.cloud.core.basic.service.EcBaseService;
import com.easy.cloud.core.common.map.utils.EcMapUtils;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.service.EcSysFilterConfigService;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * 基础配过滤器配置服务类
 * @author daiqi
 * @create 2018-07-23 21:46
 */
public abstract class EcBaseSysFilterConfigService  extends EcBaseService implements EcSysFilterConfigService {
    /**
     * 构建过滤器链定义列表
     */
    protected synchronized Map<String, String> buildFilterChainDefinitions(Map<String, SysFilterConfigDTO> filterConfigDTOMap) {
        Map<String, String> filterChainDefinitions = Maps.newLinkedHashMap();
        if (EcMapUtils.isEmpty(filterConfigDTOMap)) {
            return filterChainDefinitions;
        }
        //  按照级别进行排序
        filterConfigDTOMap = sortPriorityLevelDesc(filterConfigDTOMap);
        for (String key : filterConfigDTOMap.keySet()) {
            filterChainDefinitions.put(key, filterConfigDTOMap.get(key).getFilterName());
        }
        return filterChainDefinitions;
    }

    /**
     * 按照优先级降序排列
     */
    protected final synchronized Map<String, SysFilterConfigDTO> sortPriorityLevelDesc(Map<String, SysFilterConfigDTO> filterConfigDTOMap) {
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
        Map<String, SysFilterConfigDTO> filterConfigDTOMapTemp = EcMapUtils.newLinkedHashMap();
        for (SysFilterConfigDTO filterConfigDTO : filterConfigDTOS) {
            filterConfigDTOMapTemp.put(filterConfigDTO.getUrlPattern(), filterConfigDTO);
        }
        return filterConfigDTOMapTemp;
    }
}
