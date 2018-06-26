package com.easy.cloud.core.operator.sysfilterconfig.controller;

import com.easy.cloud.core.authority.manager.EcAuthorityManager;
import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
import com.easy.cloud.core.operator.sysfilterconfig.service.SysFilterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：控制转发类
 *
 * @author daiqi
 * @date 2018-06-25 16:36:55
 */
@RestController(value = "sysFilterConfigController")
@RequestMapping(value = "sysFilterConfig")
public class SysFilterConfigController extends EcBaseController {
    @Autowired
    private SysFilterConfigService sysFilterConfigService;
    @Autowired
    private EcAuthorityManager authorityManager;

    @RequestMapping(value = "saveSysFilterConfig")
    public EcBaseServiceResult saveSysFilterConfig(@RequestBody SysFilterConfigDTO sysFilterConfigDTO) {
        EcBaseServiceResult serviceResult = sysFilterConfigService.saveSysFilterConfig(sysFilterConfigDTO);
        authorityManager.updateFilterChains();
        return serviceResult;
    }

    @RequestMapping(value = "updateSysFilterConfig")
    public EcBaseServiceResult updateSysFilterConfig(@RequestBody SysFilterConfigDTO sysFilterConfigDTO) {
        EcBaseServiceResult serviceResult =  sysFilterConfigService.updateSysFilterConfig(sysFilterConfigDTO);
        authorityManager.updateFilterChains();
        return serviceResult;
    }
}
