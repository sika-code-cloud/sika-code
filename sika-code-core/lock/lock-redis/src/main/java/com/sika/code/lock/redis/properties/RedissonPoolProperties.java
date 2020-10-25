package com.sika.code.lock.redis.properties;

import lombok.Data;

/**
 * <p>
 * 池属性
 * </p>
 *
 * @author daiqi
 * @date 2019/7/28 12:56
 */
@Data
public class RedissonPoolProperties {

    private static final int MAX_IDLE_DEFAULT = 8;
    private static final int MIN_IDLE_DEFAULT = 0;
    private static final int MAX_ACTIVE_DEFAULT = 8;
    private static final int MAX_WAIT_DEFAULT = -1;
    private static final int CONN_TIMEOUT_DEFAULT = 3000;
    private static final int SO_TIMEOUT_DEFAULT = 3000;
    private static final int SIZE_DEFAULT = Runtime.getRuntime().availableProcessors();

    private int maxIdle = MAX_IDLE_DEFAULT;

    private int minIdle = MIN_IDLE_DEFAULT;

    private int maxActive = MAX_ACTIVE_DEFAULT;

    private int maxWait = MAX_WAIT_DEFAULT;

    private int connTimeout = CONN_TIMEOUT_DEFAULT;

    private int soTimeout = SO_TIMEOUT_DEFAULT;

    /**
     * 池大小
     */
    private int size = SIZE_DEFAULT;

}