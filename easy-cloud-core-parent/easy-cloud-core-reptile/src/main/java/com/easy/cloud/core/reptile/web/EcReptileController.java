package com.easy.cloud.core.reptile.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileKeyValueDTO;
import com.easy.cloud.core.reptile.pipeline.EcPipelineTest;

@RestController
@RequestMapping(value = "reptile")
@EcLogAnnotation(analysisSwitch = false, logSwitch = false)
public class EcReptileController extends EcBaseController{
	@RequestMapping(value = "addReptileUrl")
	public EcBaseServiceResult addReptileUrl(@RequestParam(name = "serialNum") String serialNum) {
		try {
			EcPipelineTest.queue.put((new EcReptileKeyValueDTO [] {new EcReptileKeyValueDTO("code", serialNum)}));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return EcBaseServiceResult.newInstanceOfSuccess();
	}
}
