package com.sika.code.standard.base.pojo.domain;

import com.sika.code.basic.pojo.domain.BaseDomain;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 基础领域接口
 *
 * @author daiqi
 * @create 2019-05-13 13:40
 */
public interface BaseStandardDomain extends BaseDomain {

    /**
     * <p>
     * 获取redisTemplate实例
     * </p>
     *
     * @return RedisTemplate
     * @author daiqi
     * @date 2019/6/20 22:52
     */
    default RedisTemplate redisTemplate() {
        return getBean(RedisTemplate.class);
    }

}
