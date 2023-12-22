package com.sika.code.demo.interfaces.common.cache;

import com.alibaba.fastjson.JSON;
import com.sika.code.cache.executor.LocalCacheDefaultExecutor;
import com.sika.code.cache.executor.RedisCacheDefaultExecutor;
import com.sika.code.cache.manager.CacheManager;
import com.sika.code.cache.pojo.GetLocalCacheDTO;
import com.sika.code.cache.pojo.GetRedisCacheDTO;
import com.sika.code.core.base.test.BaseTest;
import com.sika.code.demo.application.business.user.service.UserService;
import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.demo.infrastructure.business.user.pojo.query.UserQuery;
import com.sika.code.demo.interfaces.SikaCodeDemoApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * <p>
 * 测试缓存
 * </p>
 *
 * @author by sikadai
 * @version 1.0
 * @since 2022/8/7 17:37
 */
@SpringBootTest(classes = SikaCodeDemoApplication.class)
public class TestCache extends BaseTest {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testGetCache() {
        GetLocalCacheDTO<UserDTO> localCacheDTO = new GetLocalCacheDTO<>();
        GetRedisCacheDTO<UserDTO> redisCacheDTO = new GetRedisCacheDTO<>();
        Class<?> clazz = UserService.class;
        String methodName = "find";
        UserQuery query = new UserQuery();
        query.setId(1L);
        String key = "cache:10002";
        localCacheDTO.setType("1001");
        localCacheDTO.setKey(key);
        localCacheDTO.setMethodName(methodName);
        localCacheDTO.setMethodClass(clazz);
        localCacheDTO.setMethodArgs(new Object[]{query});

        redisCacheDTO.setValueClass(UserDTO.class);
        redisCacheDTO.setExpire(1800L);
        redisCacheDTO.setKey(key);
        redisCacheDTO.setMethodName(methodName);
        redisCacheDTO.setMethodClass(clazz);
        redisCacheDTO.setMethodArgs(new Object[]{query});

        UserDTO userDTO = cacheManager.getCache(localCacheDTO, new LocalCacheDefaultExecutor<>(),
                redisCacheDTO, new RedisCacheDefaultExecutor<>(), redisTemplate);
        log.info("userDTO:{}", JSON.toJSONString(userDTO));
    }

    @Test
    public void testClearCache() {

    }
}
