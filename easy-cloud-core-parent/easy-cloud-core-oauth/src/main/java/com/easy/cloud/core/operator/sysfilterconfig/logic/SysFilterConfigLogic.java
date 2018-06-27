package com.easy.cloud.core.operator.sysfilterconfig.logic;

import com.easy.cloud.core.authority.manager.EcAuthorityManager;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.service.SysFilterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author daiqi
 * @create 2018-06-27 12:25
 */
@Component
public class SysFilterConfigLogic {
    @Autowired
    private SysFilterConfigService sysFilterConfigService;
    @Autowired
    private EcAuthorityManager authorityManager;

    @Transactional(rollbackFor = Exception.class)
    public EcBaseServiceResult saveSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        EcBaseServiceResult serviceResult = sysFilterConfigService.saveSysFilterConfig(sysFilterConfigDTO);
        authorityManager.updateFilterChains(sysFilterConfigService.loadFilterChainDefinitions());
        return serviceResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public EcBaseServiceResult updateSysFilterConfig(SysFilterConfigDTO sysFilterConfigDTO) {
        EcBaseServiceResult serviceResult = sysFilterConfigService.updateSysFilterConfig(sysFilterConfigDTO);
        authorityManager.updateFilterChains(sysFilterConfigService.loadFilterChainDefinitions());
        return serviceResult;
    }
}
