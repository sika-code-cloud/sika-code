package com.easy.cloud.core.operator.sysfilterconfig.controller;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.constant.EcLogConstant;
import com.easy.cloud.core.common.log.proxy.impl.EcLogControllerProxy;
import com.easy.cloud.core.operator.sysfilterconfig.logic.SysFilterConfigLogic;
import com.easy.cloud.core.operator.sysfilterconfig.pojo.dto.SysFilterConfigDTO;
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
@EcLogAnnotation(logSwitch = true, analysisSwitch = false, level = EcLogConstant.EcLogLevelEnum.INFO, proxyClass = EcLogControllerProxy.class, type = EcLogConstant.EcLogTypeEnum.CONTROLLER)
public class SysFilterConfigController extends EcBaseController {
    @Autowired
    private SysFilterConfigLogic sysFilterConfigLogic;

    @RequestMapping(value = "saveSysFilterConfig")
    public EcBaseServiceResult saveSysFilterConfig(@RequestBody SysFilterConfigDTO sysFilterConfigDTO) {
        EcBaseServiceResult serviceResult = sysFilterConfigLogic.saveSysFilterConfig(sysFilterConfigDTO);
        return serviceResult;
    }

    @RequestMapping(value = "updateSysFilterConfig")
    public EcBaseServiceResult updateSysFilterConfig(@RequestBody SysFilterConfigDTO sysFilterConfigDTO) {
        EcBaseServiceResult serviceResult =  sysFilterConfigLogic.updateSysFilterConfig(sysFilterConfigDTO);
        return serviceResult;
    }
}
