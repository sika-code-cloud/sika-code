package com.easy.cloud.core.cache.redis.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easy.cloud.core.cache.redis.annotation.EcRedisAnnotation;
import com.easy.cloud.core.cache.redis.constant.EcRedisConstant.EcRedisActionType;
import com.easy.cloud.core.cache.redis.proxy.demo.EcRedisDemoProxy;
import com.easy.cloud.core.common.log.pojo.dto.EcLogDTO;
import com.easy.cloud.core.common.log.utils.EcLogUtils;

/**
 * 
 * <p>
 * 测试redis注解服务类
 * </p>
 *
 * @author daiqi
 * @创建时间 2018年4月27日 下午2:03:50
 */
@Service
public class TestRedisService {
	@Autowired
	private TestRedisDAO testRedisDAO;
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@EcRedisAnnotation(actionType = EcRedisActionType.SAVE, proxyClass = EcRedisDemoProxy.class)
	public EcLogDTO saveEcLogDTO(String id) {
		EcLogDTO logDTO = new EcLogDTO();
		logDTO.setRequestPath("测试的path");
		EcLogUtils.info("保存数据洛", logDTO, logger);
		return logDTO;
	}

//	@EcRedisAnnotation(actionType = EcRedisActionType.QUERY, proxyClass = EcRedisDemoProxy.class)
	public EcLogDTO queryEcLogDTO(String id) {
		EcLogDTO logDTO = new EcLogDTO();
		logDTO.setRequestPath("查询的测试的path");
		logDTO = testRedisDAO.queryRequestPath(id);
		EcLogUtils.info("查询数据洛", logDTO, logger);
		return logDTO;
	}

	@EcRedisAnnotation(actionType = EcRedisActionType.UPDATE, proxyClass = EcRedisDemoProxy.class)
	public EcLogDTO updateEcLogDTO(String id) {
		EcLogDTO logDTO = new EcLogDTO();
		logDTO.setRequestPath("更新的path");
		EcLogUtils.info("更新数据洛", logDTO, logger);
		return logDTO;
	}

	@EcRedisAnnotation(actionType = EcRedisActionType.DELETE, proxyClass = EcRedisDemoProxy.class)
	public void deleteEcLogDTO(String id) {
		EcLogUtils.info("删除数据洛", id, logger);
	}
	
}
