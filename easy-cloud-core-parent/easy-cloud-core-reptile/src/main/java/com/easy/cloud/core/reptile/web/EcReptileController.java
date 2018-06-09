package com.easy.cloud.core.reptile.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.basic.pojo.dto.EcBaseServiceResult;
import com.easy.cloud.core.common.log.annotation.EcLogAnnotation;
import com.easy.cloud.core.reptile.common.constant.EcReptileConstant;
import com.easy.cloud.core.reptile.common.pojo.dto.EcReptileKeyValueDTO;
import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.request.HttpGetRequest;

import javassist.bytecode.annotation.Annotation;

@RestController
@RequestMapping(value = "reptile")
@EcLogAnnotation(analysisSwitch = false, logSwitch = false)
public class EcReptileController extends EcBaseController{
	@Autowired
	private GeccoEngine geccoEngine;
	@RequestMapping(value = "addReptileUrl")
	public EcBaseServiceResult addReptileUrl(@RequestParam(name = "serialNum") String serialNum) {
//		try {
//			EcPipelineTest.queue.put((new EcReptileKeyValueDTO [] {new EcReptileKeyValueDTO("code", serialNum)}));
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		EcReptileKeyValueDTO reptileKeyValueDTO = new EcReptileKeyValueDTO("code", serialNum);
		String tempUrl = EcReptileConstant.MATCH_URL_DETAIL;
		tempUrl = tempUrl.replace(reptileKeyValueDTO.getKey(), reptileKeyValueDTO.getValue());
		String fullUrl = tempUrl.replace("{", "").replace("}", "");
		geccoEngine.getScheduler().into(new HttpGetRequest(fullUrl));
		return EcBaseServiceResult.newInstanceOfSuccess();
	}
}
