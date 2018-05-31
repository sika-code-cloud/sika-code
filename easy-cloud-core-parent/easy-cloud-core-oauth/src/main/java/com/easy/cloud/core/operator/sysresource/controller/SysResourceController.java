package com.easy.cloud.core.operator.sysresource.controller;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogLevelEnum;
import com.easy.cloud.core.common.log.constant.EcLogConstant.EcLogTypeEnum;
import com.easy.cloud.core.common.log.proxy.impl.EcLogControllerProxy;
import com.easy.cloud.core.operator.sysresource.pojo.dto.SysResourceDTO;
import com.easy.cloud.core.operator.sysresource.service.SysResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：控制转发类
 * 
 * @author THINK
 * @date 2018-05-30 16:24:17
 */
@RestController(value = "sysResourceController")
@RequestMapping(value = "sysResource")
@EcLogAnnotation(logSwitch = false, analysisSwitch = false)
public class SysResourceController extends EcBaseController {
	@Autowired
	private SysResourceService sysResourceService;

	@RequestMapping(value = "saveResource")
	public EcBaseServiceResult saveResource(@RequestBody SysResourceDTO resourceDTO) {
		return sysResourceService.save(resourceDTO);
	}
}
