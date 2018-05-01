package com.easy.cloud.core.cache.redis.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.easy.cloud.core.basic.controller.EcBaseController;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;

/**
 * 
 * <p>
 * 测试redis注解控制器
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月27日 下午1:59:52
 */
@RestController
@RequestMapping("/testRedisController")
public class TestRedisController extends EcBaseController{
	@Autowired
	private TestRedisService testRedisService;


	@RequestMapping(value = "deleteEcLogDTO")
	@ResponseBody
	public String deleteEcLogDTO(@RequestParam String id) {
		testRedisService.deleteEcLogDTO(id);
		return id;
	}
	@RequestMapping(value = "saveEcLogDTO")
	@ResponseBody
	public EcLogDTO saveEcLogDTO(@RequestParam String id) {
		return testRedisService.saveEcLogDTO(id);
	}

	@RequestMapping(value = "queryEcLogDTO")
	@ResponseBody
	public EcLogDTO queryEcLogDTO(@RequestParam String id) {
		return testRedisService.queryEcLogDTO(id);
	}

	@RequestMapping(value = "updateEcLogDTO")
	@ResponseBody
	public EcLogDTO updateEcLogDTO(@RequestParam String id) {
		return testRedisService.updateEcLogDTO(id);
	}
}
