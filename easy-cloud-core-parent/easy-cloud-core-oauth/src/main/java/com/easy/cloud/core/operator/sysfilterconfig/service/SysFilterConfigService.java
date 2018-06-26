package com.easy.cloud.core.operator.sysfilterconfig.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;

import java.util.List;
import java.util.Map;

/**
 * 描述：服务接口
 *
 * @author THINK
 * @date 2018-06-25 16:36:55
 */
public interface SysFilterConfigService {
    List<SysFilterConfigDTO> listAll();

    EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO);

    EcBaseServiceResult updateSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO);

    Map<String, String> loadFilterChainDefinitions();
}
