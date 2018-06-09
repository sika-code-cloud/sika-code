package com.easy.cloud.core.reptile.common.selector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.easy.cloud.core.reptile.dynamicbean.service.EcReptileDynamicBeanService;

@Component
public class EcReptileSelector {

	private static EcReptileDynamicBeanService reptileDynamicBeanService;

	public static EcReptileDynamicBeanService getReptileDynamicBeanService() {
		return reptileDynamicBeanService;
	}

	@Autowired
	public void setReptileDynamicBeanService(EcReptileDynamicBeanService reptileDynamicBeanService) {
		EcReptileSelector.reptileDynamicBeanService = reptileDynamicBeanService;
	}
}
