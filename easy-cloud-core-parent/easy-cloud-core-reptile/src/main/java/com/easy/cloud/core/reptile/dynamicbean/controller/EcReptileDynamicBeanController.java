package com.easy.cloud.core.reptile.dynamicbean.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.reptile.dynamicbean.pojo.dto.EcReptileDynamicBeanDTO;
import com.easy.cloud.core.reptile.dynamicbean.pojo.query.EcReptileDynamicBeanQuery;
import com.easy.cloud.core.reptile.dynamicbean.service.EcReptileDynamicBeanService;

/**
 * 描述：控制转发类
 * 
 * @author daiqi
 * @date 2018-06-07 17:20:59
 */
@RestController(value = "ecReptileDynamicBeanController")
@RequestMapping(value = "reptileDynamicBean")
public class EcReptileDynamicBeanController extends EcBaseController {
	@Autowired
	private EcReptileDynamicBeanService reptileDynamicBeanService;
	
	public EcBaseServiceResult saveReptileDynamicBean(EcReptileDynamicBeanDTO dynamicBeanDTO) {
		return reptileDynamicBeanService.saveReptileDynamicBean(dynamicBeanDTO);
	}
	
	@RequestMapping(value = "registerToGeccoEngine")
	public EcBaseServiceResult registerToGeccoEngine(@RequestBody EcReptileDynamicBeanQuery dynamicBeanQuery) {
		return reptileDynamicBeanService.registerToGeccoEngine(dynamicBeanQuery);
	}
}
