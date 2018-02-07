package com.easy.cloud.user.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dq.easy.cloud.model.common.json.utils.DqJSONUtils;

@RestController
@RequestMapping("test")
public class ConfigTest {

	@Autowired
	private Environment environment;
	
	@RequestMapping("getFrom")
	public String getFrom(){
		Map<String, Object> retMap = new HashMap<>();
		retMap.put("from", environment.getProperty("from"));
		return DqJSONUtils.parseObject(retMap, String.class);
	}
}
