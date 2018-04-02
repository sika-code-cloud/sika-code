package com.dq.easy.cloud.config.client.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@RequestMapping("test")
public class TestConfigController {
	@Value("${from}")
	private String from;
	@Value("${spring.datasource.url}")
	private String url;
	
	@RequestMapping("/findFrom")
	public Map<String,Object> findFrom(){
		Map<String,Object> retMap = new HashMap<String, Object>();
		retMap.put("from", from);
		retMap.put("url", url);
		return retMap;
	}
}
