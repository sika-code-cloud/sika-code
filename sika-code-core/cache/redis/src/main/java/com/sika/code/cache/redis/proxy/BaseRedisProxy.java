package com.sika.code.cache.redis.proxy;


import com.sika.code.cache.redis.annotation.RedisAnnotation;
import com.sika.code.basic.util.Assert;
import com.sika.code.cache.redis.pojo.bo.RedisBO;
import com.sika.code.cache.redis.constant.RedisConstant;
import lombok.Data;
import org.springframework.data.redis.core.*;

/**
 * redis基础代理类---子类通过继承此类实现相关功能
 *
 * @author daiqi
 * @date 2018年4月20日 下午10:49:57
 */
@Data
public abstract class BaseRedisProxy implements RedisProxy {
    protected RedisTemplate<String, Object> redisTemplate;

    @Override
    public final Object handle(RedisBO redisBO) {
        // 执行处理
        return doHandle(redisBO);
    }

    public BaseRedisProxy(RedisTemplate<String, Object> redisTemplate) {
        Assert.verifyObjNull(redisTemplate, "redisTemplate");
        this.redisTemplate = redisTemplate;
    }

    /**
     * 执行处理
     */
    protected final Object doHandle(RedisBO redisBO) {
        RedisAnnotation redisAnnotation = redisBO.getRedisAnnotation();
//        switch (redisAnnotation.actionType()) {
//            case RedisConstant.RedisActionType.QUERY:
//                return query(redisBO);
//            case RedisConstant.RedisActionType.SAVE:
//                return save(redisBO);
//            case RedisConstant.RedisActionType.UPDATE:
//                return update(redisBO);
//            case RedisConstant.RedisActionType.DELETE:
//                return delete(redisBO);
//            default:
//                return redisBO.proceed();
//        }
        return redisBO.proceed();
    }

    /**
     * 查询---子类通过重写此方法实现查询业务逻辑
     */
    protected Object query(RedisBO redisBO) {
        return redisBO.proceed();
    }

    /**
     * 更新---子类通过重写此方法实现更新业务逻辑
     */
    protected Object update(RedisBO redisBO) {
        return redisBO.proceed();
    }

    /**
     * 保存---子类通过重写此方法实现保存业务逻辑
     */
    protected Object save(RedisBO redisBO) {
        return redisBO.proceed();
    }

    /**
     * 删除---子类通过重写此方法实现删除业务逻辑
     */
    protected Object delete(RedisBO redisBO) {
        return redisBO.proceed();
    }

    protected final ValueOperations<String, Object> opsForValue() {
        return redisTemplate.opsForValue();
    }

    protected final SetOperations<String, Object> opsForSet() {
        return redisTemplate.opsForSet();
    }

    protected final ZSetOperations<String, Object> opsForZSet() {
        return redisTemplate.opsForZSet();
    }

    protected final ListOperations<String, Object> opsForList() {
        return redisTemplate.opsForList();
    }

    protected final HashOperations<String, String, Object> opsForHash() {
        return redisTemplate.opsForHash();
    }

    protected final ClusterOperations<String, Object> opsForCluster() {
        return redisTemplate.opsForCluster();
    }
}
