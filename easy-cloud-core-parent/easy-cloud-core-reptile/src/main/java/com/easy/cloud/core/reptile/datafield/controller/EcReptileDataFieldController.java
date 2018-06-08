package com.easy.cloud.core.reptile.datafield.controller;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.reptile.datafield.pojo.dto.EcReptileDataFieldDTO;
import com.easy.cloud.core.reptile.datafield.service.EcReptileDataFieldService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：控制转发类
 * 
 * @author THINK
 * @date 2018-06-07 17:02:14
 */
@RestController(value = "ecReptileDataFieldController")
@RequestMapping(value = "reptileDataField")
public class EcReptileDataFieldController extends EcBaseController {
	@Autowired
	private EcReptileDataFieldService reptileDataFieldService;
	
	@RequestMapping(value = "saveReptileDataField")
	public EcBaseServiceResult saveReptileDataField(@RequestBody EcReptileDataFieldDTO dataFieldDTO) {
		return reptileDataFieldService.saveReptileDataField(dataFieldDTO);
	}
	
	@RequestMapping(value = "updateReptileDataField")
	public EcBaseServiceResult updateReptileDataField(@RequestBody EcReptileDataFieldDTO dataFieldDTO) {
		return reptileDataFieldService.updateReptileDataField(dataFieldDTO);
	}
}
