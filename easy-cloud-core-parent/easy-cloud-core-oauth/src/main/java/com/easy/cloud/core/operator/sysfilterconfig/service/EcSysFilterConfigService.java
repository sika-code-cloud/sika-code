package com.easy.cloud.core.operator.sysfilterconfig.service;

import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.query.SysFilterConfigQuery;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;

import java.util.List;
import java.util.Map;

/**
 * 描述：服务接口
 *
 * @author THINK
 * @date 2018-06-25 16:36:55
 */
public interface EcSysFilterConfigService {
    /**
     * 获取可用的列表数据
     */
    List<SysFilterConfigDTO> listByAvailable();

    EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO);

    EcBaseServiceResult updateSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO);

    Map<String, String> loadFilterChainDefinitions();

    EcBaseServiceResult deleteByQuery(SysFilterConfigQuery filterConfigQuery);
}
